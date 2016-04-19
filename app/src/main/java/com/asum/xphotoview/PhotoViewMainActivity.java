package com.asum.xphotoview;

import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.asum.xphotoview.interfaces.PhotoViewLongClickCallBack;
import com.asum.xphotoview.interfaces.PhotoViewScaleCallBack;
import com.asum.xphotoview.utils.XPhotoView;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;

public class PhotoViewMainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view_main);

        XPhotoView xPhotoView = (XPhotoView) findViewById(R.id.activity_main_xphotoview);
        xPhotoView.setLongClickCallBack(new PhotoViewLongClickCallBack() {
            public void onLongClick(PhotoView photoView) {
                Log.i("XJW", "long click");
            }
        });

        xPhotoView.setOnScaleChangeListener(new PhotoViewScaleCallBack() {
            public void onPhotoTap(int index, View view, float x, float y) {
                Log.i("XJW", index + "：" + x + "--" + y);
            }

            public void onOutSide() {
                Log.i("XJW", "外面");
            }

            public void onMatrixChanged(int index, float scale, RectF rect) {
                Log.i("XJW", index + "：" + scale + "--" + rect);
            }
        });

        ArrayList<Integer> resIds = new ArrayList<Integer>();
        resIds.add(R.drawable.wallpaper);
        resIds.add(R.drawable.wallpaper);
        resIds.add(R.drawable.wallpaper);
        resIds.add(R.drawable.wallpaper);
        resIds.add(R.drawable.wallpaper);
        resIds.add(R.drawable.wallpaper);
        resIds.add(R.drawable.wallpaper);

        xPhotoView.showFromResIds(resIds, true);
    }
}
