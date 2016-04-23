package com.gjg.mobilesafe.engine;

import android.util.Xml;

import com.gjg.mobilesafe.domain.UpdateInfo;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;

/**
 * 作者： ${高俊光}
 * 时间： 2015/11/1   17：25.
 */
public class UpdateInfoParser {

    public static UpdateInfo getUpdateInfo(InputStream is) throws Exception{
        XmlPullParser parser= Xml.newPullParser();
        UpdateInfo updateInfo=new UpdateInfo();
        parser.setInput(is,"utf-8");
        //开始解析资源 定位到资源头部
        int type=parser.getEventType();
        while (type!=XmlPullParser.END_DOCUMENT){
            switch (type){
                case XmlPullParser.START_TAG:
                    if("version".equals(parser.getName())){
                        String version=parser.nextText();
                        updateInfo.setVersion(version);

                    }else if("description".equals(parser.getName())){
                        String description=parser.nextText();
                        updateInfo.setDescription(description);

                    }else if("apkurl".equals(parser.getName())){
                        String apkurl=parser.nextText();
                        updateInfo.setApkurl(apkurl);

                    }
                    break;
            }
            type=parser.next();
        }
        return updateInfo;
    }
}
