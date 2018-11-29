package com.xingen.slientinstalllib.observer;

import android.content.pm.IPackageInstallObserver;
import android.os.RemoteException;

import com.xingen.slientinstalllib.constants.InstallConstants;
import com.xingen.slientinstalllib.executor.MainExecutor;
import com.xingen.slientinstalllib.listener.InstallResultListener;

/**
 * Created by ${xinGen} on 2018/2/2.
 * <p>
 * 包安装状态的监听
 */

public class PackageInstallObserver extends IPackageInstallObserver.Stub {
    private MainExecutor mainExecutor;
    private InstallResultListener installResultListener;

    public PackageInstallObserver(MainExecutor mainExecutor, InstallResultListener installResultListener) {
        this.mainExecutor = mainExecutor;
        this.installResultListener = installResultListener;
    }
    @Override
    public void packageInstalled(final String packageName, int returnCode) throws RemoteException {
        if (installResultListener == null) {
            return;
        }
        if (checkInstallSuccess(returnCode)) {
               success(packageName);
        } else {
            failure(packageName);
        }
    }
    public  void success(final String packageName){
        this.mainExecutor.execute(new Runnable() {
            @Override
            public void run() {
                installResultListener.installSuccess(packageName);
            }
        });
    }
    public  void failure(final String packageName){
        this.mainExecutor.execute(new Runnable() {
            @Override
            public void run() {
                installResultListener.installFailure(packageName);
            }
        });
    }

    /**
     * 检查是否安装成功
     * @param returnCode
     * @return
     */
    private boolean checkInstallSuccess(int returnCode) {
        return returnCode == InstallConstants.INSTALL_SUCCEEDED ? true : false;
    }
}
