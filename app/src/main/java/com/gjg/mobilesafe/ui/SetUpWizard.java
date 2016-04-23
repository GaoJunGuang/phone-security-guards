package com.gjg.mobilesafe.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.gjg.mobilesafe.R;

/**
 * 作者： ${高俊光}
 * 时间： 2015/11/25   22：58.
 */
public class SetUpWizard extends Activity implements OnClickListener {
    private Button bt_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setupwizard1);
        bt_next = (Button) this.findViewById(R.id.bt_next);
        bt_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_next:
                Intent intent = new Intent(this,SetUpWizard2.class);
                //一定要把当前的activity从任务栈里面移除
                finish();
                startActivity(intent);
                //设置activity切换时候的动画效果
                overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
                break;

        }
    }
}
