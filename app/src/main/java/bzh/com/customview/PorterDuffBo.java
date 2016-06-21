package bzh.com.customview;

import android.graphics.Bitmap;
import android.graphics.Point;

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
public class PorterDuffBo {

    private int size;

    private Point mPoint;

    public PorterDuffBo() {
        mPoint = new Point();
    }

    public void setSize(int size) {
        this.size = size;
        mPoint.set(size, size);
    }

    public Bitmap initSrcBitmap() {
        int[] pixels = new int[mPoint.x * mPoint.y];
        int dst = 0;
        for (int row = 0; row < mPoint.y; row++) {
            for (int col = 0; col < mPoint.x; col++) {
                pixels[dst++] = color(
                        (float) (mPoint.y - row) / mPoint.y,
                        (float) (mPoint.x - col) / mPoint.x,
                        (float) (mPoint.x - col) / mPoint.x,
                        (float) col / mPoint.x);
            }
        }

        return Bitmap.createBitmap(pixels, size, size, Bitmap.Config.ARGB_8888);
    }

    public Bitmap initDisBitmap() {
        int[] pixels = new int[mPoint.x * mPoint.y];
        int dst = 0;
        for (int row = 0; row < mPoint.y; ++row) {
            for (int col = 0; col < mPoint.x; ++col) {
                pixels[dst++] = color((float) (mPoint.x - col) / mPoint.x, (float) (mPoint.y - row) / mPoint.x, (float) row / mPoint.y, (float) row / mPoint.y);
            }
        }
        return Bitmap.createBitmap(pixels, size, size, Bitmap.Config.ARGB_8888);
    }

    private int color(float Alpha, float R, float G, float B) {
        return (int) (Alpha * 0XFF) << 24 | (int) (R * Alpha * 0XFF) << 16 | (int) (G * Alpha * 0XFF) << 8 | (int) (B * Alpha * 0XFF);
    }

}
