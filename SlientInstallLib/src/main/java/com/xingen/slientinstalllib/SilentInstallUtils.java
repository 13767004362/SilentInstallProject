package com.xingen.slientinstalllib;

import android.content.Context;
import android.content.pm.IPackageInstallObserver;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.xingen.slientinstalllib.constants.InstallConstants;
import com.xingen.slientinstalllib.executor.MainExecutor;
import com.xingen.slientinstalllib.listener.InstallResultListener;
import com.xingen.slientinstalllib.observer.PackageInstallObserver;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by ${xinGen} on 2018/2/2.
 * <p>
 * 备注：
 * 1. android.uid.system
 * 2. 系统签名
 */

public class SilentInstallUtils {
    private static final MainExecutor mainExecutor = new MainExecutor();

    private SilentInstallUtils() {

    }

    /**
     *   反射通过获取到contextIml
     *    获取到 ApplicationPackageManager
     * @param context
     * @param packageURI
     * @param observer
     * @param flags
     * @param installerPackageName
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private static void install(Context context, Uri packageURI, IPackageInstallObserver observer, int flags, String installerPackageName)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //contextImpl 返回的是ApplicationPackageManager对象
        PackageManager packageManager = context.getPackageManager();
        Class<?> packageManagerClass = packageManager.getClass();
        Method installPackageMethod = packageManagerClass.getDeclaredMethod("installPackage", Uri.class, IPackageInstallObserver.class, int.class, String.class);
        if (installPackageMethod != null) {
            installPackageMethod.setAccessible(true);
            installPackageMethod.invoke(packageManager, packageURI, observer, flags, installerPackageName);
        }
    }


    /**
     * sdcard 上 替换安装
     *
     * @param path
     * @param installResultListener
     */
    private static void runReplaceInstall(Context context,final String path, final InstallResultListener installResultListener) {
        int flag = InstallConstants.INSTALL_REPLACE_EXISTING;
        runInstall(context,path, flag, installResultListener);
    }

    /**
     * sdcard 上安装
     *
     * @param path
     * @param installResultListener
     */
    private static void runExternalInstall(Context context,final String path, final InstallResultListener installResultListener) {
        int flag = InstallConstants.INSTALL_EXTERNAL;
        runInstall(context,path, flag, installResultListener);
    }

    /**
     * 手机内部安装
     *
     * @param path
     * @param installResultListener
     */
    private static void runInternalInstall(Context context,final String path, final InstallResultListener installResultListener) {
        int flag = InstallConstants.INSTALL_INTERNAL;
        runInstall(context,path, flag, installResultListener);
    }

    /**
     * 安装时耗时的，需要异步炒作
     *
     * @param path
     * @param installResultListener
     */
    private static void runInstall(final Context context, final String path, final int flag, final InstallResultListener installResultListener) {
        final PackageInstallObserver packageInstallObserver = new PackageInstallObserver(mainExecutor, installResultListener);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    /**
                     *  install方法
                     *  1.uri:安装文件路径
                     *  2.observer:观察者，安装成功还是失败
                     *  3.flags:标记状态
                     */
                    Uri uri = Uri.fromFile(new File(path));
                    install(context, uri, packageInstallObserver, flag, null);
                } catch (Exception e) {
                    packageInstallObserver.failure(e.getMessage());
                }
            }
        }).start();
    }


}
