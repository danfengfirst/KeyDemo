package com.efly.map.keydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;

import com.efly.map.keydemo.mytest.NumKeyView;
import com.efly.map.keydemo.util.ToastUtil;

/**
 * Created by danfeng.wang on 2016/12/22.
 */

public class KeyDemo extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {
    private PassWordEdit mEditText;
    private NumKeyView mKeyView;
    private Button mButtonEnsure;
    private ImageView mImageViewCancle;
    private StringBuffer mPasswordStr;

    private ToastUtil mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keyboard_pop);
        init();
    }

    private void init() {
        mEditText = (PassWordEdit) findViewById(R.id.passWordEdit);//自定义密码输入框
        mKeyView = (NumKeyView) findViewById(R.id.keyboardview);//自定义键盘
        mButtonEnsure = (Button) findViewById(R.id.bt_ensure);//确定按钮
        mImageViewCancle = (ImageView) findViewById(R.id.im_cancle);//取消按钮
        mEditText.setInputType(InputType.TYPE_NULL);//隐藏键盘
        mKeyView.setRandomKeyBoard(true);//设置随机数字键盘
        mToast = new ToastUtil(getApplicationContext());
        addListener();
        mEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());//隐藏文本，不加这行代码会显示输入文本


    }

    private void addListener() {
        mEditText.setOnFocusChangeListener(this);
        mButtonEnsure.setOnClickListener(this);
        mImageViewCancle.setOnClickListener(this);
        mEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                showKeyBoard();
                return false;
            }
        });
        final Editable editable = mEditText.getText();
        //对按键的输入与删除事件进行监听
        mKeyView.setOnKeyPressListener(new NumKeyView.OnKeyPressListener() {
            @Override
            public void onInertKey(String text) {
                //这里不可以直接使用mEditText来进行append，否则会显示数字
                if (editable.length() < 6) {
                    mPasswordStr.append(text);
                    editable.append(text);
                }
            }
            @Override
            public void onDeleteKey() {
                if (editable.length() > 0) {
                    int length = editable.length();
                    editable.delete(length - 1, length);
                    mPasswordStr.delete(length - 1, length);
                }
            }
        });
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_ensure:
                if (mPasswordStr.length() <= 0) {
                    mToast.showToastCenter("请输入有效密码");
                }
                if (mPasswordStr.length() == 6) {
                    Intent intent = new Intent();
                    intent.putExtra("data", mPasswordStr.toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
            case R.id.im_cancle:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onFocusChange(View view, boolean hasfocus) {
        mPasswordStr = new StringBuffer();
        if (hasfocus) {
            hideSoftKeyBoard();
            showKeyBoard();
        } else {
            hideKeyBoard();
        }
    }
    public void hideSoftKeyBoard() {
        InputMethodManager im = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
        im.hideSoftInputFromInputMethod(getCurrentFocus().getWindowToken(), InputMethodManager.RESULT_HIDDEN);
    }

    public void showKeyBoard() {
        if (mKeyView != null && (mKeyView.getVisibility() == View.INVISIBLE || mKeyView.getVisibility() == View.GONE)) {
            mKeyView.setVisibility(View.VISIBLE);
        }
    }

    public void hideKeyBoard() {
        if (mKeyView != null && (mKeyView.getVisibility() == View.VISIBLE)) {
            mKeyView.setVisibility(View.GONE);
        }
    }

}
