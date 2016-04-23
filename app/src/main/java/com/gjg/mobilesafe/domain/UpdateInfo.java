package com.gjg.mobilesafe.domain;

/**
 * 作者： ${高俊光}
 * 时间： 2015/11/1   15：37.
 */
public class UpdateInfo{
    private String version;
    private String description;
    private String apkurl;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApkurl() {
        return apkurl;
    }

    public void setApkurl(String apkurl) {
        this.apkurl = apkurl;
    }
}
