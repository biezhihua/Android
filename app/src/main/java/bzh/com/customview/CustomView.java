package bzh.com.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * ==========================================================<br>
 * <b>版权</b>：　　　音悦台 版权所有(c)2016<br>
 * <b>作者</b>：　　  zhihua.bie@yinyuetai.com<br>
 * <b>创建日期</b>：　16-6-13<br>
 * <b>描述</b>：　　　<br>
 * <b>版本</b>：　    V1.0<br>
 * <b>修订历史</b>：　<br>
 * ==========================================================<br>
 */
public class CustomView extends View {
    Paint mPaint;
    Context mContext;
    private Bitmap mBitmap;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

//        ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(new float[]{
//                1.438F, -0.122F, -0.016F, 0, -0.03F,
//                -0.062F, 1.378F, -0.016F, 0, 0.05F,
//                -0.062F, -0.122F, 1.483F, 0, -0.02F,
//                0, 0, 0, 1, 0,
//        });
//        mPaint.setColorFilter(colorMatrixColorFilter);

//        mPaint.setColorFilter(new LightingColorFilter(0xFFFF00FF, 0x00000000));

//        mPaint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN));

//        mPaint.setXfermode()

        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.test);

//        mPaint.setShader(new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));

//        mPaint.setShader(new LinearGradient(0, 0, 600, 600, new int[] { Color.RED, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE }, new float[] { 0, 0.1F, 0.5F, 0.7F, 0.8F }, Shader.TileMode.REPEAT));


//        mPaint.setShader(new SweepGradient(600, 600, Color.RED, Color.YELLOW));

        RadialGradient shader = new RadialGradient(600, 600, 300, Color.RED, Color.YELLOW, Shader.TileMode.REPEAT);
        mPaint.setShader(shader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0,0,1200,1200,mPaint);
    }
}
