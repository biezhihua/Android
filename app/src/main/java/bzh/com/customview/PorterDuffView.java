package bzh.com.customview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * ==========================================================<br>
 * <b>版权</b>：　　　音悦台 版权所有(c)2016<br>
 * <b>作者</b>：　　  zhihua.bie@yinyuetai.com<br>
 * <b>创建日期</b>：　16-6-14<br>
 * <b>描述</b>：　　　<br>
 * <b>版本</b>：　    V1.0<br>
 * <b>修订历史</b>：　<br>
 * ==========================================================<br>
 */
public class PorterDuffView extends View {

    PorterDuff.Mode MODE = PorterDuff.Mode.DST_OUT;

    int RECT_SIZE_SMALL = 400;

    int RECT_SIZE_LARGE = 800;

    Paint mPaint;

    PorterDuffBo porterDuffBo;

    PorterDuffXfermode porterDuffXfermode;

    int screenW;

    int screenH;

    int s_l;
    int s_t;

    int d_l;
    int d_t;

    int rectX;
    int rectY;


    public PorterDuffView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        porterDuffBo = new PorterDuffBo();

        porterDuffXfermode = new PorterDuffXfermode(MODE);


        calu(context);
    }

    public int[] getScreenSize(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return new int[]{metrics.widthPixels, metrics.heightPixels};
    }

    private void calu(Context context) {
        // 获取包含屏幕尺寸的数组
        int[] screenSize = getScreenSize((Activity) context);

        // 获取屏幕尺寸
        screenW = screenSize[0];
        screenH = screenSize[1];

        // 计算左上方正方形原点坐标
        s_l = 0;
        s_t = 0;

        // 计算右上方正方形原点坐标
        d_l = screenW - RECT_SIZE_SMALL;
        d_t = 0;

        // 计算中间方正方形原点坐标
        rectX = screenW / 2 - RECT_SIZE_LARGE / 2;
        rectY = RECT_SIZE_SMALL + (screenH - RECT_SIZE_SMALL) / 2 - RECT_SIZE_LARGE / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLACK);

        porterDuffBo.setSize(RECT_SIZE_SMALL);

        canvas.drawBitmap(porterDuffBo.initSrcBitmap(), s_l, s_t, mPaint);
        canvas.drawBitmap(porterDuffBo.initDisBitmap(), d_l, d_t, mPaint);

        int sc = canvas.saveLayer(0, 0, screenW, screenH, null, Canvas.ALL_SAVE_FLAG);

        porterDuffBo.setSize(RECT_SIZE_LARGE);

        canvas.drawBitmap(porterDuffBo.initDisBitmap(), rectX, rectY, mPaint);

        mPaint.setXfermode(porterDuffXfermode);

        canvas.drawBitmap(porterDuffBo.initSrcBitmap(), rectX, rectY, mPaint);

        mPaint.setXfermode(null);

        canvas.restoreToCount(sc);

    }
}
