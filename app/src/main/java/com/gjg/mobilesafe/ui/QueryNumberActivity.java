package com.gjg.mobilesafe.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gjg.mobilesafe.R;

/**
 * 作者： ${高俊光}
 * 时间： 2016/4/17   22：49.
 */
public class QueryNumberActivity extends Activity {
    private TextView tv_query_number_address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query_number);
        tv_query_number_address= (TextView) this.findViewById(R.id.tv_query_number_address);

    }

    public void query(View v){

    }
}
