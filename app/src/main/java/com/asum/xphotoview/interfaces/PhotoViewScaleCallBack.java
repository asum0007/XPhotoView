package com.asum.xphotoview.interfaces;

import android.graphics.RectF;
import android.view.View;

public interface PhotoViewScaleCallBack {
	public void onPhotoTap(int index, View view, float x, float y);

	public void onOutSide();

	public void onMatrixChanged(int index, float scale, RectF rect);
}
