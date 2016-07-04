package com.input.text.crazy.client.widget.textbox;

import com.google.gwt.canvas.client.Canvas;
import com.input.text.crazy.client.utils.Font;
import com.input.text.crazy.client.utils.Utils;

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

        this.style = style;

        for (String symbol : map.keySet()) {
            SymbolFlyweight flyweight = create(symbol);
            assert flyweight != null;

            map.put(symbol, flyweight);
        }
    }

    // TODO: refactor - make it via SymbolRegistry
    public SymbolFlyweight get(final String symbol) {

        SymbolFlyweight flyweight = map.get(symbol);

        if (flyweight == null) {
            flyweight = create(symbol);
            assert flyweight != null;

            map.put(symbol, flyweight);
        }

        return flyweight;
    }

    // TODO: refactor - make it via SymbolRegistry
    protected SymbolFlyweight create(final String symbol) {
        assert style != null;

        int width = measureWidth(symbol);
        Font font = style.getFont();
        assert font != null;

        return new SymbolFlyweight(symbol, width, font);
    }

    protected int measureWidth(final String symbol) {
        assert style != null;

        if (symbol == null) {
            return 0;
        }

        Canvas canvas = textBox.getCanvas();
        assert canvas != null;

        return Utils.symbolWidth(canvas.getContext2d(), symbol) + style.getLetterSpacing();
    }
}
