package com.elaine.shuangyiapp;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 * Activity管理工具
 */
public class ActivitysManager {

    private static Stack<Activity> mStack;
    private static ActivitysManager instanse;

    private ActivitysManager() {
        mStack = new Stack<>();
    }

    //单例
    public static ActivitysManager getInstanse() {
        if (instanse == null) {
            instanse = new ActivitysManager();
        }
        return instanse;
    }

    public void add(Activity mActivity) {
        mStack.add(mActivity);
    }

    //获取栈顶Activity
    public Activity getTopActivity() {
        return mStack.lastElement();
    }

    //结束栈顶Activity
    public void killTopActivity() {
        killActivity(getTopActivity());
    }

    //结束Activity
    public void killActivity(Activity activity) {
        if (activity == null) return;
        mStack.remove(activity);
        activity.finish();
        activity = null;
    }

    //结束所有Activity
    public void killAllActivity() {
        for (Activity activity : mStack) {
            activity.finish();
        }
        mStack.clear();
    }

    //退出应用
    public void exitApp(Context mContext) {
        killAllActivity();
        //为了防止推送挂掉就不让app完全退出了
//        ActivityManager manager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
//        manager.restartPackage(mContext.getPackageName());
//        System.exit(0);
    }
}
