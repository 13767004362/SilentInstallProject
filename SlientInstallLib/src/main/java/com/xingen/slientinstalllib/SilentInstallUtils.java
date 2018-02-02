package com.xingen.slientinstalllib;

import android.content.pm.IPackageInstallObserver;
import android.content.pm.IPackageManager;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by ${xinGen} on 2018/2/2.
 *
 * 备注：
 * 1. android.uid.system
 * 2. 系统签名
 */

public class SilentInstallUtils {
    private static final MainExecutor mainExecutor = new MainExecutor();
    private SilentInstallUtils(){

    }
    /**
     * 通过反射获取到IPackageManager类
     * IPackageManager类： 安装类
     *
     * @return
     */
    private static IPackageManager createIPackageManager() {
        try {
            Class<?> clazz = Class.forName("android.os.ServiceManager");
            Method method_getService = clazz.getMethod("getService", String.class);
            IBinder binder = (IBinder) method_getService.invoke(null, "package");
            IPackageManager pm = IPackageManager.Stub.asInterface(binder);
            return pm;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * sdcard 上 替换安装
     * @param path
     * @param installResultListener
     */
    private static  void runReplaceInstall(final String path, final InstallResultListener installResultListener){
        int flag = InstallConstants.INSTALL_REPLACE_EXISTING;
        runInstall(path,flag,installResultListener);
    }

    /**
     * sdcard 上安装
     * @param path
     * @param installResultListener
     */
    private static  void runExternalInstall(final String path, final InstallResultListener installResultListener){
        int flag = InstallConstants.INSTALL_EXTERNAL;
        runInstall(path,flag,installResultListener);
    }
    /**
     * 手机内部安装
     * @param path
     * @param installResultListener
     */
    private static  void runInternalInstall(final String path, final InstallResultListener installResultListener){
        int flag = InstallConstants.INSTALL_INTERNAL;
        runInstall(path,flag,installResultListener);
    }
    /**
     * 安装时耗时的，需要异步炒作
     *
     * @param path
     * @param installResultListener
     */
    private static void runInstall(final String path, final  int flag, final InstallResultListener installResultListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                IPackageManager iPackageManager = createIPackageManager();
                if (iPackageManager == null) {
                    return;
                }
                try {
                    /**
                     *  install方法
                     *  1.uri:安装文件路径
                     *  2.observer:观察者，安装成功还是失败
                     *  3.flags:标记状态
                     *  4.installer: 整个路径
                     */
                    Uri uri = Uri.fromFile(new File(path));
                    PackageInstallObserver packageInstallObserver = new PackageInstallObserver(mainExecutor, installResultListener);
                    String installer = new File(path).getPath();
                    iPackageManager.installPackage(uri, packageInstallObserver, flag, installer);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
