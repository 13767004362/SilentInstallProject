package com.xingen.slientinstalllib;

import android.content.pm.PackageManager;

/**
 * Created by ${xinGen} on 2018/2/2.
 *
 * install flags 状态，详见PackageManager
 */

public class InstallConstants {
    //sdcard中替换安装
    public static final int INSTALL_REPLACE_EXISTING = 0X00000002;

    //安装成功
    public static final int INSTALL_SUCCEEDED = 1;
    /**
     * sdcard安装
     */
    public static final int INSTALL_EXTERNAL = 0x00000008;
    /**
     * 手机内部存储
     */
    public static final int INSTALL_INTERNAL = 0x00000010;

}
