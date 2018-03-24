package com.elaine.shuangyiapp.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.elaine.shuangyiapp.R;
import com.elaine.shuangyiapp.ShuangYiApplication;

import butterknife.ButterKnife;

/**
 * Fragment基类
 */
public abstract class BaseFragment extends Fragment implements View.OnTouchListener{

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(null == rootView){
            rootView = inflater.inflate(setInflateId(), container, false);
            rootView.setOnTouchListener(this);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(this,rootView);
        init();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    public abstract int setInflateId();

    public abstract void init();


    protected void showToast(String str) {
        if(TextUtils.isEmpty(str)) return;
        Toast.makeText(ShuangYiApplication.getInstance(), str, Toast.LENGTH_SHORT).show();
    }

    public void startActivity(Class clazz){
        startActivity(new Intent(getActivity(), clazz));
    }

    protected void startActivity(Class<?> cls, String... objs) {
        Intent intent = new Intent(getActivity(), cls);
        for (int i = 0; i < objs.length; i++) {
            intent.putExtra(objs[i], objs[++i]);
        }
        startActivity(intent);
    }

    //Activity设置带返回按钮的Toolbar
//    public void setupBackToolbar(String title){
//        setupBackToolbar(R.id.toolbar,title);
//    }

    //id不同的需要自行传入
    public void setupBackToolbar(int toolbarId,String title){
        Toolbar mToolbar = (Toolbar) rootView.findViewById(toolbarId);
        mToolbar.setNavigationIcon(R.drawable.bt_back_grey);//TODO
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        mToolbar.setTitle(title);
    }


    /**
     * 针对SP存储的字符串，简化下操作
     * @param tv  欲显示的TextView
     * @param text  待显示文本
     */
    public void checkAndSetTextView(TextView tv, String text){
        if(!TextUtils.isEmpty(text)){
            tv.setText(text);
        }
    }

    protected boolean filterException(Exception e) {
        if (e != null) {
            e.printStackTrace();
            if(e.getMessage().contains("Connection Lost")){
                showToast("网络连接失败");
            }
            return false;
        } else {
            return true;
        }
    }



}
