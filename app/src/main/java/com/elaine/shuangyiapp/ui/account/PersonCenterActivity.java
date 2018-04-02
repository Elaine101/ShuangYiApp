package com.elaine.shuangyiapp.ui.account;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
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
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.elaine.core.api.Constants;
import com.elaine.core.model.CarLevelBean;
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
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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

    private Calendar calendar;
    private  int year,month,day;
    private long today;
    private List<String> insuranceCompanyList;
    private UserInformBean userInform;
    private List<String> carLevelList;
    private ArrayAdapter<String> arrayAdapter;
    private Map<String,String> carLevelMap;

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
        }
    }
    @OnClick(R.id.bt_save) void save(){

    }
    @OnClick(R.id.iv_back_to_center)void backToCenter(){
        drawerLayout.closeDrawer(rl_right);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    arrayAdapter = new ArrayAdapter<String>(PersonCenterActivity.this,android.R.layout.simple_spinner_dropdown_item,carLevelList);
                    right_listView.setAdapter(arrayAdapter);
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
        Log.d("TAG", "getDate: "+"年"+year+"月"+month+"日"+day);

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

        userInform = new UserInformBean();
        insuranceCompanyList = new ArrayList<>();
        carLevelList = new ArrayList<>();
        carLevelMap = new HashMap<>();
        right_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG", "onItemClick: "+carLevelList.get(position)+carLevelMap.get(carLevelList.get(position)));
                drawerLayout.closeDrawer(rl_right);
            }
        });

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
                if (TextUtils.isEmpty(data.getSex())){
                    rb_male.setChecked(true);
                }else {

                }
                tv_birthday.setText(data.getBirthday());


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
                String avatarPath = imageItems.get(0).path;
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

    public void getBrandByLevel(){}

    class InternetThread extends Thread{
        @Override
        public void run() {
            ShuangYiApplication.getInstance().getAccountAction().getCarLevel("get_car_level",new ActionCBImpl<CarLevelBean>(getApplicationContext()){
                @Override
                public void onSuccess(CarLevelBean data) {
                    super.onSuccess(data);
                    for(int i=0;i<data.getResData().size();i++){
                        carLevelList.add(data.getResData().get(i).getName());
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
