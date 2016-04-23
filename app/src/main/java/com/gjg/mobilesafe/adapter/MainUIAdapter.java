package com.gjg.mobilesafe.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gjg.mobilesafe.R;

/**
 * 作者： ${高俊光}
 * 时间： 2015/11/15   15：20.
 */
public class MainUIAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private static ImageView iv_icon;
    private static TextView tv_itemname;
    private SharedPreferences sp;

    public MainUIAdapter(Context context) {
        this.context = context;
        inflater= LayoutInflater.from(context);
        sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
    }

    private static String[] names = { "手机防盗", "通讯卫士", "软件管理", "任务管理", "流量管理",
            "手机杀毒", "系统优化", "高级工具", "设置中心" };
    private static int[] icons = { R.drawable.widget05, R.drawable.widget02,
            R.drawable.widget01, R.drawable.widget07, R.drawable.widget05,
            R.drawable.widget04, R.drawable.widget06, R.drawable.widget03,
            R.drawable.widget08 };

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=inflater.inflate(R.layout.mainscreen_item,null);
        iv_icon= (ImageView) view.findViewById(R.id.iv_main_icon);
        tv_itemname= (TextView) view.findViewById(R.id.tv_main_itemname);
        iv_icon.setImageResource(icons[position]);
        tv_itemname.setText(names[position]);
        if(position==0){
            String name = sp.getString("lost_name", "手机防盗");
            if(!"手机防盗".equals(name)){
                tv_itemname.setText(name);
            }
        }
        return view;
    }
}
