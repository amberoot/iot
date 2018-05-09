package com.cuanbo.cb_iot.Tool;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


/**
 * Created by xww on 18/3/23.
 */

public class Common {


    private static Dialog dialog = null;
    /**
     * 自定义提示框
     * @param message
     */
//    public static void creatCustomDialog(String message, Context context) {
//        if (dialog != null) {
//            dialog.dismiss();
//        }
//        dialog = new android.app.AlertDialog.Builder(context).create();
//        dialog.show();
//
//        Window window = dialog.getWindow();
//        window.setContentView(R.layout.custom_dialog);
//        window.setGravity(Gravity.CENTER);//设置dialog显示的位置居中
//        //window.setWindowAnimations(R.style.alpha_anim);//添加动画效果
//        //设置对话框背景透明，AlertDialog无效，才有效
//        window.setBackgroundDrawableResource(R.color.FullTransparent);
//
//        //设置对话框的宽度和高度
//        int screenWidth = DeviceMsg.getScreenWidth();
//        int screenHeight = DeviceMsg.getScreenHeiht();
//        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
//        p.width = screenWidth/3;
//        p.height = screenHeight/3;
//        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
//        dialog.getWindow().setAttributes(p);     //设置生效
//
//        TextView tv_title=window.findViewById(R.id.tv_title);
//        tv_title.setText("系统提示");
//        TextView tv_message=window.findViewById(R.id.tv_message);
//        tv_message.setText(message);
//
//        Button btn_yes=window.findViewById(R.id.btn_yes);
//        btn_yes.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO 自动生成的方法存根
//                dialog.dismiss();
//            }
//        });
//    }
//
//    /**
//     *
//     * @param message
//     * @param context
//     */
//    public static void creatQuestDialog(final String message, Context context) {
//        if (dialog != null) {
//            dialog.dismiss();
//        }
//        dialog = new android.app.AlertDialog.Builder(context).create();
//        dialog.show();
//
//        Window window = dialog.getWindow();
//        window.setContentView(R.layout.quest_dialog);
//        window.setGravity(Gravity.CENTER);//设置dialog显示的位置居中
//        //window.setWindowAnimations(R.style.alpha_anim);//添加动画效果
//        //设置对话框背景透明，AlertDialog无效，Dialog才有效
//        window.setBackgroundDrawableResource(R.color.FullTransparent);
//
//        //设置对话框的宽度和高度
//        int screenWidth = DeviceMsg.getScreenWidth();
//        int screenHeight = DeviceMsg.getScreenHeiht();
//        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
//        p.width = screenWidth/3;
//        p.height = screenHeight/3;
//        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
//        dialog.getWindow().setAttributes(p);     //设置生效
//
//        TextView tv_title=window.findViewById(R.id.label_title);
//        tv_title.setText("系统提示");
//        TextView tv_message=window.findViewById(R.id.label_quest);
//        tv_message.setText(message);
//
//        Button btn_cancel=window.findViewById(R.id.btn_cancel2);
//        btn_cancel.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO 自动生成的方法存根
//                dialog.dismiss();
//            }
//        });
//        Button btn_comfirm=window.findViewById(R.id.btn_comfirm2);
//        btn_comfirm.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO 自动生成的方法存根
//
//                if (message.equals("确定要退出系统吗？")) {
//                    dialog.dismiss();
//                    ActivityCollector.finishAll();
//                    System.exit(0);
//                }else if (message.startsWith("是否确定要删除项目")) {
//                    String ProjectName = message.substring(9);
//                    FileHelper fileHelper = new FileHelper();
//                    fileHelper.deleteFile(ProjectName);
//                    dialog.dismiss();
//                }else {
//                    if (StartSocketThread.socket != null) {
//                        try {
//                            StartSocketThread.socket.close();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    new Thread(new StartSocketThread()).start();
//                    dialog.dismiss();
//                }
//
//            }
//        });
//    }
//


    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date curDate = new Date(System.currentTimeMillis());

        return formatter.format(curDate);
    }


    public static void delay(int time){
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

            }
        }, time);
    }

    /**
     * 将16进制字符串(例如"3E 43 4F")转换为byte[]
     *
     * @param str
     * @return
     */
    public static byte[] strToBytes(String str) {
        if(str == null || str.trim().equals("")) {
            return new byte[0];
        }
        
        //去除字符串中的空格转成数组
        String[] array16 = str.split(" ");
        //数组转字符串有,隔开: {3E,43,4F}
        String str16 = Arrays.toString(array16);
        //数组转字符串无,隔开：3E434F
        StringBuilder build = new StringBuilder();
        for (int i = 0; i < array16.length; i++) {
            build.append(array16[i]);
        }
        
        byte[] bytes = new byte[build.length() / 2];
        for(int i = 0; i < build.length() / 2; i++) {
            String subStr = build.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }
        
        return bytes;
    }
    
    //关键代码 运行序列化和反序列化  进行深度拷贝
    public static <T> ArrayList<T> deepCopy(ArrayList<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        ArrayList<T> dest = (ArrayList<T>) in.readObject();
        return dest;
    }

    /**
     * 字符串MD5加密
     * @return
     */
    public static String md5(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 字符串多次MD5加密
     * @param string
     * @param times
     * @return
     */
    public static String md5(String string, int times) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        String md5 = md5(string);
        for (int i = 0; i < times - 1; i++) {
            md5 = md5(md5);
        }
        return md5(md5);
    }

}
