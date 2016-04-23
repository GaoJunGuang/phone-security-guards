package com.gjg.mobilesafe.engine;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

/**
 * 作者： ${高俊光}
 * 时间： 2016/4/16   17：44.
 */
public class GPSInfoProvider {
    private static Context context;
    LocationManager locationManager;
    private static GPSInfoProvider mGPSInfoProvider;
    private static MyLocationListener listener;

    private GPSInfoProvider() {
    }

    ;

    public static synchronized GPSInfoProvider getInstance(Context context) {
        if (mGPSInfoProvider == null) {
            mGPSInfoProvider = new GPSInfoProvider();
            GPSInfoProvider.context = context;

        }
        return mGPSInfoProvider;

    }

    public String getLocation() {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //locationManager.getAllProviders();
        String provider = getProvider(locationManager);
        //有权限
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(provider, 60000, 50, getListener());

        }
        /*else {
            context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }*/
        SharedPreferences sharedPreferences=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        String location=sharedPreferences.getString("location","");
        return location;
    }

    //停止监听
    public void stopGPSListener() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            locationManager.removeUpdates(getListener());
        }

    }

    private synchronized MyLocationListener getListener(){
        if(listener==null){
            listener=new MyLocationListener();
        }
        return listener;
    }

    private class MyLocationListener implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {
            String latitude="weidu"+ location.getLatitude();
            String longitude="jingdu"+ location.getLongitude();
            SharedPreferences sharedPreferences= context.getSharedPreferences("config", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("location",latitude+"-"+longitude);
            editor.commit();
        }

        //状态 可用<-->不可用
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    /**
     *
     * @param locationManager 位置关联服务
     * @return 最好的位置提供者
     */
    private String getProvider(LocationManager locationManager){
        Criteria criteria=new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
        criteria.setSpeedRequired(true);
        criteria.setCostAllowed(true);
        return locationManager.getBestProvider(criteria,true);
    }




}
