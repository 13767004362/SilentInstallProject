package com.xingen.slientinstalllib;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

/**
 * Created by ${xinGen} on 2018/2/2.
 */

public class MainExecutor implements Executor {
    private final Handler handler;

    public MainExecutor() {
        handler=new Handler(Looper.getMainLooper());
    }

    @Override
    public void execute(@NonNull Runnable command) {
        handler.post(command);
    }
}
