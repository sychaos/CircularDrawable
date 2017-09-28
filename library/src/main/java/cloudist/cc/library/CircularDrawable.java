package cloudist.cc.library;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by cloudist on 2017/9/28.
 */

public class CircularDrawable extends Drawable {

    /**
     * Paint object to draw the element.
     */
    private final Paint paint;
    private int outlineColor;
    private int outlineWidth;

    public CircularDrawable(int outlineColor, int outlineWidth) {
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.outlineColor = outlineColor;
        this.outlineWidth = outlineWidth;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        final Rect bounds = getBounds();

        // Calculations on the different components sizes
        int size = Math.min(bounds.height(), bounds.width());
        float outerRadius = (size - outlineWidth) / 2;
        // Outline Circle
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(outlineWidth);
        paint.setColor(outlineColor);
        canvas.drawCircle(bounds.centerX(), bounds.centerY(), outerRadius, paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return 1 - paint.getAlpha();
    }

    /**
     * Helper class to manage the creation of a CircularProgressDrawable
     *
     * @author Saul Diaz <sefford@gmail.com>
     */
    public static class Builder {

        /**
         * Color of the outline of the empty ring in #AARRGGBB mode.
         */
        int outlineColor;
        /**
         * Color of the outline of the empty ring in #AARRGGBB mode.
         */
        int outlineWidth;

        /**
         * Sets the default empty outer ring outline color.
         *
         * @param outlineColor Outline color in #AARRGGBB format.
         * @return
         */
        public Builder setOutlineColor(int outlineColor) {
            this.outlineColor = outlineColor;
            return this;
        }

        public Builder setOutlineWidth(int outlineWidth) {
            this.outlineWidth = outlineWidth;
            return this;
        }

        /**
         * Creates a new CircularProgressDrawable with the requested parameters
         *
         * @return New CircularProgressDrawableInstance
         */
        public CircularDrawable create() {
            return new CircularDrawable(outlineColor, outlineWidth);
        }

    }
}
