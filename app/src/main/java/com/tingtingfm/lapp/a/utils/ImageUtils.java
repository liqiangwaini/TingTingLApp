package com.tingtingfm.lapp.a.utils;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.tingtingfm.lapp.a.R;

/**
 * Created by lenovo on 2015/12/17.
 */
public class ImageUtils {
    static DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.album_default) // 设置图片下载期间显示的图片
            .showImageForEmptyUri(R.drawable.album_default) // 设置图片Uri为空或是错误的时候显示的图片
            .showImageOnFail(R.drawable.album_default) // 设置图片加载或解码过程中发生错误显示的图片
            .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
            .cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
            .displayer(new SimpleBitmapDisplayer())
            .build(); // 构建完成


    public static void displayImage(String uri, ImageView view) {
        int index = 0;
        StringBuilder sb = new StringBuilder();
        if ((index = uri.toLowerCase().indexOf(".jpg")) != -1
                || (index = uri.toLowerCase().indexOf(".gif")) != -1
                || (index = uri.toLowerCase().indexOf(".png")) != -1) {
            sb.append(uri.substring(0, index));
        } else {
            return;
        }

        sb.append("_300x300");
        sb.append(uri.substring(index));

        ImageLoader.getInstance().displayImage(sb.toString(), view, options);
    }
}
