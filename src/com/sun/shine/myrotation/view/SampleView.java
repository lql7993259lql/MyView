package com.sun.shine.myrotation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class SampleView extends View {
	private Paint mPaints;
	private boolean mUseCenters;
	private RectF mOvals;
	private int mwidth,mheight;

	
	public SampleView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs, defStyleAttr);
	}

	public SampleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs, 0);
	}

	public SampleView(Context context) {
		super(context);
		init(context, null, 0);
	}
	
	
	public void init(Context context, AttributeSet attrs, int defStyleAttr) {
	}

	
	

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mwidth = getWidth();
		mheight = getHeight();
		mPaints = new Paint();
		mPaints.setStyle(Paint.Style.STROKE);
		mPaints.setStrokeWidth(mwidth/3);
		mPaints.setColor(0x880000FF);
		mPaints.setAntiAlias(true);//·À¾â³Ý
		mPaints.setDither(true);
		mUseCenters = false;
		
		mOvals = new RectF(mwidth/6, mheight/6, mwidth-mwidth/6, mheight-mheight/6);
		canvas.drawColor(Color.TRANSPARENT);
		
		//»æÖÆµÈ±ÈÀýµÄ»¡Ïß
		mPaints.setColor(Color.BLUE);
		canvas.drawArc(mOvals, 0, 108, mUseCenters, mPaints);
		mPaints.setColor(Color.YELLOW);
		canvas.drawArc(mOvals, 108, 216, mUseCenters, mPaints);
		mPaints.setColor(Color.RED);
		canvas.drawArc(mOvals, 324, 36, mUseCenters, mPaints);
		mPaints.setColor(Color.BLACK);
		
		
		//»æÖÆÎÄ×Ö
		mPaints.setStrokeWidth(1);
		mPaints.setStyle(Paint.Style.FILL);
		mPaints.setTextSize(mwidth/12);
		canvas.drawText("200M", (mwidth/3)+((mwidth/3)/4),(mheight/2)+((mheight/3)/12), mPaints);
		
	}
	
	
	
	
}




//@Override
//protected void onDraw(Canvas canvas) {
//	super.onDraw(canvas);
//	mwidth = getWidth();
//	mheight = getHeight();
//	mPaints = new Paint();
//	mPaints.setStyle(Paint.Style.STROKE);
//	mPaints.setStrokeWidth(50);
//	mPaints.setColor(0x880000FF);
//	mPaints.setAntiAlias(true);//·À¾â³Ý
//	mPaints.setDither(true);
//	mUseCenters = false;
//	
//	mOvals = new RectF(50, 50, 150, 150);
//	canvas.drawColor(Color.WHITE);
//	mPaints.setColor(Color.BLUE);
//	canvas.drawArc(mOvals, 270, 90, mUseCenters, mPaints);
//	mPaints.setColor(Color.YELLOW);
//	canvas.drawArc(mOvals, 0, 180, mUseCenters, mPaints);
//	mPaints.setColor(Color.RED);
//	canvas.drawArc(mOvals, 180, 90, mUseCenters, mPaints);
//	mPaints.setColor(Color.BLACK);
//	mPaints.setStrokeWidth(1);
//	canvas.drawText("200M", 90,100, mPaints);
//	invalidate();
//}