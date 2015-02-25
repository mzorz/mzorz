package com.zorz.mario.app.ui.components;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;


/**
 * Created by mariozorz on 2/25/15.
 */


public class CircleTransformation implements Transformation {
    //private final int BORDER_COLOR = Color.WHITE;
    //private final int BORDER_RADIUS = 5;

    private final int radius;
    private final int margin;  // dp
    private final int borderColor;
    private final int borderWidth;


    // radius is corner radii in dp
    // margin is the board in dp
    public CircleTransformation(final int radius, final int margin, final int borderColor, final int borderStroke) {
        this.radius = radius;
        this.margin = margin;
        this.borderColor = borderColor;
        this.borderWidth = borderStroke;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());

        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        if (squaredBitmap != source) {
            source.recycle();
        }

        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);

        float r = size / 2f;

        // Prepare the background
        Paint paintBg = new Paint();
        paintBg.setColor(borderColor);
        paintBg.setAntiAlias(true);

        // Draw the background circle
        canvas.drawCircle(r, r, r, paintBg);

        // Draw the image smaller than the background so a little border will be seen
        canvas.drawCircle(r, r, r - borderWidth, paint);

        squaredBitmap.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        //return "circle";
        return "rounded+"+borderColor+radius+margin+borderWidth;
    }
}
