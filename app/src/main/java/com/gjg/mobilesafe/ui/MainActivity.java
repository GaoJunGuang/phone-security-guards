package com.gjg.mobilesafe.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.gjg.mobilesafe.R;
import com.gjg.mobilesafe.adapter.MainUIAdapter;

/**
 * 作者： ${高俊光}
 * 时间： 2015/11/3   21：34.
 */
public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
    private static final String TAG = "MainActivity";
    private GridView gv_main;
    private MainUIAdapter adapter;
    // 用来持久化一些配置信息
    private SharedPreferences sp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreen);
        sp = this.getSharedPreferences("config", Context.MODE_PRIVATE);
        gv_main= (GridView) findViewById(R.id.gv_main);
        adapter=new MainUIAdapter(this);
        gv_main.setAdapter(adapter);
        gv_main.setOnItemClickListener(this);
        gv_main.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, int position, long id) {
                if (position == 0) {
                    AlertDialog.Builder buider = new AlertDialog.Builder(MainActivity.this);
                    buider.setTitle("设置");
                    buider.setMessage("请输入要更改的名称");
                    final EditText et = new EditText(MainActivity.this);
                    et.setHint("请输入文本");
                    buider.setView(et);
                    buider.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            String name = et.getText().toString().trim();
                            if ("".equals(name)) {
                                Toast.makeText(getApplicationContext(),
                                        "内容不能为空", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("lost_name", name);
                                // 完成数据的提交
                                editor.commit();
                                TextView tv  = (TextView) view.findViewById(R.id.tv_main_itemname);
                                tv.setText(name);
                            }

                        }
                    });
                    buider.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    buider.create().show();
                }
                return false;
            }
        });

    }

    /**
     * gridview条目被点击是调用的方法
     * @param parent gridview
     * @param view   条目
     * @param position  条目位置
     * @param id  行号
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e(TAG,"点击位置"+position);
        switch (position) {
            case 0:
                Log.e(TAG,"进入手机防盗");
                Intent lostProtect=new Intent(MainActivity.this,LostProtectActivity.class);
                startActivity(lostProtect);
                break;
            case 7:
                Log.e(TAG,"进入手机防盗");
                Intent atoolsIntent=new Intent(MainActivity.this,AtoolsActivity.class);
                startActivity(atoolsIntent);
                break;

        }

    }
}
