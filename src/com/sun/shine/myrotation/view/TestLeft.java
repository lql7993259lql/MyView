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
		mpaint.setStyle(Paint.Style.STROKE);  //�������� STROKE���� FILL ʵ��
		mpaint.setStrokeJoin(Paint.Join.ROUND); //���ʽ�Ǣ������ ��Ӱ����ε��ǵ�������
		mpaint.setStrokeCap(Paint.Cap.ROUND); //���ʱ�ˢ���� ��Ӱ�컭�ʵ�ʼĩ��
		mpaint.setDither(true);            //������
		mpaint.setStrokeWidth(8);

		mpaint.setAntiAlias(true);//�����

		setBackgroundColor(Color.TRANSPARENT);// ���ñ���ͼƬΪ͸��


		PorterDuffMode = PorterDuff.Mode.CLEAR;

		backgroundcolor = Color.CYAN;//��ɫ 

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
		// �жϰ����ǲ���͸������
		int x = (int) event.getX();
		int y = (int) event.getY();
		if (IsTouchPoint(x, y)) {
		
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				return super.onTouchEvent(event);
			}else{
				Log.i(TAG, "��������Ч����...");
			}
		}else{
			Log.i(TAG, "��������Ч����...");
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
