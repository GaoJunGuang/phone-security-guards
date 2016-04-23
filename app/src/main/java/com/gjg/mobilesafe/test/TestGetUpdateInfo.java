package com.gjg.mobilesafe.test;

import android.test.AndroidTestCase;

import com.gjg.mobilesafe.R;
import com.gjg.mobilesafe.domain.UpdateInfo;
import com.gjg.mobilesafe.engine.UpdateInfoService;

/**
 * 作者： ${高俊光}
 * 时间： 2015/11/2   21：30.
 */
public class TestGetUpdateInfo extends AndroidTestCase {
    public void testGetUpdateInfo() throws Exception{
        UpdateInfoService service=new UpdateInfoService(getContext());
        UpdateInfo info=service.getUpdateInfo(R.string.updataurl);
        assertEquals("2.0",info.getVersion());
    }
}
