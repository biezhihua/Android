

##Canvas

### drawXXX为主的绘制方法

### clipXXX为主的裁剪方法

    clipRect
        Rect
            union
            intersect
        Region

    clipPath
        Path
            lineTo 连接至某个坐标
            moveTo 改变起始点
            close
            quadTo 二阶贝塞尔曲线
            arcTo
            addArc

    Region  区域
    Region.OP 组合模式
            DIFFERENCE          最终区域为第一个区域与第二个区域不同的区域。
            INTERSECT           最终区域为第一个区域与第二个区域相交的区域。
            REPLACE             最终区域为第二个区域。
            REVERSE_DIFFERENCE  最终区域为第二个区域与第一个区域不同的区域。
            UNION               最终区域为第一个区域加第二个区域。
            XOR                 最终区域为第一个区域加第二个区域并减去两者相交的区域。

    Region和Rect有什么区别呢？
            Region表示的是一个区域，而Rect表示的是一个矩形
            Region有个很特别的地方是它不受Canvas的变换影响，Canvas的local不会直接影响到Region自身

### scale/skew/translate/rotate等变换方法

### saveXXX和restoreXXX构成的画布锁定和还原