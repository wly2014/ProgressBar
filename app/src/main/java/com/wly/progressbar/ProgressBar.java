package com.wly.progressbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2015/2/5.
 */
public class ProgressBar extends View {


    private Paint paint;
    private Paint paint1;
    private Paint paint2;
    private Paint paint3;
    private Paint paint4;

    private float cx0 = -10;
    private float cx1 = -10;
    private float cx2 = -10;
    private float cx3 = -10;
    private float cx4 = -10;

    private long delay = 100;
    private long duration = 1500;
    private float start = -10;
    private float end ;
    private int desiredWidth = 500;
    private int desiredHeight = 10;

    private ObjectAnimator animator;
    private ObjectAnimator animator1;
    private ObjectAnimator animator2;
    private ObjectAnimator animator3;
    private ObjectAnimator animator4;
    private boolean isRunning = false;

    public ProgressBar(Context context) {
        super(context);
//        init();
    }

    public ProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
//        init();
    }

    public ProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        init();
    }

    private void init() {

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);

        paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setColor(Color.YELLOW);

        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setColor(Color.GREEN);

        paint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint3.setColor(Color.BLUE);

        paint4 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint4.setColor(Color.CYAN);

        animator = ObjectAnimator.ofFloat(this, "cx0", start, end);
        animator.setDuration(duration);
        animator.setInterpolator(new DecelerateAccelerateInterpolator());
        animator.start();

        animator1 = ObjectAnimator.ofFloat(this, "cx1", start, end);
        animator1.setDuration(duration);
        animator1.setStartDelay(delay);
        animator1.setInterpolator(new DecelerateAccelerateInterpolator());


        animator2 = ObjectAnimator.ofFloat(this, "cx2", start, end);
        animator2.setDuration(duration);
        animator2.setStartDelay(delay * 2);
        animator2.setInterpolator(new DecelerateAccelerateInterpolator());

        animator3 = ObjectAnimator.ofFloat(this, "cx3", start, end);
        animator3.setDuration(duration);
        animator3.setStartDelay(delay * 3);
        animator3.setInterpolator(new DecelerateAccelerateInterpolator());

        animator4 = ObjectAnimator.ofFloat(this, "cx4", start, end);
        animator4.setDuration(duration);
        animator4.setStartDelay(delay * 4);
        animator4.setInterpolator(new DecelerateAccelerateInterpolator());
        animator4.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                start();
                Log.i("onAnimationEnd", "");
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(widthMeasureSpec);

        Log.i("widthSize ", widthSize + "");
        int measuredWidth, measuredHeight;

//        if (widthMode == MeasureSpec.AT_MOST) {
//            measuredWidth = desiredWidth;
//        } else
        measuredWidth = widthSize;
//
//        if (heightMode == MeasureSpec.AT_MOST) {
//            measuredHeight = desiredHeight;
//        } else
        measuredHeight = heightSize;

        setMeasuredDimension(measuredWidth, measuredHeight);

        end = -start + measuredWidth;
        init();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (!isRunning) {
            start();
            isRunning = true;
        }

        canvas.drawCircle(cx0, 100, 10, paint);
        canvas.drawCircle(cx1, 100, 10, paint1);
        canvas.drawCircle(cx2, 100, 10, paint2);
        canvas.drawCircle(cx3, 100, 10, paint3);
        canvas.drawCircle(cx4, 100, 10, paint4);
    }

    public void start() {
        animator.start();
        animator1.start();
        animator2.start();
        animator3.start();
        animator4.start();

    }

    public float getCx0() {
        return cx0;
    }

    public void setCx0(float cx0) {
        this.cx0 = cx0;
        invalidate();
    }

    public float getCx1() {
        return cx1;
    }

    public void setCx1(float cx1) {
        this.cx1 = cx1;
        invalidate();
    }

    public float getCx2() {
        return cx2;
    }

    public void setCx2(float cx2) {
        this.cx2 = cx2;
        invalidate();
    }

    public float getCx3() {
        return cx3;
    }

    public void setCx3(float cx3) {
        this.cx3 = cx3;
        invalidate();
    }

    public float getCx4() {
        return cx4;
    }

    public void setCx4(float cx4) {
        this.cx4 = cx4;
        invalidate();
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                this.start();
//        }
//        return true;
//    }

    private class DecelerateAccelerateInterpolator implements TimeInterpolator {

        private DecelerateAccelerateInterpolator() {

        }

        @Override
        public float getInterpolation(float input) {

//            if (input < 0.5) {
//                return (float) Math.sqrt(0.25 - (input - 0.5) * (input - 0.5));
//            } else {
//                return (float) (1 - Math.sqrt(0.25 - (input - 0.5) * (input - 0.5)));
//            }
            return (float) (Math.asin(2 * input - 1) / Math.PI + 0.5);
        }
    }
}
