package client.widget.textbox;

import client.utils.Font;

public class SymbolFlyweight {

    protected String symbol;
    protected int width;
    protected Font font;

    public SymbolFlyweight(String symbol, int width, Font font) {
        assert width >= 0;
        assert font != null;

        this.symbol = symbol;
        this.width = width;
        this.font = font;
    }

    public int getWidth() {
        return width;
    }

    public String getSymbol() {
        return symbol;
    }

    public Font getFont() {
        return font;
    }

    @Override
    public int hashCode() {
        int result = symbol.hashCode();
        result = 31 * result + width;
        result = 31 * result + font.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SymbolFlyweight)) return false;

        SymbolFlyweight flyweight = (SymbolFlyweight) o;

        if (width != flyweight.width) return false;
        if (!font.equals(flyweight.font)) return false;
        if (!symbol.equals(flyweight.symbol)) return false;

        return true;
    }
}
