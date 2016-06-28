package com.input.text.crazy.client.widget.crazy;

import com.google.gwt.canvas.client.Canvas;
import com.input.text.crazy.client.utils.Pair;
import com.input.text.crazy.client.widget.textbox.Symbol;
import com.input.text.crazy.client.widget.textbox.Text;
import com.input.text.crazy.client.widget.textbox.TextView;

public class TextViewFlipVertical extends TextView {

    protected SymbolViewFlipVertical symbolViewFlip;

    public TextViewFlipVertical(Text text) {
        super(text);
        symbolViewFlip = new SymbolViewFlipVertical(null);
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

            boolean even = first % 2 != 0;
            for (int i = first; i <= last; ++i) {
                Symbol symbol = text.get(i);

                if (even) {
                    symbolViewFlip.setSymbol(symbol);
                    symbolViewFlip.draw(canvas);
                } else {
                    symbolView.setSymbol(symbol);
                    symbolView.draw(canvas);
                }

                even = !even;
            }
        }
    }
}
