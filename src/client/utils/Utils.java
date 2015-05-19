package client.utils;

import client.widget.textbox.Symbol;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.dom.client.KeyCodes;

import java.util.Collection;

public final class Utils {

    // not the best way, but it should be work correct for more cases
    private static native int getDevicePixelRatio() /*-{
        return window.devicePixelRatio;
    }-*/;
// Test this approach on different platforms and browsers + android/ios devices
//    function isHighDensity(){
//        return ((window.matchMedia && (window.matchMedia('only screen and (min-resolution: 124dpi), only screen and (min-resolution: 1.3dppx), only screen and (min-resolution: 48.8dpcm)').matches || window.matchMedia('only screen and (-webkit-min-device-pixel-ratio: 1.3), only screen and (-o-min-device-pixel-ratio: 2.6/2), only screen and (min--moz-device-pixel-ratio: 1.3), only screen and (min-device-pixel-ratio: 1.3)').matches)) || (window.devicePixelRatio && window.devicePixelRatio > 1.3));
//    }
//
//
//    function isRetina(){
//        return ((window.matchMedia && (window.matchMedia('only screen and (min-resolution: 192dpi), only screen and (min-resolution: 2dppx), only screen and (min-resolution: 75.6dpcm)').matches || window.matchMedia('only screen and (-webkit-min-device-pixel-ratio: 2), only screen and (-o-min-device-pixel-ratio: 2/1), only screen and (min--moz-device-pixel-ratio: 2), only screen and (min-device-pixel-ratio: 2)').matches)) || (window.devicePixelRatio && window.devicePixelRatio > 2)) && /(iPad|iPhone|iPod)/g.test(navigator.userAgent);
//    }

    public static int highResolutionFactor;
    static {
        highResolutionFactor = getDevicePixelRatio() > 1 ? 2 : 1;
    }

    public static int resolution(int value) {
        return value * highResolutionFactor;
    }

    public static int symbolWidth(final Context2d context, final String symbol) {
        assert context != null;
        assert symbol != null;

        return (int) Math.round(context.measureText(symbol).getWidth());
    }

    public static final String UNIT_PX = "px";

    // get size from String like "12px"
    public static int getSize(String sizeWithUnit) {
        assert sizeWithUnit != null;

        String[] parts = sizeWithUnit.split(UNIT_PX);
        int size = 0;
        try {
            size = parts.length == 0 ? 0 : Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            Logger.infoLog("You try to parse integer incorrect value");
        }

        return size;
    }

    // 0 .. 32 ascii codes is for non-printing characters
    public static final int NON_PRINTING = 32;

    public static Rectangle getRectangle(final Symbol symbol) {
        assert symbol != null;

        Font font = symbol.getFont();
        assert font != null;
        assert font.getFontMetrics() != null;

        return new Rectangle(
                symbol.getX(),
                symbol.getBaseline() - font.getFontMetrics().getBaseline(),
                symbol.getWidth(),
                font.getFontSize()
        );
    }

    // create new rectangle, which include both rectangles
    public static Rectangle getRectangle(final Rectangle r1, final Rectangle r2) {
        assert r1 != null;
        assert r2 != null;

        int x;
        int y;
        int width;
        int height;

        // get top rectangle
        boolean cond = r1.getY() < r2.getY();
        Rectangle top = cond ? r1 : r2;
        Rectangle bottom = cond ? r2 : r1;
        y = top.getY();
        height = bottom.getY() + bottom.getHeight() - top.getY();

        // get left rectangle
        cond = r1.getX() < r2.getX();
        Rectangle left = cond ? r1 : r2;
        Rectangle right = cond ? r2 : r1;
        x = left.getX();
        width = right.getX() + right.getWidth() - left.getX();

        return new Rectangle(x, y, width, height);
    }

    public static Pair<Integer, Integer> getWordPosition(final String text, final int index) {
        assert text != null;
        assert !text.isEmpty();

        int first = -1;
        int last = -10; // incorrect position
        for (int i = 0; i < text.length(); ++i) {
            if (text.charAt(i) == KeyCodes.KEY_SPACE) {
                if (index <= i) {
                    last = i;
                    break;
                } else {
                    first = i;
                }
            }
        }

        if (last == -10) {
            last = text.length() - 1;
        }

        return new Pair<>(first, last);
    }

    public static String textToString(final Collection<Symbol> symbols) {
        assert symbols != null;

        StringBuilder builder = new StringBuilder();
        for (Symbol symbol : symbols) {

            assert symbol != null;
            builder.append(symbol.getSymbol());
        }
        return builder.toString();
    }
}
