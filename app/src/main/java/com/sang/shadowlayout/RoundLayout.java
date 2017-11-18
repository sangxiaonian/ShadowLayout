package com.sang.shadowlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * 作者： ${桑小年} on 2017/11/18.
 * 努力，为梦长留
 */

public class RoundLayout extends FrameLayout {

    private Paint mPaint;
    private Path mPath;
    private float topLeftRadius;
    private float topRightRadius;
    private float bottomLeftRadius;
    private int bottomRightRadius;
    private Paint imagePaint;
    private RectF rectF;
    private Context mContext;

    public RoundLayout(@NonNull Context context) {
        this(context, null, 0);
    }

    public RoundLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }


    public RoundLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }


    private void initView(Context context, AttributeSet attrs) {
        mContext = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLUE);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));


        imagePaint = new Paint();
        imagePaint.setXfermode(null);

        mPath = new Path();

        topLeftRadius = dip2px(20);
        topRightRadius = dip2px(60);
        bottomLeftRadius = dip2px(60);
        bottomRightRadius = dip2px(60);

        rectF = new RectF();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rectF.left = 0;
        rectF.top = 0;
        rectF.right = w;
        rectF.bottom = h;
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.saveLayer(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), imagePaint, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);
        mPath.addRoundRect(rectF,topLeftRadius,topLeftRadius, Path.Direction.CCW);
        canvas.drawPath(mPath,mPaint);
        canvas.restore();

    }



    public int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
