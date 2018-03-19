package com.elaine.core;

/**
 * 实现ActionCallbackListener,对其进行一个公共统一的处理
 */
public interface ActionCallback<T> {

    public void onSuccess(T data);

    public void onFailure(int errorCode, String messge);

    public void onBefore();

    public void onAfter();

    public void inProgress(float progress);

    public void onError();
}
