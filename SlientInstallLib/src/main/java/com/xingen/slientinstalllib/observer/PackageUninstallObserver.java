package com.xingen.slientinstalllib.observer;

import android.content.pm.IPackageDeleteObserver;
import android.os.IBinder;
import android.os.RemoteException;

import com.xingen.slientinstalllib.executor.MainExecutor;
import com.xingen.slientinstalllib.listener.UninstallResultListener;

/**
 * Created by ${xinGen} on 2018/3/8.
 */

public class PackageUninstallObserver  extends IPackageDeleteObserver.Stub {
    private MainExecutor mainExecutor;
    private UninstallResultListener uninstallResultListener;
    private String packageName;
    public PackageUninstallObserver(String packageName,MainExecutor mainExecutor,UninstallResultListener uninstallResultListener) {
        this.packageName=packageName;
        this.mainExecutor = mainExecutor;
        this.uninstallResultListener=uninstallResultListener;
    }
    @Override
    public void packageDeleted(boolean succeeded) throws RemoteException {
        if (this.uninstallResultListener==null){
            return;
        }
        if (succeeded){
          this.uninstallResultListener.uninstallSuccess(this.packageName);
        }else{
            this.uninstallResultListener.uninstallSuccess(this.packageName);
        }
    }
}
