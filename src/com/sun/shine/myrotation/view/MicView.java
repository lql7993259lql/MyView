package com.sun.shine.myrotation.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MicView extends View {
	private Paint mpaint = null;
	
	private Bitmap mBitmap = null;
	private Bitmap mbackg = null;
	
	private Canvas mCanvas = null;
	private Canvas mCanvasbg = null;
	
	private TextView mView = null;
	
	private int FillHeight = 0;
	
	private boolean PorterFlag = false;
	private PorterDuff.Mode PorterDuffMode = PorterDuff.Mode.CLEAR;
	
	
	private int backgroundcolor = 0;
	private int foregroundcolor = 0;

	private static Bitmap mFreeBitmap;

	static {
		mFreeBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
	}

	public MicView(Context context, AttributeSet attrs, int defStyleAttr) {

		super(context, attrs, defStyleAttr);
		init(context, attrs, defStyleAttr);
	}

	public MicView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs, 0);
	}

	public MicView(Context context) {
		super(context);
		init(context, null, 0);
	}

	public void init(Context context, AttributeSet attrs, int defStyleAttr) {
		mBitmap = null;
		mbackg = null;

		mCanvas = null;
		mCanvasbg = null;
		mpaint = null;
		mpaint = new Paint();
		mpaint.setColor(Color.BLUE);
		mpaint.setStyle(Paint.Style.STROKE);  //画笔类型 STROKE空心 FILL 实心
		mpaint.setStrokeJoin(Paint.Join.ROUND); //画笔接洽点类型 如影响矩形但角的外轮廓
		mpaint.setStrokeCap(Paint.Cap.ROUND); //画笔笔刷类型 如影响画笔但始末端
		mpaint.setDither(true);            //防抖动
		mpaint.setStrokeWidth(8);

		mpaint.setAntiAlias(true);//防锯齿

		setBackgroundColor(Color.TRANSPARENT);// 设置背景图片为透明

		FillHeight = 0;
		PorterFlag = false;

		PorterDuffMode = PorterDuff.Mode.CLEAR;

		backgroundcolor = Color.CYAN;//青色 
		foregroundcolor = Color.BLUE;//蓝色

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		int BmpW = this.getWidth();
		int BmpH = this.getHeight();
System.err.println("BmpW="+BmpW+",BmpH="+BmpH);
		int space = BmpW / 7;
		int arcw = BmpW / 6;
		int rectw = 0;
		int recth = 0;

		if (null == mbackg) {
			mCanvasbg = null;
			mbackg = Bitmap.createBitmap(BmpW, BmpH, Bitmap.Config.ARGB_8888);
			mCanvasbg = new Canvas(mbackg);
		}

		if (null == mBitmap) {
			mCanvas = null;
			mBitmap = Bitmap.createBitmap(BmpW, BmpH, Config.ARGB_8888);
			mCanvas = new Canvas(mBitmap);
		}

		Rect rt = new Rect();
		rt.left = space + arcw;
		rt.right = BmpW - space - arcw;
		rt.top = 0;
		rt.bottom = BmpH / 2;

		rectw = rt.width();
		recth = rt.height();

		drawRoundRect(rt, 26, 26);

		// 绘制圆柱（center）
		rt.left = BmpW / 2 - arcw / 2;
		rt.right = BmpW / 2 + arcw / 2;
		rt.top = BmpH / 2 + space + arcw;
		rt.bottom = BmpH;
		drawRoundRect(rt, 5, 5);

		// 绘制衡柱
		rt.left = BmpW / 6;
		rt.right = BmpW - BmpW / 6;
		rt.top = BmpH - arcw;
		rt.bottom = BmpH;
		drawRoundRect(rt, 10, 10);

		
		//绘制半圆形
		rt.left = 0 + arcw / 2;
		rt.right = BmpW - arcw / 2;
		rt.top = recth / 2;
		rt.bottom = BmpH / 2 + space + arcw;
		drawArc(rt, 0, 180, arcw, false);

		// 绘制圆柱（left）
		rt.left = 0;
		rt.right = arcw;
		rt.top = recth / 2;
		rt.bottom = recth;
		drawRoundRect(rt, 10, 10);

		// 绘制圆柱（right）
		rt.left = BmpW - arcw;
		rt.right = BmpW;
		rt.top = recth / 2;
		rt.bottom = recth;
		drawRoundRect(rt, 10, 10);

		mpaint.setColor(backgroundcolor);

		canvas.drawBitmap(mBitmap, 0, 0, mpaint);

		if (FillHeight > 0) {
			if (PorterFlag) {
				mpaint.setXfermode(new PorterDuffXfermode(PorterDuffMode));
			}
			int mm = (BmpH * FillHeight) / 100;

			Bitmap mSubbackg = Bitmap.createBitmap(mbackg, 0, BmpH - mm, BmpW,
					mm);
			canvas.drawBitmap(mSubbackg, 0, BmpH - mm, mpaint);

			if (mSubbackg != null && !mSubbackg.isRecycled()) {
				mSubbackg.recycle();
				mSubbackg = null;
			}

		}

	}

	private void drawArc(Rect rc, float startangle, float endangle,
			int arcwidth, boolean useCenter) {
		if ((null == mpaint) || (null == mCanvas) || (null == mCanvasbg)) {
			return;
		}
		mpaint.setStyle(Paint.Style.STROKE);
		mpaint.setStrokeWidth(arcwidth);

		mpaint.setColor(backgroundcolor);
		mCanvas.drawArc(new RectF(rc), startangle, endangle, useCenter, mpaint);

		mpaint.setColor(foregroundcolor);
		mCanvasbg.drawArc(new RectF(rc), startangle, endangle, useCenter,
				mpaint);

		mpaint.setStyle(Paint.Style.FILL);

	}

	private void drawRoundRect(Rect rc, float rx, float ry) {
		if ((null == mpaint) || (null == mCanvas) || (null == mCanvasbg)) {
			return;
		}
		mpaint.setStyle(Paint.Style.FILL);

		mpaint.setColor(backgroundcolor);
		mCanvas.drawRoundRect(new RectF(rc), rx, ry, mpaint);

		mpaint.setColor(foregroundcolor);
		mCanvasbg.drawRoundRect(new RectF(rc), rx, ry, mpaint);

		mpaint.setStyle(Paint.Style.STROKE);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 判断按了是不是透明区域
		int x = (int) event.getX();
		int y = (int) event.getY();
		if (IsTouchPoint(x, y)) {
			if (mView != null) {
				mView.setText("x=" + x + "; y=" + y);
			}
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				return super.onTouchEvent(event);
			}
		}
		if (mView != null) {
			mView.setText("单击了无效区域...");
		}
		return false;
	}

	public boolean IsTouchPoint(int x, int y) {
		if (null == mBitmap) {
			return false;
		}
		if (x < 0 || x > getWidth()) {
			return false;
		}
		if (y < 0 || y > getHeight()) {
			return false;
		}
		int pixel = mBitmap.getPixel(x, y);
		if ((pixel & 0xff000000) != 0) {
			return true;
		} else {
			return false;
		}
	}

	public void DestoryBmp() {

		if (mBitmap != null && !mBitmap.isRecycled()) {
			mCanvas.setBitmap(mFreeBitmap);
			mBitmap.recycle();
			mBitmap = null;
		}
		if (mbackg != null && !mbackg.isRecycled()) {
			mCanvas.setBitmap(mFreeBitmap);
			mbackg.recycle();
			mbackg = null;
		}

	}

	public void SetViewDisplay(TextView view) {

		this.mView = view;
	}

	public void setViewHeight(int mFillHeight) {

		FillHeight = mFillHeight;
		if (FillHeight > 100) {
			FillHeight = 100;
		}
		if (FillHeight < 0) {
			FillHeight = 0;
		}
		invalidate();

	}

	public void setViewPorterFlag(boolean PorterFlag, PorterDuff.Mode mode) {

		this.PorterFlag = PorterFlag;
		this.PorterDuffMode = mode;
		invalidate();

	}

	public void setViewBackgroundColor(int backgroundcolor) {

		this.backgroundcolor = backgroundcolor;
		invalidate();

	}

	public void setViewForegroundColor(int foregroundcolor) {

		this.foregroundcolor = foregroundcolor;
		invalidate();

	}

	public void setViewColor(int backgroundcolor, int foregroundcolor) {
		this.backgroundcolor = backgroundcolor;
		this.foregroundcolor = foregroundcolor;
		invalidate();

	}
}
