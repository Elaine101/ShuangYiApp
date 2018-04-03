package com.elaine.shuangyiapp.ui.account;

import android.app.AlertDialog;
import android.app.Application;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.elaine.core.api.Constants;
import com.elaine.core.model.CarBrandBean;
import com.elaine.core.model.CarLevelBean;
import com.elaine.core.model.CarTypeBean;
import com.elaine.core.model.InsuranceCompanyBean;
import com.elaine.core.model.UserInformBean;
import com.elaine.core.utils.SPUtils;
import com.elaine.shuangyiapp.ActionCBImpl;
import com.elaine.shuangyiapp.R;
import com.elaine.shuangyiapp.ShuangYiApplication;
import com.elaine.shuangyiapp.tools.GlideImageLoader;
import com.elaine.shuangyiapp.ui.base.BaseActivity;
import com.elaine.shuangyiapp.ui.base.BaseFragment;
import com.elaine.shuangyiapp.utils.ScreenUtils;
import com.elaine.shuangyiapp.views.ProgressWheel;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by elaine on 2018/3/26.
 */

public class PersonCenterActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.rl_member_card)
    RelativeLayout rl_member_card;
    @BindView(R.id.iv_member_card)
    ImageView iv_member_card;
    @BindView(R.id.tv_memberId)
    TextView tv_memberId;
    @BindView(R.id.tv_create_date)
    TextView tv_create_date;
    @BindView(R.id.iv_avatar)
    CircleImageView iv_avatar;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.rb_male)
    RadioButton rb_male;
    @BindView(R.id.rb_female)
    RadioButton rb_female;
    @BindView(R.id.et_id_number)
    EditText et_id_number;
    @BindView(R.id.et_car_id)
    EditText et_carId;
    @BindView(R.id.tv_birthday)
    TextView tv_birthday;
    @BindView(R.id.tv_car_buy_time)
    TextView tv_carBuyTime;
    @BindView(R.id.tv_insurance_data)
    TextView tv_insuranceDate;
    @BindView(R.id.tv_car_type)
    TextView tv_car_type;
    @BindView(R.id.tv_insurance_company)
    TextView tv_insuranceCompany;
    @BindView(R.id.draw_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.rl_right)
    RelativeLayout rl_right;
    @BindView(R.id.right_list_view)
    ListView right_listView;

    private String avatarPath;
    private Calendar calendar;
    private  int year,month,day;
    private long today;
    private static int type = 0; //判断车型列表当前处于哪一页
    private UserInformBean userInform;
    private String carLevel;
    private String carBrand;
    private String carType;
    private List<String> insuranceCompanyList;
    private List<String> carList;
    private ArrayAdapter<String> arrayAdapter;
    private Map<String,String> carLevelMap;
    private Map<String,String> carBrandMap;
    private Map<String,String> carTypeMap;

    private ProgressWheel  progressWheel;

    @OnClick(R.id.iv_back) void back(){
         finish();
    }
    @OnClick(R.id.rl_avatar) void pickPicture(){
        ImagePicker imagePicker = ImagePicker.getInstance();
        //设置图片加载器
        imagePicker.setImageLoader(new GlideImageLoader());
        //设置为单选
        imagePicker.setMultiMode(false);
        //剪裁
        imagePicker.setCrop(true);
        //剪裁类型
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);
        //剪裁宽度、高度
        imagePicker.setFocusWidth(ScreenUtils.getScreenWidth(this));
        imagePicker.setFocusHeight(ScreenUtils.getScreenWidth(this));
        Intent intent = new Intent(this, ImageGridActivity.class);
        startActivityForResult(intent, 1);
    }
    @OnClick(R.id.rl_birthday) void setBirthday(){
        setDataPickDialog(tv_birthday);
    }
    @OnClick(R.id.rl_car_buy_time) void setCarBuyTime(){
        setDataPickDialog(tv_carBuyTime);
    }
    @OnClick(R.id.rl_insurance_company) void setInsuranceCompany(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(insuranceCompanyList.toArray(new CharSequence[insuranceCompanyList.size()]), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                 tv_insuranceCompany.setText(insuranceCompanyList.get(which));
            }
        });
        builder.create().show();
    }
    @OnClick(R.id.rl_insurance_date) void setInsuranceDate(){
        setDataPickDialog(tv_insuranceDate);
    }
    @OnClick(R.id.rl_car_type) void setCarType(){
        if (!drawerLayout.isDrawerOpen(rl_right)){
            drawerLayout.openDrawer(rl_right);
            if (type!=1){
                carList.clear();
                carList.addAll(new ArrayList<String>(carLevelMap.keySet()));
                arrayAdapter.notifyDataSetChanged();
                type = 1;
            }

            Log.d("TAG", "onItemClick: "+type);
        }
    }
    @OnClick(R.id.bt_save) void save(){
        if (TextUtils.isEmpty(avatarPath)){
            showToast("请设置头像");
        }
        if (TextUtils.isEmpty(et_name.getText())){
            showToast("请输入真实姓名");
        }
        if (TextUtils.isEmpty(et_id_number.getText())){
            showToast("请输入身份证号码");
        }
        if (TextUtils.isEmpty(tv_birthday.getText())){
            showToast("请输入生日号码");
        }
        if (TextUtils.isEmpty(tv_car_type.getText())){
            showToast("请输入您车的类型");
        }
        if (TextUtils.isEmpty(tv_carBuyTime.getText())){
            showToast("请输入您车购买的时间");
        }
        if (TextUtils.isEmpty(tv_carBuyTime.getText())){
            showToast("请输入您车购买的时间");
        }
        if (TextUtils.isEmpty(et_carId.getText())){
            showToast("请输入您的车牌号码");
        }
        if (TextUtils.isEmpty(tv_insuranceCompany.getText())){
            showToast("请输入您车的保险公司");
        }
        if (TextUtils.isEmpty(tv_insuranceDate.getText())){
            showToast("请输入您办理保险的时间");
        }
        submitResult();

    }
    //提交结果
    private void submitResult() {
        Log.d("TAG", "submitResult: "+avatarPath);
        String sex;
        if (rb_male.isChecked()){
            sex = "1";
        }else {
            sex = "0";
        }
        ShuangYiApplication.getInstance().getAccountAction().suppleInformation("supplement_information",
                SPUtils.getString(this,Constants.TOKEN),
                "http://abc",
                et_name.getText().toString().trim(),
                sex,
                et_id_number.getText().toString().trim(),
                tv_birthday.getText().toString().trim(),
                tv_car_type.getText().toString().trim(),
                tv_carBuyTime.getText().toString().trim(),
                et_carId.getText().toString().trim(),
                tv_insuranceCompany.getText().toString().trim(),
                tv_insuranceDate.getText().toString().trim(),
                new ActionCBImpl<String>(this){
                    @Override
                    public void onSuccess(String data) {
                        super.onSuccess(data);
                        showToast("修改成功");
                    }
                }
                );
    }

    @OnClick(R.id.iv_back_to_center)void backList(){
        if (type ==1){
            drawerLayout.closeDrawer(rl_right);
        }else if (type ==2){
            carList.clear();
            carList.addAll(carLevelMap.keySet());
            arrayAdapter.notifyDataSetChanged();
            type = 1;
        } else if (type ==3){
            carList.clear();
            carList.addAll(carBrandMap.keySet());
            arrayAdapter.notifyDataSetChanged();
            type = 2;
        } else {
            drawerLayout.closeDrawer(rl_right);
        }

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    type = 1;
                    arrayAdapter.notifyDataSetChanged();
                    break;
                case 1:
                case 2:
                    arrayAdapter.notifyDataSetChanged();
                    progressWheel.dismiss();
                    break;
                case 3:
                    arrayAdapter.notifyDataSetChanged();
                    progressWheel.dismiss();
            }
        }
    };

    //获取当前时间
    private void getDate() {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        today = calendar.getTimeInMillis();

    }

    @Override
    public int setInflateId() {
        return R.layout.activity_person_center;
    }

    @Override
    public void init() {
        //设置不自动弹出键盘
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //设置CarType的那个drawer关闭手势滑动
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        insuranceCompanyList = new ArrayList<>();
        carList = new ArrayList<>();
        carLevelMap = new HashMap<>();
        carBrandMap = new HashMap<>();
        carTypeMap = new HashMap<>();
        userInform = new UserInformBean();
        arrayAdapter = new ArrayAdapter<String>(PersonCenterActivity.this,android.R.layout.simple_spinner_dropdown_item,carList);
        right_listView.setAdapter(arrayAdapter);
        right_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (type ==1){
                    carLevel = ((TextView)view).getText().toString();
                    Log.d("TAG", "onItemClick: "+carLevel );
                    getCarType(carLevelMap.get(carLevel),null);
                }else if (type ==2){
                    carBrand = ((TextView)view).getText().toString();
                    Log.d("TAG", "onItemClick: "+carLevelMap.get(carLevel) +carBrandMap.get(carBrand));
                    getCarType(carLevelMap.get(carLevel),carBrandMap.get(carBrand));
                } else if (type ==3){
                    carType =((TextView)view).getText().toString();
                    Log.d("TAG", "onItemClick: "+carType);
                    tv_car_type.setText(carType);
                    drawerLayout.closeDrawer(rl_right);
                }

            }
        });
        progressWheel = new ProgressWheel(this);
        initData();
        initInternetRequest();

    }

    private void initData() {
        ShuangYiApplication.getInstance().getAccountAction().getUserInform("get_user_info", SPUtils.getString(this,Constants.TOKEN),new ActionCBImpl<UserInformBean>(this)
        {
            @Override
            public void onSuccess(UserInformBean data) {
                super.onSuccess(data);
                userInform = data;
                tv_memberId.setText(data.getMember_card());
                Glide.with(getApplicationContext()).load(data.getHeadimg())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.iv_me_defalut_avatar)
                        .into(iv_avatar);
                if (TextUtils.isEmpty(data.getSex())||data.getSex().equals("1")){
                    rb_male.setChecked(true);
                    rb_female.setChecked(false);
                }else {
                    rb_male.setChecked(false);
                    rb_female.setChecked(true);
                }
                tv_birthday.setText(data.getBirthday());
                et_name.setText(userInform.getRealname());
                if (!TextUtils.isEmpty(tv_birthday.getText())){
                    tv_birthday.setText(userInform.getBirthday());
                }
                if (!TextUtils.isEmpty(et_id_number.getText())){
                    et_carId.setText(userInform.getIdnum());
                }
                if (!TextUtils.isEmpty(tv_car_type.getText())){
                    tv_car_type.setText(userInform.getCar_type());
                }
                if (!TextUtils.isEmpty(tv_carBuyTime.getText())){
                    tv_carBuyTime.setText(userInform.getCar_buytime());
                }
                if (!TextUtils.isEmpty(et_carId.getText())){
                    et_carId.setText(userInform.getCar_type_id());
                }
                if (!TextUtils.isEmpty(tv_insuranceCompany.getText())){
                    tv_insuranceCompany.setText(userInform.getInsurance_company());
                }
                if (!TextUtils.isEmpty(tv_insuranceDate.getText())){
                    tv_insuranceDate.setText(userInform.getInsurance_time());
                }


            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode ==ImagePicker.RESULT_CODE_ITEMS){
            Log.d("TAG", "onActivityResult: "+resultCode);
            if (data !=null&& requestCode == 1){
                ArrayList<ImageItem> imageItems = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                avatarPath = imageItems.get(0).path;
                Log.d("TAG", "onActivityResult: "+avatarPath);
                Glide.with(this).load(avatarPath).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv_avatar);
            }
        }
    }

    public void setDataPickDialog(final TextView tv){
        getDate();
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                tv.setText(year+"-"+month+"-"+day);
            }
        };
        DatePickerDialog dialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,listener,year,month,day);
        DatePicker datePicker = dialog.getDatePicker();
        datePicker.setMaxDate(today);
        dialog.show();
    }

    public void initInternetRequest(){
      InternetThread internetThread = new InternetThread();
      internetThread.start();
    }

    public void getCarType(final String carLevel, final String carBrand){
        progressWheel.show();
        if (carBrand==null){
            type = 2;
            Thread getLevelThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    ShuangYiApplication.getInstance().getAccountAction().getCarBrandByLevel("get_brand_by_level",carLevel,new ActionCBImpl<CarBrandBean>(getApplicationContext()){
                        @Override
                        public void onSuccess(CarBrandBean data) {
                            super.onSuccess(data);
                            carList.clear();
                            for(int i=0;i<data.getResData().size();i++){
                                carList.add(data.getResData().get(i).getName());
                                carBrandMap.put(data.getResData().get(i).getName(),data.getResData().get(i).getId());
                            }
                            handler.sendEmptyMessage(2);
                        }
                    });
                }
            });
            getLevelThread.start();
        }else {
            type = 3;
            Thread getTypeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    ShuangYiApplication.getInstance().getAccountAction().getCarType("get_car_type",carBrand,carLevel,new ActionCBImpl<CarTypeBean>(getApplicationContext()){
                        @Override
                        public void onSuccess(CarTypeBean data) {
                            super.onSuccess(data);
                            carList.clear();
                            for (int i=0;i<data.getResData().size();i++){
                                carList.add(data.getResData().get(i).getName());
                                carTypeMap.put(data.getResData().get(i).getName(),data.getResData().get(i).getId());
                            }
                            handler.sendEmptyMessage(3);
                        }
                    });
                }
            });
            getTypeThread.start();
        }

    }

    class InternetThread extends Thread{
        @Override
        public void run() {
            ShuangYiApplication.getInstance().getAccountAction().getCarLevel("get_car_level",new ActionCBImpl<CarLevelBean>(getApplicationContext()){
                @Override
                public void onSuccess(CarLevelBean data) {
                    super.onSuccess(data);
                    for(int i=0;i<data.getResData().size();i++){
                        carList.add(data.getResData().get(i).getName());
                        carLevelMap.put(data.getResData().get(i).getName(),data.getResData().get(i).getId());
                    }
                    handler.sendEmptyMessage(0);
                }
            });
            ShuangYiApplication.getInstance().getAccountAction().getAllInsuranceCompany("insurance_company",new ActionCBImpl<InsuranceCompanyBean>(getApplicationContext()){
                @Override
                public void onSuccess(InsuranceCompanyBean data) {
                    for (int i=0;i<data.getResData().size();i++){
                        insuranceCompanyList.add(data.getResData().get(i).getName());
                    }
                    handler.sendEmptyMessage(1);

                }
            });
        }
    }


}
