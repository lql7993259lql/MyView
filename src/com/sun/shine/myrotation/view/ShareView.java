package com.sun.shine.myrotation.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

@SuppressLint("DrawAllocation")
public class ShareView extends View {
	public ShareView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO 自动生成的构造函数存根
	}

	
	protected void onDraw(Canvas canvas){
		canvas.drawColor(Color.WHITE);//设置canvas底色
		Paint paint=new Paint();
		paint.setColor(Color.BLUE);//设置画笔的颜色
		canvas.drawCircle(33, 33, 25, paint);//画圆
		paint.setColor(Color.RED);
		canvas.drawRect(100,100,200,200,paint);//画矩形
		//第二种画矩形
		Rect rect = new Rect();
		rect.set(200,200,300,300);
		paint.setStyle(Style.STROKE);//设置画笔的样式空心
		canvas.drawRect(rect, paint);
		
		paint.setColor(Color.RED);
		paint.setTextSize(20);//设置字体大小
		canvas.drawText("哈哈哈", 400, 400, paint);//写字
		
		paint.setColor(Color.BLACK);
		canvas.drawLine(0, 401, 401, 401, paint);//画一条直线
		
		RectF rectf = new RectF();
		rectf.set(50,402,100,50);
		paint.setColor(Color.YELLOW);
		canvas.drawOval(rectf,paint);//绘制椭圆
	  
		RectF rectf2 = new RectF();
		rectf2.set(150,140,210,200);
		paint.setColor(Color.WHITE);
		canvas.drawArc(rectf2, 150, 140	, true, paint);//绘制扇形
	}
}
