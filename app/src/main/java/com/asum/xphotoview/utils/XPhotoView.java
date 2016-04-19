package com.asum.xphotoview.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.asum.xphotoview.adapter.PhotoViewAdapter;
import com.asum.xphotoview.interfaces.IPagerAdapter;
import com.asum.xphotoview.interfaces.PhotoLoaderCallBack;
import com.asum.xphotoview.interfaces.PhotoViewLongClickCallBack;
import com.asum.xphotoview.interfaces.PhotoViewScaleCallBack;
import com.asum.xphotoview.viewpager.ToggleViewPager;

import java.util.ArrayList;

public class XPhotoView extends RelativeLayout {
	private Context context;
	private PhotoViewLongClickCallBack longClickCallBack;
	private PhotoViewScaleCallBack scaleCallBack;
	private ToggleViewPager viewPager;

	public XPhotoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;

		initialize();
	}

	public XPhotoView(Context context) {
		super(context);
		this.context = context;

		initialize();
	}

	private void initialize() {
		viewPager = new ToggleViewPager(context);
		viewPager.setBackgroundColor(Color.argb(0, 0, 0, 0));
		this.addView(viewPager);
	}

	public void setLongClickCallBack(PhotoViewLongClickCallBack longClickCallBack) {
		this.longClickCallBack = longClickCallBack;
	}

	public void setOnScaleChangeListener(PhotoViewScaleCallBack scaleCallBack) {
		this.scaleCallBack = scaleCallBack;
	}

	public void showFromResIds(ArrayList<Integer> resIds, boolean longClickable) {
		PhotoViewAdapter adapter = new PhotoViewAdapter();
		adapter.setLongClickable(longClickable, longClickCallBack);
		adapter.setOnScaleChangeListener(scaleCallBack);
		adapter.setResIds(resIds);
		show(adapter);
	}

	public void showFromResId(int resId, boolean longClickable) {
		ArrayList<Integer> resIds = new ArrayList<Integer>();
		resIds.add(resId);
		showFromResIds(resIds, longClickable);
	}

	public void showFromUrls(ArrayList<String> urls, PhotoLoaderCallBack loaderCallBack, boolean longClickable) {
		PhotoViewAdapter adapter = new PhotoViewAdapter();
		adapter.setLongClickable(longClickable, longClickCallBack);
		adapter.setOnScaleChangeListener(scaleCallBack);
		adapter.setUrls(urls, loaderCallBack);
		show(adapter);
	}

	public void showFromUrl(String url, PhotoLoaderCallBack callBack, boolean longClickable) {
		ArrayList<String> urls = new ArrayList<String>();
		urls.add(url);
		showFromUrls(urls, callBack, longClickable);
	}

	public void showFromBitmaps(ArrayList<Bitmap> bitmaps, boolean longClickable) {
		PhotoViewAdapter adapter = new PhotoViewAdapter();
		adapter.setLongClickable(longClickable, longClickCallBack);
		adapter.setOnScaleChangeListener(scaleCallBack);
		adapter.setBitmaps(bitmaps);
		show(adapter);
	}

	public void showFromBitmap(Bitmap bitmap, boolean longClickable) {
		ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
		bitmaps.add(bitmap);
		showFromBitmaps(bitmaps, longClickable);
	}

	public void setCurrentItem(int index) {
		viewPager.setCurrentItem(index);
	}

	public int getCurrentItem() {
		return viewPager.getCurrentItem();
	}

	public ViewPager getViewPager() {
		return viewPager;
	}

	private void show(IPagerAdapter adapter) {
		viewPager.setAdapter((PagerAdapter) adapter);
	}
}
