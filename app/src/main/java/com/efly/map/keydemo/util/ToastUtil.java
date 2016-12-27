package com.efly.map.keydemo.util;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

/**
 * Created by danfeng.wang on 2016/12/26.
 */

public class ToastUtil
{
    public static Toast toast;
    private static CountDownTimer cdt;
    public static Context mContext;

    public ToastUtil(Context context) {
        this.mContext=context;
    }
    public static void showToastShort(String text){
        if(toast==null){
            toast=Toast.makeText(mContext,text,Toast.LENGTH_SHORT);
            updToastTextSize(toast);
        }
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setText(text+"");
        toast.show();
    }
    public static void showToastCenter(String text){
        if(toast==null){
            toast=Toast.makeText(mContext,text,Toast.LENGTH_LONG);
            updToastTextSize(toast);
        }
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setText(text+"");
        toast.show();
    }
    private static void updToastTextSize(Toast toast){
        try {
            Field field=toast.getClass().getField("mNextView");
            field.setAccessible(true);
            ViewGroup view= (ViewGroup) field.get(toast);
            if(view==null){ return;}
            TextView tv= (TextView) view.getChildAt(0);
            if (tv==null)return;
            tv.setTextSize(50);


        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
