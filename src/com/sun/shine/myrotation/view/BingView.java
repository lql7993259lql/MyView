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

public class BingView extends View {
	private Paint mpaint = null;
	
	private Bitmap mBitmap = null;

	
	private Canvas mCanvas = null;

	private int backgroundcolor = 0;
	

	public BingView(Context context, AttributeSet attrs, int defStyleAttr) {

		super(context, attrs, defStyleAttr);
		init(context, attrs, defStyleAttr);
	}

	public BingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs, 0);
	}

	public BingView(Context context) {
		super(context);
		init(context, null, 0);
	}

	public void init(Context context, AttributeSet attrs, int defStyleAttr) {
		mBitmap = null;
		mCanvas = null;
		mpaint = null;
		mpaint = new Paint();
		mpaint.setColor(Color.BLUE);
		mpaint.setStyle(Paint.Style.STROKE);  //�������� STROKE���� FILL ʵ��
		mpaint.setStrokeJoin(Paint.Join.ROUND); //���ʽ�Ǣ������ ��Ӱ����ε��ǵ�������
		mpaint.setStrokeCap(Paint.Cap.ROUND); //���ʱ�ˢ���� ��Ӱ�컭�ʵ�ʼĩ��
		mpaint.setDither(true);            //������
		mpaint.setStrokeWidth(8);

		mpaint.setAntiAlias(true);//�����

		setBackgroundColor(Color.TRANSPARENT);// ���ñ���ͼƬΪ͸��

	

		backgroundcolor = Color.CYAN;//��ɫ 
		

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		int BmpW = this.getWidth();
		int BmpH = this.getHeight();

		int space = BmpW / 7;
		int arcw = BmpW / 6;
		int rectw = 0;
		int recth = 0;



		if (null == mBitmap) {
			mCanvas = null;
			mBitmap = Bitmap.createBitmap(BmpW, BmpH, Config.ARGB_8888);
			mCanvas = new Canvas(mBitmap);
		}

		Rect rt = new Rect();

		//���ư�Բ��
		rt.left = 0 + arcw / 2;
		rt.right = BmpW - arcw / 2;
		rt.top = recth / 2;
		rt.bottom = BmpH / 2 + space + arcw;
		drawArc(rt, 0, 110, arcw, false);

		

		mpaint.setColor(backgroundcolor);

		canvas.drawBitmap(mBitmap, 0, 0, mpaint);

		
	}

	private void drawArc(Rect rc, float startangle, float endangle,
			int arcwidth, boolean useCenter) {
		if ((null == mpaint) || (null == mCanvas)) {
			return;
		}
		mpaint.setStyle(Paint.Style.STROKE);
		mpaint.setStrokeWidth(arcwidth);

		mpaint.setColor(backgroundcolor);
		mCanvas.drawArc(new RectF(rc), startangle, endangle, useCenter, mpaint);


		mpaint.setStyle(Paint.Style.FILL);

	}

	
}
