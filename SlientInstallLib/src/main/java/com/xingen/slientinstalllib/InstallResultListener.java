package com.xingen.slientinstalllib;

/**
 * Created by ${xinGen} on 2018/2/2.
 */

public interface InstallResultListener {
    void installSuccess(String packageName);
    void installFailure(String packageName);
}
