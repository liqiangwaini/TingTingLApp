package com.tingtingfm.lapp.a;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class TTApplication extends Application {
    private static final String TAG = "TTFM/TTApplication";

    private static TTApplication instance = null;

    private boolean isUpdating;

    public static Context getAppContext() {
        return instance;
    }

    public static Resources getAppResources() {
        if (instance == null)
            return null;
        return instance.getResources();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        isUpdating = false;

        initImageLoader(this);
    }

    public void onLowMemory() {
        super.onLowMemory();
    }

    public boolean isUpdating() {
        return isUpdating;
    }

    public void setUpdating(boolean isUpdating) {
        this.isUpdating = isUpdating;
    }

    private static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCacheSize(50 * 1024 * 1024)
                .writeDebugLogs()
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }
}
