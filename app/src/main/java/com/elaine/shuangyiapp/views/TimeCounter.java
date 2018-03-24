package com.elaine.shuangyiapp.views;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * 按钮倒计时
 */
//获取验证码倒计时
public class TimeCounter extends CountDownTimer {
    private Button button;
    public TimeCounter(long millisInFuture, long countDownInterval, Button button) {
        super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        this.button = button;
    }
    @Override
    public void onTick(long millisUntilFinished) {
        button.setClickable(false);
        //bt_getCheck.setTextColor(0xff0DA49D);
        button.setText(millisUntilFinished /1000+"秒");
    }

    @Override
    public void onFinish() {
        //计时完毕时触发
        //bt_getCheck.setTextColor(0xffffffff);
        button.setText("重新验证");
        button.setClickable(true);
    }
}
