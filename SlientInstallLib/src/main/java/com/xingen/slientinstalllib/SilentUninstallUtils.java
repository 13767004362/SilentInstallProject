package com.xingen.slientinstalllib;

import android.content.Context;
import android.content.pm.PackageManager;

import com.xingen.slientinstalllib.executor.MainExecutor;
import com.xingen.slientinstalllib.listener.UninstallResultListener;
import com.xingen.slientinstalllib.observer.PackageUninstallObserver;

import java.lang.reflect.Method;

/**
 * Created by ${xinGen} on 2018/3/8.
 *
 * 静默卸载
 *
 * PackageManager下的deletePackage();
 *
 */

public class SilentUninstallUtils {
    private static final MainExecutor mainExecutor = new MainExecutor();

    /**
     * 采用反射方式调用到PackageManager下的deletePackage();
     * @param context
     * @param packageName
     * @param resultListener
     */
    public static  void runUninstall(Context context,String packageName, UninstallResultListener resultListener){
      try {
          PackageManager packageManager=context.getPackageManager();
          Method[] methods = packageManager != null ? packageManager.getClass().getDeclaredMethods() : null;
          Method deletePackageMethod=null;
          if (methods!=null&&methods.length>0){
              for (Method method:methods){
                  if (method.getName().toString().equals("deletePackage")){
                      deletePackageMethod=method;
                      break;
                  }
              }
          }
          if (deletePackageMethod!=null){
                deletePackageMethod.setAccessible(true);
                deletePackageMethod.invoke(packageManager,packageName,new PackageUninstallObserver(packageName,mainExecutor,resultListener),0);
          }
      }catch (Exception e){
          e.printStackTrace();
      }
    }
}
