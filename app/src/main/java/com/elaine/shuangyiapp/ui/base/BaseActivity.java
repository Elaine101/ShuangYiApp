package com.elaine.shuangyiapp.ui.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.elaine.core.AccountAction;
import com.elaine.shuangyiapp.ActivitysManager;
import com.elaine.shuangyiapp.R;
import com.elaine.shuangyiapp.ShuangYiApplication;


import butterknife.ButterKnife;

;

public abstract class BaseActivity extends AppCompatActivity {

    public Context mContext;
    public ShuangYiApplication application;
    public AccountAction accountAction;

//    SystemBarTintManager mTintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitysManager.getInstanse().add(this);

        mContext = this;
        application = (ShuangYiApplication) getApplication();
        accountAction = application.getAccountAction();


        setContentView(setInflateId());
        ButterKnife.bind(this);
        init();
        initAcition();
//        applyKitKatTranslucency();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitysManager.getInstanse().killActivity(this);
    }

    //设置布局id
    public abstract int setInflateId();

    //视图，组件,数据的初始化
    public abstract void init();

    //事件监听
    public void initAcition(){}


    /**
     * 不需要沉浸状态栏覆写此方法即可
     */
    public void applyKitKatTranslucency() {
        // KitKat translucent navigation/status bar.
//        applyKitKatTranslucency(getResources().getColor(R.color.colorPrimary));
    }

//    public void applyKitKatTranslucency(int color){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setTranslucentStatus(true); //设置透明状态栏
//            mTintManager = new SystemBarTintManager(this);
//            mTintManager.setStatusBarTintEnabled(true);
//            mTintManager.setNavigationBarTintEnabled(true);
//            mTintManager.setTintColor(color); //设置状态栏颜色
//        }
//    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    //Activity设置带返回按钮的Toolbar
//    public void setupBackToolbar(String title){
//        setupBackToolbar(R.id.toolbar,title);
//    }

    //id不同的需要自行传入
    public void setupBackToolbar(int toolbarId,String title){
        Toolbar mToolbar = (Toolbar) findViewById(toolbarId);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //显示小箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(R.drawable.bt_back_grey);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setTitle(title);
    }


    //以下为常用方法
     public void showToast(String msg){
         if(TextUtils.isEmpty(msg)) return;
         Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
     }

    protected void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    public void startActivity(Class<?> cls, String... objs) {
        Intent intent = new Intent(this, cls);
        for (int i = 0; i < objs.length; i++) {
            intent.putExtra(objs[i], objs[++i]);
        }
        startActivity(intent);
    }

    protected boolean filterException(Exception e) {
        if (e != null) {
            e.printStackTrace();
            showToast(e.getMessage());
            return false;
        } else {
            return true;
        }
    }

}
