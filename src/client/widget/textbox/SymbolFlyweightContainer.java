package client.widget.textbox;

import client.utils.Font;
import client.utils.Utils;
import com.google.gwt.canvas.client.Canvas;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class SymbolFlyweightContainer {

    protected DrawTextBox textBox;
    protected Style style;

    protected Map<String, SymbolFlyweight> map = new HashMap<>();

    public SymbolFlyweightContainer(DrawTextBox textBox) {
        assert textBox != null;
        this.textBox = textBox;
    }

    public void init(final Style style) {
        assert style != null;
        assert map != null;

        this.style = style;

        for (String symbol : map.keySet()) {
            SymbolFlyweight flyweight = create(symbol);
            assert flyweight != null;

            map.put(symbol, flyweight);
        }
    }

    public SymbolFlyweight get(@Nullable final String symbol) {
        assert map != null;

        SymbolFlyweight flyweight = map.get(symbol);

        if (flyweight == null) {
            flyweight = create(symbol);
            assert flyweight != null;

            map.put(symbol, flyweight);
        }

        return flyweight;
    }

    protected SymbolFlyweight create(@Nullable final String symbol) {
        assert style != null;

        int width = measureWidth(symbol);
        Font font = style.getFont();
        assert font != null;

        return new SymbolFlyweight(symbol, width, font);
    }

    protected int measureWidth(@Nullable final String symbol) {
        assert style != null;

        if (symbol == null) {
            return 0;
        }

        Canvas canvas = textBox.getCanvas();
        assert canvas != null;

        return Utils.symbolWidth(canvas.getContext2d(), symbol) + style.getLetterSpacing();
    }
}
