package client.widget.textbox;

import client.utils.Font;

public class Symbol implements Element {

    protected int baseline;
    protected int x;

    protected SymbolFlyweight flyweight;
    protected SymbolView view;

    public Symbol(SymbolFlyweight flyweight) {
        assert flyweight != null;

        this.flyweight = flyweight;
    }

    public String getSymbol() {
        return flyweight.getSymbol();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getBaseline() {
        return baseline;
    }

    public void setBaseline(int baseline) {
        this.baseline = baseline;
    }

    public int getWidth() {
        return flyweight.getWidth();
    }

    public Font getFont() {
        return flyweight.getFont();
    }

    @Override
    public SymbolView getView() {
        return view;
    }

    public void setView(SymbolView view) {
        assert view != null;

        this.view = view;
    }

    public String toString() {
        return "symbol: " + getSymbol() +
                "; width= " + getWidth() +
                "; x= " + x +
                "; baseline= " + baseline;
    }

    @Override
    public int hashCode() {
        int result = baseline;
        result = 31 * result + x;
        result = 31 * result + flyweight.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Symbol)) return false;

        Symbol symbol = (Symbol) o;

        if (baseline != symbol.baseline) return false;
        if (x != symbol.x) return false;
        if (!flyweight.equals(symbol.flyweight)) return false;

        return true;
    }
}
