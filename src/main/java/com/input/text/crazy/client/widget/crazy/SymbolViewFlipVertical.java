package com.input.text.crazy.client.widget.crazy;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.input.text.crazy.client.widget.textbox.Symbol;
import com.input.text.crazy.client.widget.textbox.SymbolView;

import javax.annotation.Nullable;

public class SymbolViewFlipVertical extends SymbolView {

    public SymbolViewFlipVertical(@Nullable Symbol symbol) {
        super(symbol);
    }

    @Override
    public void draw(Canvas canvas) {
        assert canvas != null;
        assert symbol != null;
        assert symbol.getFont() != null;
        assert symbol.getFont().getFontMetrics() != null;

        Context2d context = canvas.getContext2d();

        context.save();

        context.scale(1, -1);

        context.fillText(
                symbol.getSymbol(),
                symbol.getX(),
                -1 * symbol.getBaseline() + symbol.getFont().getFontMetrics().getMean()
        );

        context.restore();
    }
}
