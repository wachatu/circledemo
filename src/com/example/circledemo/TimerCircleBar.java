package com.example.circledemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.util.AttributeSet;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class TimerCircleBar extends View {
	
	private RectF mColorWheelRectangle = new RectF();
	private Paint mDefaultWheelPaint;
	private Paint mColorWheelPaint;
	private Paint textPaint;
	private float mColorWheelRadius;
	private float circleStrokeWidth;
	private float pressExtraStrokeWidth;
	private String mText;
	private float mSweepAnglePer;
	private float mSweepAngle;
	private int mTextSize;
	private String textstr = "Configuring";
	BarAnimation anim;
	public TimerCircleBar(Context context) {
		super(context);
		init(null, 0);
	}

	public TimerCircleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs, 0);
	}

	public TimerCircleBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs, defStyle);
	}

	
	private void init(AttributeSet attrs, int defStyle) {
		circleStrokeWidth = dip2px(getContext(), 15);
		pressExtraStrokeWidth = dip2px(getContext(), 2);
		mTextSize = dip2px(getContext(), 20);
		mColorWheelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mColorWheelPaint.setColor(0xFF29a6f6);
		mColorWheelPaint.setStyle(Paint.Style.STROKE);
		mColorWheelPaint.setStrokeWidth(circleStrokeWidth);
		mDefaultWheelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mDefaultWheelPaint.setColor(0xFFeeefef);
		mDefaultWheelPaint.setStyle(Paint.Style.STROKE);
		mDefaultWheelPaint.setStrokeWidth(circleStrokeWidth);
		textPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG);
		textPaint.setColor(0xFF333333);
		textPaint.setStyle(Style.FILL_AND_STROKE);
		textPaint.setTextAlign(Align.LEFT);
		textPaint.setTextSize(mTextSize);
		mText = "Configuring";
		mSweepAngle = 0;
		anim = new BarAnimation();
		anim.setDuration(10000);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawArc(mColorWheelRectangle, -90, 360, false, mDefaultWheelPaint);
		canvas.drawArc(mColorWheelRectangle, -90, mSweepAnglePer, false, mColorWheelPaint);
		Rect bounds = new Rect();
		textPaint.getTextBounds(textstr, 0, textstr.length(), bounds);
		canvas.drawText(
				textstr+"",
				(mColorWheelRectangle.centerX())
						- (textPaint.measureText(textstr) / 2),
				mColorWheelRectangle.centerY() + bounds.height() / 2,
				textPaint);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int height = getDefaultSize(getSuggestedMinimumHeight(),
				heightMeasureSpec);
		int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
		int min = Math.min(width, height);
		setMeasuredDimension(min, min);
		mColorWheelRadius = min - circleStrokeWidth -pressExtraStrokeWidth ;
		mColorWheelRectangle.set(circleStrokeWidth+pressExtraStrokeWidth, circleStrokeWidth+pressExtraStrokeWidth,
				mColorWheelRadius, mColorWheelRadius);
	}
	public void startCustomAnimation(){
		this.startAnimation(anim);
	}
    
	public void setText(String text){
		this.startAnimation(anim);
	}
	
	public void setSweepAngle(float sweepAngle){
		mSweepAngle = sweepAngle;
	}
	
    
    public class BarAnimation extends Animation {
    	/**
    	 * Initializes expand collapse animation, has two types, collapse (1) and expand (0).
    	 * @param view The view to animate
    	 * @param type The type of animation: 0 will expand from gone and 0 size to visible and layout size defined in xml.
    	 * 1 will collapse view and set to gone
    	 */
    	public BarAnimation() {

    	}

    	@Override
    	protected void applyTransformation(float interpolatedTime, Transformation t) {
    		super.applyTransformation(interpolatedTime, t);
    		if (interpolatedTime < 1.0f) {
    			mSweepAnglePer =  interpolatedTime * mSweepAngle;
    		} else {
    			mSweepAnglePer = mSweepAngle;
    		}
    		postInvalidate();  
    	}
    }
    
    public static int dip2px(Context context, float dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }


}
