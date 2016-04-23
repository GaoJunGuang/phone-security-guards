package com.gjg.mobilesafe.engine;

import android.content.Context;
import android.util.Log;

import com.gjg.mobilesafe.domain.UpdateInfo;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 作者： ${高俊光}
 * 时间： 2015/11/1   15：33.
 */
public class UpdateInfoService {
    private Context context;

    public UpdateInfoService(Context context) {
        this.context = context;
    }

    /**
     *
     * @param urlId 资源文件对应的id
     * @return 更新信息
     */
    public UpdateInfo getUpdateInfo(int urlId) throws Exception{
        String path=context.getResources().getString(urlId);
        URL url=new URL(path);
        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5000);
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        //下面的执行有问题
        InputStream inputStream=connection.getInputStream();
        Log.e("UpdateInfoService",inputStream+"");
        return UpdateInfoParser.getUpdateInfo(inputStream);
    }

}
