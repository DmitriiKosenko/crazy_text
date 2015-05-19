package client.widget.crazy;

import client.widget.textbox.Symbol;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;

import javax.annotation.Nullable;

public class SymbolViewFlipHorizontal extends SymbolViewFlipVertical {

    public SymbolViewFlipHorizontal(@Nullable Symbol symbol) {
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

        context.scale(-1, 1);

        context.fillText(
                symbol.getSymbol(),
                -1 * symbol.getX() - symbol.getWidth(),
                symbol.getBaseline()
        );

        context.restore();
    }
}
