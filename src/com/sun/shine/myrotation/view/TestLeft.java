package com.sun.shine.myrotation.view;

import com.sun.shine.myrotation.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class TestLeft extends View {
	private static final String TAG = TestLeft.class.getSimpleName();
	private Paint mpaint = null;
	
	private Bitmap mBitmap = null;
	
	
	private Canvas mCanvas = null;
	private Canvas mCanvasbg = null;
	

	private PorterDuff.Mode PorterDuffMode = PorterDuff.Mode.CLEAR;
	
	
	private int backgroundcolor = 0;


	private static Bitmap mFreeBitmap;

	static {
		mFreeBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
	}

	public TestLeft(Context context, AttributeSet attrs, int defStyleAttr) {

		super(context, attrs, defStyleAttr);
		init(context, attrs, defStyleAttr);
	}

	public TestLeft(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs, 0);
	}

	public TestLeft(Context context) {
		super(context);
		init(context, null, 0);
	}

	public void init(Context context, AttributeSet attrs, int defStyleAttr) {
		mBitmap = null;


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


		PorterDuffMode = PorterDuff.Mode.CLEAR;

		backgroundcolor = Color.CYAN;//青色 

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		int BmpW = this.getWidth();
		int BmpH = this.getHeight();
		
		mCanvas = null;
//		mBitmap = Bitmap.createBitmap();
		mCanvas = new Canvas(mBitmap);
		mpaint.setColor(backgroundcolor);

		canvas.drawBitmap(mBitmap, 0, 0, mpaint);

		

	}

	

	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 判断按了是不是透明区域
		int x = (int) event.getX();
		int y = (int) event.getY();
		if (IsTouchPoint(x, y)) {
		
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				return super.onTouchEvent(event);
			}else{
				Log.i(TAG, "单击了无效区域...");
			}
		}else{
			Log.i(TAG, "单击了无效区域...");
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
		

	}

	
}
