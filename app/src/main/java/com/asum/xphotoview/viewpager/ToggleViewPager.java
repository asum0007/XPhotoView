package com.asum.xphotoview.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ToggleViewPager extends ViewPager {
	private boolean isLocked;

	public ToggleViewPager(Context context) {
		super(context);
		isLocked = false;
	}

	public ToggleViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		isLocked = false;
	}

	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (!isLocked) {
			try {
				return super.onInterceptTouchEvent(ev);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public boolean onTouchEvent(MotionEvent event) {
		return !isLocked && super.onTouchEvent(event);
	}

	public void switchLock() {
		isLocked = !isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public boolean isLocked() {
		return isLocked;
	}
}
