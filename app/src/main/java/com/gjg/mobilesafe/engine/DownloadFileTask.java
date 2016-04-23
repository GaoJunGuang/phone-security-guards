package com.gjg.mobilesafe.engine;

import android.app.ProgressDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 作者： ${高俊光}
 * 时间： 2015/11/3   21：40.
 */
public class DownloadFileTask {
    /**
     *
     * @param path 服务器路径
     * @param filepath 本地文件路径
     * @return 本地文件对象
     * @throws Exception
     */
    public static File getFile(String path,String filepath,ProgressDialog pd) throws Exception{
        URL url=new URL(path);
        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        if(connection.getResponseCode()==200){
            int total =  connection.getContentLength();
            pd.setMax(total);
            InputStream inputStream=connection.getInputStream();
            File file=new File(filepath);
            FileOutputStream fos=new FileOutputStream(file);
            byte[] buffer=new byte[1024];
            int len=0;
            int process = 0;
            while((len=inputStream.read(buffer))!=-1){
                fos.write(buffer,0,len);
                process +=len;
                pd.setProgress(process);
                Thread.sleep(50);
            }
            fos.flush();
            fos.close();
            inputStream.close();
            return file;

        }

        return null;
    }
}
