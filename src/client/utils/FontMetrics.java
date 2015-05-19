package client.utils;

import client.exceptions.UnSupportedWidgetException;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.CanvasPixelArray;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.ImageData;

import javax.annotation.Nullable;

/**
 * Bundle of font metrics for certain font.
 * Measure font metrics and provide interface for their access.
 *
 */
public class FontMetrics {

    protected Font font;

    protected int baseline; // from top of font rectangle
    protected int mean;
    protected int cap;
    protected int ascent;
    protected int descent;
    protected int shoulder;

    // measured parameters
    protected int xHeight;
    protected int forAscentHeight;
    protected int capHeight;
    protected int forDescentHeight;


    public FontMetrics(Font font) {
        assert font != null;

        this.font = font;
        measureParameters();
        countMetrics();
    }

    protected void measureParameters() {
        Canvas canvas = getCanvas();
        assert canvas != null;

        Context2d context = canvas.getContext2d();

        context.setTextBaseline(Context2d.TextBaseline.ALPHABETIC);
        xHeight = getHeight(context, "x");
        forAscentHeight = getHeight(context, "h");
        capHeight = getHeight(context, "X");

        context.setTextBaseline(Context2d.TextBaseline.BOTTOM);
        forDescentHeight = getHeight(context, "p");

        assert xHeight >= 0;
        assert forAscentHeight >= 0;
        assert capHeight >= 0;
        assert forDescentHeight >= 0;
    }

    protected void countMetrics() {
        int fontSize = font.getFontSize();
        assert fontSize > 0;

        mean = xHeight;
        cap = capHeight;

        int h1 = forAscentHeight - xHeight;
        int h2 = forDescentHeight - xHeight;

        shoulder = (fontSize - h1 - forDescentHeight) / 2;
        if (shoulder < 0) { // some specific fonts
            descent = h2;
            ascent = fontSize - (descent + mean);
            shoulder = 0;
        } else {
            ascent = h1 + shoulder;
            descent = h2 + shoulder;
        }

        baseline = ascent + mean;

        assert baseline >= 0;
        assert mean >= 0;
        assert cap >= 0;
        assert ascent >= 0;
        assert descent >= 0;
        assert shoulder >= 0;

        assert mean + ascent + descent == font.getFontSize();
        assert baseline == ascent + mean;
    }

    // get height of drawing symbol from baseline to it top
    protected int getHeight(final Context2d context, final String symbol) {
        assert context != null;
        assert symbol != null;
        assert font != null;

        int x = 0;
        int y = font.getFontSize();
        int w = Utils.symbolWidth(context, symbol);
        int h = font.getFontSize();

        context.setFillStyle("red");
        context.fillText(symbol, x, y);

        // crop appropriate image
        ImageData data = context.getImageData(x, y - font.getFontSize(), w, h);
        assert data != null;

        int top = getTop(data);

        context.clearRect(x, y, font.getFontSize() * 2, font.getFontSize() * 2);

        return font.getFontSize() - top;
    }

    // get distance from font top and symbol top
    protected int getTop(final ImageData data) {
        assert data != null;

        int top = 0;
        int pos = 0;

        int width = data.getWidth();

        CanvasPixelArray _data = data.getData();

        // find non zero alpha channel
        while (pos < _data.getLength()) {
            if (_data.get(pos + 3) != 0) {
                pos -= pos % (width * 4);
                top = (pos / 4) / width;

                pos = _data.getLength();
            }
            pos += 4;
        }

        return top;
    }

    protected @Nullable Canvas getCanvas() {
        assert font != null;
        try {
            Canvas canvas = Canvas.createIfSupported();
            if (canvas == null) {
                throw new UnSupportedWidgetException();
            }

            canvas.setCoordinateSpaceHeight(font.getFontSize() * 2);
            canvas.setCoordinateSpaceWidth(font.getFontSize() * 2);

            canvas.getContext2d().setFont(font.getFont());

            return canvas;
        } catch (UnSupportedWidgetException e) {
            Logger.errorLog(e.getMessage());
        }

        return null;
    }



    public int getBaseline() {
        return baseline;
    }

    public int getMean() {
        return mean;
    }

    public int getCap() {
        return cap;
    }

    public int getAscent() {
        return ascent;
    }

    public int getDescent() {
        return descent;
    }

    public int getShoulder() {
        return shoulder;
    }

    @Override
    public String toString() {
        return "FontLineMetrics{" +
                "baseline=" + baseline +
                ", mean=" + mean +
                ", cap=" + cap +
                ", ascent=" + ascent +
                ", descent=" + descent +
                ", shoulder=" + shoulder +
                '}';
    }

    @Override
    public int hashCode() {
        int result = baseline;
        result = 31 * result + mean;
        result = 31 * result + cap;
        result = 31 * result + ascent;
        result = 31 * result + descent;
        result = 31 * result + shoulder;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FontMetrics)) return false;

        FontMetrics that = (FontMetrics) o;

        if (ascent != that.ascent) return false;
        if (baseline != that.baseline) return false;
        if (cap != that.cap) return false;
        if (descent != that.descent) return false;
        if (mean != that.mean) return false;
        if (shoulder != that.shoulder) return false;

        return true;
    }
}
