package com.xingen.slientinstalllib.listener;

/**
 * Created by ${xinGen} on 2018/3/8.
 */

public interface UninstallResultListener {
    void uninstallSuccess(String packageName);
    void uninstallFailure(String packageName);
}
