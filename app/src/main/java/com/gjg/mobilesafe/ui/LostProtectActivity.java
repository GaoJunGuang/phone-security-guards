package com.gjg.mobilesafe.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gjg.mobilesafe.R;
import com.gjg.mobilesafe.util.MD5Encoder;

/**
 * 作者： ${高俊光}
 * 时间： 2015/11/16   19：02.
 */
public class LostProtectActivity extends Activity implements View.OnClickListener {
    private static final String TAG ="LostProtectActivity" ;
    private SharedPreferences sharedPreferences;
    private Dialog dialog;
    private EditText et_pwd;
    private EditText et_pwd_confirm;
    private TextView tv_lost_protected;
    private TextView tv_reenter_setup_wizard;
    private CheckBox cb_isprotecting;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences=getSharedPreferences("config", Context.MODE_PRIVATE);
        if(isSetPwd()){
            Log.i(TAG,"设置了密码，正常登录");
            showNormalEntryDialog();
        }else{
            Log.i(TAG,"没有设置密码，进入第一次登录界面");
            showFirstEntryDialog();
        }
    }

    /**
     * 是否设置密码
     * @rurn
     */
    public boolean isSetPwd(){
        String password=sharedPreferences.getString("password", null);
        if(password==null){
            return false;
        }else{
            if("".equals(password)){
                return false;
            }else{
                return true;
            }
        }

    }

    /**
     * 正常登陆的对话框
     */
    private void showNormalEntryDialog() {
        dialog = new Dialog(this, R.style.MyDialog);
        //dialog.setContentView(R.layout.first_entry_dialog);
        View view = View.inflate(this, R.layout.normal_entry_dialog, null);
        et_pwd = (EditText) view.findViewById(R.id.et_normal_entry_pwd);
        Button bt_normal_ok = (Button) view.findViewById(R.id.bt_normal_dialog_ok);
        Button bt_normal_cancle =  (Button) view.findViewById(R.id.bt_normal_dialog_cancle);
        bt_normal_ok.setOnClickListener(this);
        bt_normal_cancle.setOnClickListener(this);
        dialog.setContentView(view);
        dialog.show();

    }

    /**
     * 第一次进入程序时候的对话框
     */
    private void showFirstEntryDialog() {
        dialog = new Dialog(this, R.style.MyDialog);
        //dialog.setContentView(R.layout.first_entry_dialog);
        View view = View.inflate(this, R.layout.first_entry_dialog, null);
        et_pwd = (EditText) view.findViewById(R.id.et_first_entry_pwd);
        et_pwd_confirm = (EditText) view.findViewById(R.id.et_first_entry_pwd_confirm);
        Button bt_ok = (Button) view.findViewById(R.id.bt_first_dialog_ok);
        Button bt_cancle =  (Button) view.findViewById(R.id.bt_first_dialog_cancle);
        bt_ok.setOnClickListener(this);
        bt_cancle.setOnClickListener(this);
        dialog.setContentView(view);
        dialog.show();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_first_dialog_cancle:
                dialog.dismiss();
                break;
            case R.id.bt_first_dialog_ok:
                String pwd = et_pwd.getText().toString().trim();
                String pwd_confirm = et_pwd_confirm.getText().toString().trim();
                if("".equals(pwd)||"".equals(pwd_confirm)){
                    Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    if(pwd.equals(pwd_confirm)){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("password", MD5Encoder.encode(pwd));
                        editor.commit();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "两次密码不同", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                dialog.dismiss();
                finish();
                Intent intents=new Intent(LostProtectActivity.this,SetUpWizard.class);
                startActivity(intents);
                break;
            case R.id.bt_normal_dialog_cancle:
                dialog.dismiss();
                finish();
                break;
            case R.id.bt_normal_dialog_ok:
                String password = et_pwd.getText().toString().trim();
                if("".equals(password)){
                    Toast.makeText(getApplicationContext(), "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }else{

                    String realpwd = sharedPreferences.getString("password", "");
                    if (realpwd.equals(MD5Encoder.encode(password))){
                        if(isSetUpGuide()){//true
                            Log.i(TAG, "加载手机防盗主界面");
                            enterPhoneProtect();


                        }else{
                            Log.i(TAG,"激活手机设置向导");
                            finish();
                            Intent intent=new Intent(LostProtectActivity.this,SetUpWizard.class);
                            startActivity(intent);

                        }


                    }else{
                        Toast.makeText(getApplicationContext(), "密码错误", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                dialog.dismiss();
                break;
            case R.id.tv_reenter_setup_wizard:
                finish();
                Intent intent=new Intent(LostProtectActivity.this,SetUpWizard.class);
                startActivity(intent);
                break;

        }

    }

    private void enterPhoneProtect() {
        setContentView(R.layout.lost_protected);
        //初始化控件
        tv_lost_protected= (TextView) this.findViewById(R.id.tv_lost_protected);
        tv_reenter_setup_wizard= (TextView) this.findViewById(R.id.tv_reenter_setup_wizard);
        cb_isprotecting= (CheckBox) this.findViewById(R.id.cb_isprotecting);

        String number=sharedPreferences.getString("safenumber",null);
        tv_lost_protected.setText("安全号码为："+number);
        //重新进入设置向导
        tv_reenter_setup_wizard.setOnClickListener(this);

        // 初始化checkbox的状态
        boolean isprotecting = sharedPreferences.getBoolean("isprotecting", false);
        if(isprotecting){
            cb_isprotecting.setText("手机防盗保护中");
            cb_isprotecting.setChecked(true);
        }

        cb_isprotecting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_isprotecting.setText("手机防盗保护中");
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isprotecting", true);
                    editor.commit();
                } else {
                    cb_isprotecting.setText("没有开启防盗保护");
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isprotecting", false);
                    editor.commit();
                }

            }
        });
    }

    /**
     * 是否设置向导
     * @return
     */
    private boolean isSetUpGuide(){
        return sharedPreferences.getBoolean("isSetUpAlready",false);
    }

}
