package com.asum.xphotoview.adapter;

import android.graphics.Bitmap;
import android.graphics.RectF;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.asum.xphotoview.interfaces.IPagerAdapter;
import com.asum.xphotoview.interfaces.PhotoLoaderCallBack;
import com.asum.xphotoview.interfaces.PhotoViewLongClickCallBack;
import com.asum.xphotoview.interfaces.PhotoViewScaleCallBack;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher.OnMatrixChangedListener;
import uk.co.senab.photoview.PhotoViewAttacher.OnPhotoTapListener;

public class PhotoViewAdapter extends PagerAdapter implements IPagerAdapter {
	private ArrayList<Integer> resIds;
	private ArrayList<Bitmap> bitmaps;
	private ArrayList<String> urls;

	private int index;
	private boolean longClickable;
	private PhotoViewLongClickCallBack longClickCallBack;
	private PhotoLoaderCallBack loaderCallBack;
	private PhotoViewScaleCallBack scaleCallBack;

	public void setResIds(ArrayList<Integer> resIds) {
		this.resIds = resIds;
	}

	public void setBitmaps(ArrayList<Bitmap> bitmaps) {
		this.bitmaps = bitmaps;
	}

	public void setUrls(ArrayList<String> urls, PhotoLoaderCallBack loaderCallBack) {
		this.urls = urls;
		this.loaderCallBack = loaderCallBack;
	}

	public void setLongClickable(boolean longClickable, PhotoViewLongClickCallBack longClickCallBack) {
		this.longClickable = longClickable;
		this.longClickCallBack = longClickCallBack;
	}

	public void setOnScaleChangeListener(PhotoViewScaleCallBack scaleCallBack) {
		this.scaleCallBack = scaleCallBack;
	}

	public int getCount() {
		if (resIds != null) {
			return resIds.size();
		} else if (bitmaps != null) {
			return bitmaps.size();
		} else if (urls != null) {
			return urls.size();
		} else {
			return 0;
		}
	}

	public View instantiateItem(ViewGroup container, int position) {
		index = position;

		final PhotoView photoView = new PhotoView(container.getContext());
		photoView.setOnPhotoTapListener(new OnPhotoTapListener() {
			public void onPhotoTap(View view, float x, float y) {
				if (scaleCallBack != null) {
					scaleCallBack.onPhotoTap(index, view, x, y);
				}
			}

			public void onOutsidePhotoTap() {
				if (scaleCallBack != null) {
					scaleCallBack.onOutSide();
				}
			}
		});

		photoView.setOnMatrixChangeListener(new OnMatrixChangedListener() {
			public void onMatrixChanged(RectF rect) {
				if (scaleCallBack != null) {
					scaleCallBack.onMatrixChanged(index, photoView.getScale(), rect);
				}
			}
		});

		if (resIds != null) {
			photoView.setImageResource(resIds.get(position));
		} else if (bitmaps != null) {
			photoView.setImageBitmap(bitmaps.get(position));
		} else if (urls != null) {
			loaderCallBack.pleaseLoadImageView(photoView, urls.get(position));
		}
		photoView.setLongClickable(longClickable);
		photoView.setOnLongClickListener(new OnLongClickListener() {
			public boolean onLongClick(View v) {
				if (longClickCallBack != null) {
					longClickCallBack.onLongClick((PhotoView) v);
				}
				return false;
			}
		});

		container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		return photoView;
	}

	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}
}
