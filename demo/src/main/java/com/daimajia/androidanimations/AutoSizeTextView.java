package com.daimajia.androidanimations;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatTextView;

import static android.util.TypedValue.COMPLEX_UNIT_PX;


public class AutoSizeTextView extends AppCompatTextView {
	public AutoSizeTextView(Context context) {
		super(context);
		init();
	}

	public AutoSizeTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public AutoSizeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		Log.i("layoutlayout", "onMeasure==>:" + this.getWidth() + ":" + this.getHeight());
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		Paint currentPaint = getPaint();
		CharSequence charSequence = getText().toString();

		float measuredTextWidth = currentPaint.measureText(charSequence, 0, charSequence.length());
		float widthRatio = measuredTextWidth * 1.0f / w;

		//1 对于不同的文字，测量结果一样，所以不能用这种，比如字母j和a的测量结果一样，但是实际上在屏幕上看到的是不一样的
//        Paint.FontMetrics fontMetrics = currentPaint.getFontMetrics();
//        float measuredTextHeight = fontMetrics.descent - fontMetrics.ascent;
//        Log.i("gerealheight", "a:" + measuredTextHeight);

		//2
		Rect rect = new Rect();
		getPaint().getTextBounds(getText().toString(), 0, getText().length(), rect);
		float measuredTextHeight = rect.height();
		Log.i("gerealheight", "b:" + measuredTextHeight);

		float heightRatio = measuredTextHeight * 1.0f / h;
		Log.i("onSizeChanged", "preheight:" + measuredTextHeight);

		float currentTextSizeInPx = getTextSize();

		if (widthRatio > heightRatio) {
			setTextSize(COMPLEX_UNIT_PX, currentTextSizeInPx / widthRatio);
		} else {
			setTextSize(COMPLEX_UNIT_PX, currentTextSizeInPx / heightRatio);
		}
//        setPadding(0, -10, 0, 0);
	}
}