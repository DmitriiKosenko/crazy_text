package com.input.text.crazy.client.widget.textbox;

import com.google.gwt.canvas.client.Canvas;
import com.input.text.crazy.client.utils.Pair;
import com.input.text.crazy.client.utils.Rectangle;

import java.util.Iterator;

public class TextView implements ElementView {

    public static final int DEFAULT_LEFT_OFFSET = 0;

    protected Text text;
    protected SymbolView symbolView;

    protected int leftOffset = DEFAULT_LEFT_OFFSET;
    protected int baseline;

    public TextView(Text text) {
        assert text != null;

        this.text = text;
        symbolView = new SymbolView(null);
    }

    public void init(final Style style) {
        assert style != null;
        assert style.getFont() != null;
        assert style.getFont().getFontMetrics() != null;
        assert text != null;

        int textBottom;

        int fontSize = style.getFont().getFontSize();
        int height = text.getHeight();

        if (fontSize <= height) {
            textBottom = text.getTop() + height / 2 + fontSize / 2;
        } else {
            // Unfortunately we don't now in this place,
            // may be need some adjusting for correct visibility of Cursor.
            // But this is not main way of working textBox,
            // that's why if it necessary to do some adjust, it should be make by hand
            textBottom = text.getTop() + fontSize;
        }

        baseline = textBottom - style.getFont().getFontMetrics().getDescent();
    }

    public void adjustVisibility(final Rectangle visibleRectangle) {
        // TODO: need to fix - after undo composite paste command incorrect caret position
        afterDeleteAdjustOffsets();
        adjustOffsets(visibleRectangle);
    }

    public void adjustOffsets(final Rectangle visibleRectangle) {
        assert text != null;

        if (visibleRectangle == null) {
            return;
        }

        int textLeftX = text.getLeft();
        int textRightX = text.getLeft() + text.getWidth();

        int visibleLeftX = visibleRectangle.getX();
        int visibleRightX = visibleLeftX + visibleRectangle.getWidth();

        if (textLeftX <= visibleLeftX && textRightX >= visibleRightX) {
            return; // all OK
        }

        int shift;
        if (textLeftX > visibleLeftX) {
            shift = Math.abs(textLeftX - visibleLeftX);
            leftOffset += shift;
        } else {
            shift = Math.abs(textRightX - visibleRightX);
            leftOffset -= shift;
        }
    }

    public void afterDeleteAdjustOffsets() {
        assert text.getText() != null;

        if (leftOffset == DEFAULT_LEFT_OFFSET) {
            return;
        }

        int width = 0;
        for (Symbol symbol : text.getText()) {
            width += symbol.getWidth();
        }

        int visibleWidth = width + leftOffset;

        if (visibleWidth < text.getWidth()) {
            int shift = Math.abs(text.getWidth() - visibleWidth);
            leftOffset += shift;

            if (leftOffset > DEFAULT_LEFT_OFFSET) {
                leftOffset = DEFAULT_LEFT_OFFSET;
            }
        }
    }

    @Override
    public void draw(final Canvas canvas) {
        assert canvas != null;
        assert symbolView != null;
        assert text != null;

        if (text.size() > 0) {
            Pair<Integer, Integer> bounds = findVisibleBounds();
            assert bounds != null;

            int first = bounds.getKey();
            int last = bounds.getValue();
            assert first >= Text.BEFORE_TEXT_POSITION;
            assert last >= Text.BEFORE_TEXT_POSITION;

            for (int i = first; i <= last; ++i) {
                Symbol symbol = text.get(i);
                symbolView.setSymbol(symbol);
                symbolView.draw(canvas);
            }
        }
    }

    protected Pair<Integer, Integer> findVisibleBounds() {
        assert text != null;

        int first = 0;
        int last = 0;
        boolean findFirst = false;
        boolean findLast = false;

        int start = text.getLeft() + leftOffset;
        int left = text.getLeft();
        int right = left + text.getWidth();

        Iterator<Symbol> iterator = text.getText().iterator();
        for (int i = 0; iterator.hasNext() && !(findFirst && findLast) ; ++i) {
            Symbol symbol = iterator.next();

            if (!findFirst && (start + symbol.getWidth() >= left)) {
                first = i;
                findFirst = true;
            }
            if (!findLast && (start + symbol.getWidth() > right)) {
                last = i;
                findLast = true;
            }

            start += symbol.getWidth();
        }

        if (findFirst && !findLast) {
            last = text.size() - 1;
        }

        return new Pair<>(first, last);
    }

    public Text getText() {
        return text;
    }

    public void setText(final Text text) {
        assert text != null;

        this.text = text;
    }

    public int getLeftOffset() {
        return leftOffset;
    }

    public int getBaseLine() {
        return baseline;
    }
}
