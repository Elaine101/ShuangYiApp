package com.elaine.shuangyiapp.views;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.elaine.shuangyiapp.R;


/**
 * 对话框封装类
 */
public class CommonDialog extends DialogFragment {

    private CommonBuilder builder;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return builder.getDialog();
    }

    public void setBuilder(CommonBuilder builder) {
        this.builder = builder;
    }


    public static class CommonBuilder {

        private int mTheme;
        private Context context;

        private Dialog dialog;

        private CommonDialog CommonDialog;

        //基础控件
        private LinearLayout ll_content, ll_bottom; //内容容器，替换为其他View
        private TextView tv_title; //标题
        private Button bt_cancel, bt_sure;

        public CommonBuilder(Context context) {
            this(context, 0);
        }

        public CommonBuilder(Context context, int theme) {
            this.context = context;
            this.mTheme = theme;
            if (mTheme == 0) {
                mTheme = R.style.CustomDialog;
            }
            init();
        }

        private void init() {
            dialog = new Dialog(context, mTheme);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
            dialog.setContentView(R.layout.dialog_common);
            dialog.setCanceledOnTouchOutside(true);

            // 设置宽度为屏宽、靠近屏幕底部。
            Window window = dialog.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.CENTER;
            wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(wlp);
            initView(dialog); //布局初始化
        }

        public CommonBuilder title(String title) {
            tv_title.setText(title);
            return this;
        }

        public EditBuilder editMode() {
            View editView = View.inflate(context, R.layout.dialog_edit, null);
            ll_content.addView(editView);
            final AppCompatEditText et_content = (AppCompatEditText) editView.findViewById(R.id.et_content);

            return new EditBuilder(et_content);
        }

        public AlertBuilder alertMode() {
            View editView = View.inflate(context, R.layout.dialog_alert, null);
            ll_content.addView(editView);
            TextView alertText = (TextView) editView.findViewById(R.id.tv_content);
            return new AlertBuilder(alertText);
        }

        public ProgressBuilder progressMode() {
            View progressView = View.inflate(context, R.layout.dialog_progress, null);
            TextView tv_msg = (TextView) progressView.findViewById(R.id.tv_msg);
            ll_content.addView(progressView);
            disableBottom();
            return new ProgressBuilder(tv_msg);
        }

        public CommonBuilder customViewMode(int layoutRes) {
            dialog.setContentView(layoutRes);
            return this;
        }
        public CommonBuilder customViewMode(View view) {
            dialog.setContentView(view);
            return this;
        }


        private void initView(Dialog dialog) {
            ll_content = (LinearLayout) dialog.findViewById(R.id.ll_content);
            ll_bottom = (LinearLayout) dialog.findViewById(R.id.ll_bottom);
            tv_title = (TextView) dialog.findViewById(R.id.tv_title);

            bt_cancel = (Button) dialog.findViewById(R.id.bt_cancel);
            bt_sure = (Button) dialog.findViewById(R.id.bt_sure);

            bt_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDismiss();
                }
            });
        }

        /**
         * 不显示底部按钮
         */
        private void disableBottom() {
            ll_bottom.setVisibility(View.GONE);
        }


        //警告框
        public class AlertBuilder {
            private TextView alertText;

            public AlertBuilder(final TextView alertText) {
                this.alertText = alertText;
            }

            public AlertBuilder message(String message) {
                alertText.setText(message);
                return this;
            }

            public AlertBuilder onSure(final OnClick click) {
                bt_sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (click != null) {
                            click.onSure();
                        }
                        onDismiss();
                    }
                });
                return this;
            }

            public CommonDialog show(FragmentManager manager) {
                return onShow(manager);
            }
        }

        //编辑框
        public class EditBuilder {

            private AppCompatEditText editView;

            public EditBuilder(final AppCompatEditText editText) {
                this.editView = editText;
            }

            public EditBuilder editText(String text) {
                editView.setText(text);
                return this;
            }

            public EditBuilder onSure(final ButtonClick click) {
                bt_sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (click != null) {
                            click.onSure(editView.getText().toString().trim());
                        }
                        onDismiss();
                    }
                });
                return this;
            }

            public CommonDialog show(FragmentManager manager) {
                return onShow(manager);
            }
        }

        //进度条对话框
        public class ProgressBuilder {

            private TextView tv_msg;

            public ProgressBuilder(TextView tv) {
                this.tv_msg = tv;
            }

            public ProgressBuilder setMessage(String message) {
                tv_msg.setText(message);
                return this;
            }

            public CommonDialog show(FragmentManager manager) {
                return onShow(manager);
            }
        }

        //单选框
        public class SingleCheckBuilder {

            public RadioGroup group;

            public SingleCheckBuilder(RadioGroup radioGroup) {
                this.group = radioGroup;
            }

            public CommonDialog show(FragmentManager manager) {
                return onShow(manager);
            }
        }

        public Dialog getDialog() {
            return dialog;
        }


        public void onDismiss() {
            if (CommonDialog != null) {
                CommonDialog.dismiss();
            }
        }

        public CommonDialog onShow(FragmentManager manager) {
            CommonDialog = new CommonDialog();
            CommonDialog.setBuilder(this);
            CommonDialog.show(manager, "");
            return CommonDialog;
        }



        //监听接口
        public interface ButtonClick {
            void onSure(String content);
        }

        public interface OnClick {
            void onSure();
        }
    }

}
