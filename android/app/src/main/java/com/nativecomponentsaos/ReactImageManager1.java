package com.nativecomponentsaos;

import android.content.Loader;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.image.ImageResizeMode;
import com.facebook.react.views.image.ReactImageView;

import org.jetbrains.annotations.NotNull;

import java.net.URL;

public class ReactImageManager1 extends SimpleViewManager<ReactImageView> {

    public static final String REACT_CLASS = "RCTImageView1";
    private @Nullable
    ReactApplicationContext mCallerContext = null;

    private ImgStartListener imgStartListener;

    private interface ImgStartListener {
        void startLoading(String imgUrl);
    }


    public ReactImageManager1(@NotNull ReactApplicationContext context) {
        this.mCallerContext = context;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }
    /* Method which sets the source from React Native */
    @ReactProp(name = "src")
    public void setSrc(ReactImageView view, String uri) {
        imgStartListener.startLoading(uri);
    }

    @Override
    protected ReactImageView createViewInstance(ThemedReactContext reactContext) {
        final ReactImageView reactImageView = new ReactImageView(reactContext, Fresco.newDraweeControllerBuilder(), null, mCallerContext);
        final Handler handler = new Handler();
        imgStartListener = new ImgStartListener() {
            @Override
            public void startLoading(final String imgUrl) {
                startDownloading(imgUrl, handler, reactImageView);

            }
        };

        return reactImageView;
    }

    private void startDownloading(final String imgUrl, final Handler handler, final ReactImageView reactImageView) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(imgUrl);
                    final Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    setImage(bmp, handler, reactImageView);
                } catch (Exception e) {
                    Log.e("ReactImageManager", "Error : " + e.getMessage());
                }
            }
        }).start();
    }

    private void setImage(final Bitmap bmp, Handler handler, final ReactImageView reactImageView) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                reactImageView.setImageBitmap(bmp);
            }
        });
    }

    @ReactProp(name = "borderRadius", defaultFloat = 0f)
    public void setBorderRadius(ReactImageView view, float borderRadius) {
        view.setBorderRadius(borderRadius);
    }

    @ReactProp(name = ViewProps.RESIZE_MODE)
    public void setResizeMode(ReactImageView view, @Nullable String resizeMode) {
        view.setScaleType(ImageResizeMode.toScaleType(resizeMode));
    }
//
//    public ReactImageView createViewInstance(ThemedReactContext context) {
//        return new ReactImageView(context, Fresco.newDraweeControllerBuilder(), null, mCallerContext);
//    }

}