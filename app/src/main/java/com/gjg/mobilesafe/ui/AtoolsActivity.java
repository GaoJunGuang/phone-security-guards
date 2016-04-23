package com.gjg.mobilesafe.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gjg.mobilesafe.R;

import java.io.File;

/**
 * 作者： ${高俊光}
 * 时间： 2016/4/17   20：59.
 */
public class AtoolsActivity extends Activity implements View.OnClickListener{
    private TextView tv_query;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atools);
        tv_query= (TextView) this.findViewById(R.id.tv_atools_query);
        tv_query.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_atools_query:
                if(isExitDataBase()){
                    Intent intent=new Intent(this,QueryNumberActivity.class);
                    startActivity(intent);

                }else{
                    progressDialog=new ProgressDialog(this);
                    progressDialog.setMessage("正在下载数据库");
                    progressDialog.show();
                    new Thread(){
                        @Override
                        public void run() {

                            //DownloadFileTask.getFile(path,filepath,pd);

                        }
                    }.start();


                }

                break;
            default:
                break;
        }
    }

    private boolean isExitDataBase() {
        File file=new File("/sdcard/address.db");

        return file.exists();
    }
}
