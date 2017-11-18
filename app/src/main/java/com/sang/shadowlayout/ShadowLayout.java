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
import android.view.View;
import android.widget.FrameLayout;

/**
 * 作者： ${桑小年} on 2017/11/18.
 * 努力，为梦长留
 */

public class ShadowLayout extends FrameLayout {

    private Paint shadowPaint;
    private float radius;

    private RectF   shadowRectF;
    private Context mContext;

    public static final int ALL = 0;
    public static final int LEFT = 1;
    public static final int TOP = 2;
    public static final int RIGHT = 3;
    public static final int BOTTOM = 4;
    private int shadowRadio, shadowX, shadowY;
    private int shadowColor;
    private int shadowSide;
    private int shadowLeft, shadowTop, shadowRight, shadowBotoom;


    public ShadowLayout(@NonNull Context context) {
        this(context, null, 0);
    }

    public ShadowLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }


    public ShadowLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }


    private void initView(Context context, AttributeSet attrs) {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        setWillNotDraw(false);
        mContext = context;

        radius = dip2px(10);

        shadowRectF = new RectF();

        shadowRadio = 20;
        shadowX = 0;
        shadowY = 0;
        shadowColor = Color.parseColor("#88000000");

        shadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        shadowPaint.setColor(Color.TRANSPARENT);
        shadowPaint.setShadowLayer(shadowRadio, shadowX, shadowY, shadowColor);
        shadowLeft = dip2px(5);
        shadowTop = dip2px(5);
        shadowRight = dip2px(5);
        shadowBotoom = dip2px(5);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
//        int offset = shadowRadio + dip2px(5);
        int offset = shadowRadio;
        float rectLeft = 0;
        float rectTop = 0;
        float rectRight = this.getWidth();
        float rectBottom = this.getHeight();
        int paddingLeft = offset;
        int paddingTop = offset;
        int paddingRight = offset;
        int paddingBottom = offset;

//        if (shadowY != 0.0f) {
//            rectBottom = rectBottom - shadowY;
//            paddingBottom = paddingBottom + (int) shadowY;
//        }
//        if (shadowX != 0.0f) {
//            rectRight = rectRight - shadowX;
//            paddingRight = paddingRight + (int) shadowX;
//        }
        shadowRectF.left = rectLeft + shadowRadio;
        shadowRectF.top = rectTop + shadowRadio;
        shadowRectF.right = rectRight - shadowRadio;
        shadowRectF.bottom = rectBottom - shadowRadio;

        setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(shadowRectF, radius, radius, shadowPaint);
    }




    public int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
