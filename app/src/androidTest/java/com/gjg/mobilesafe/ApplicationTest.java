package com.gjg.mobilesafe;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.gjg.mobilesafe.domain.UpdateInfo;
import com.gjg.mobilesafe.engine.UpdateInfoService;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    public ApplicationTest() {
        super(Application.class);
    }

    public void testGetUpdateInfo() throws Exception{
        UpdateInfoService service=new UpdateInfoService(getContext());
        UpdateInfo info=service.getUpdateInfo(R.string.updataurl);
        assertEquals("2.0", info.getVersion());
    }



}