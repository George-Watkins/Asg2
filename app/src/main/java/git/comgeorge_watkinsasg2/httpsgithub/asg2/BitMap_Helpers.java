package git.comgeorge_watkinsasg2.httpsgithub.asg2;

/**
 * Created by georg_000 on 2/25/2016.
 */

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class BitMap_Helpers {

    /**
     * Color range is (#000000=black to #ffffff=white)
     * Based on percentage, this function will choose a
     * threshold color. All colors above will be changed to
     * white, all colors below will be black
     * It will use this algorithm to determine pixel color
     * when creating a new bitmap to return from bmpOrig
     *
     * @param bmpOrig    original ffull color image
     * @param percentage used to determine where color threshold is (ranges from 0 to 99)
     * @return brand new black and white only bitmap
     */
    public static Bitmap thresholdBmp(final Bitmap bmpOrig, final int percentage) {
        return ManipBitmap.thresholdBmp(bmpOrig, percentage);
    }

    //

    /**
     * make and return a copy of the drawables bitmap
     *
     * @param drawable non null
     * @return COPY of bitmap
     */
    public static Bitmap copyBitmap(final Drawable drawable) {
        return ManipBitmap.copyBitmap(drawable);
    }

    /**
     * merge the color bmpFastColor with bmpThresholded, pixel by pixel
     * if bmpThresholded pixel is white then use color in  bmpFastColor
     * if bmpThresholded pixel is black then color is black
     *
     * @param bmpColor       edited
     * @param bmpThresholded
     */
    public static void merge(Bitmap bmpColor, final Bitmap bmpThresholded) {
        ManipBitmap.merge(bmpColor, bmpThresholded);
    }

    /**
     * Creates a new Bitmap from src
     * and changes the color saturation of its pixels
     *
     * @param src
     * @param settingSat (can range from 0.0 to 255.0)
     * @return
     */
    public static Bitmap colorBmp(final Bitmap src, final float settingSat) {
        return ManipBitmap.colorBmp(src, settingSat);
    }
}
