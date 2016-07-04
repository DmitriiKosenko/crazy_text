package com.input.text.crazy.client.widget.textbox;

import com.input.text.crazy.client.utils.Point;
import com.input.text.crazy.client.utils.Rectangle;
import com.input.text.crazy.client.utils.Utils;
import com.input.text.crazy.client.widget.textbox.caret.Caret;
import com.input.text.crazy.client.widget.textbox.event.EventListener;
import com.input.text.crazy.client.widget.textbox.event.EventType;
import com.input.text.crazy.client.widget.textbox.event.TextBoxEvent;

import javax.annotation.CheckForNull;
import java.util.*;

public class Text implements Element, EventListener {

    public static final int BEFORE_TEXT_POSITION = -1;

    protected Caret caret;
    protected TextView view;

    protected Symbol templateSymbol; // symbol before first symbol in text
    protected List<Symbol> symbols = new ArrayList<>();
    protected SymbolFlyweightContainer flyweightContainer;

    protected Rectangle position;

    protected int maxLength = 310;

    public Text(Rectangle position) {
        assert position != null;

        this.position = position;
    }

    public void init(final SymbolFlyweightContainer flyweightContainer) {
        this.flyweightContainer = flyweightContainer;

        // means that symbol width = 0
        this.templateSymbol = new Symbol(flyweightContainer.get(null)); // TODO: refactor - make it via SymbolRegistry
    }

    // TODO: move from text class
    public @CheckForNull Symbol createSymbol(final int code) {
        assert flyweightContainer != null;

        if (code < Utils.NON_PRINTING) {
            return null;
        }

        String symbol = Character.toString((char) code);
        SymbolFlyweight flyweight = flyweightContainer.get(symbol); // TODO: refactor - make it via SymbolRegistry
        assert flyweight != null;

        return new Symbol(flyweight);
    }

    public boolean add(final int index, final Symbol symbol) {
        assert symbol != null;
        assert symbols != null;

        if (index > symbols.size()) {
            return false;
        }

        symbols.add(index, symbol);
        return true;
    }

    public int getAppropriatePosition(final Point point) {
        assert point != null;
        assert symbols != null;

        if (isEmpty()) {
            return BEFORE_TEXT_POSITION;
        }

        int position = point.getX();
        int start = getLeft() + view.getLeftOffset();

        boolean find = position < start;
        Iterator<Symbol> iterator = symbols.iterator();

        int i = BEFORE_TEXT_POSITION;
        while (iterator.hasNext() && !find) {
            int symbolWidth = iterator.next().getWidth();
            ++i;

            if (position <= start + symbolWidth) {
                find = true;

                if ((position - start) < (symbolWidth / 2)) {
                    --i;
                }
            }

            start += symbolWidth;
        }

        if (!find) {
            i = symbols.size() - 1;
        }

        return i;
    }

    @Override
    public void update(final TextBoxEvent event) {
        assert event != null;
        assert caret != null;

        if (event.getType() != EventType.TIMER_EVENT) {
            recount();
            adjustVisibility(caret.getVisibleRectangle());
            recount();
        }
    }

    public void recount() {
        assert view != null;
        assert templateSymbol != null;
        assert symbols != null;

        int left = getLeft() + view.getLeftOffset();
        int baseline = view.getBaseLine();

        templateSymbol.setX(left);
        templateSymbol.setBaseline(baseline);

        for (Symbol symbol : symbols) {
            assert symbol != null;

            symbol.setX(left);
            symbol.setBaseline(baseline);
            left += symbol.getWidth();
        }
    }

    public void adjustVisibility(final Rectangle visibleRectangle) {
        assert view != null;

        view.adjustVisibility(visibleRectangle);
    }

    public Symbol get(int index) {
        assert symbols != null;

        if (index < BEFORE_TEXT_POSITION || index >= symbols.size()) {
            return null;
        }

        return index == BEFORE_TEXT_POSITION ? templateSymbol : symbols.get(index);
    }

    public Symbol remove(final int index) {
        assert symbols != null;

        if (index < 0 || index >= symbols.size()) {
            return null;
        }

        return symbols.remove(index);
    }

    public List<Symbol> remove(int index, int count) {
        assert symbols != null;
        List<Symbol> result = new LinkedList<>();

        if (index < 0 || symbols.size() == 0 || index >= symbols.size() || count <= 0) {
            return result;
        }

        int delCount = count < (symbols.size() - index) ? count : symbols.size() - index;
        for (int i = 0; i < delCount; ++i) {
            result.add(symbols.remove(index));
        }

        return result;
    }

    public void setCaret(final Caret caret) {
        assert caret != null;
        this.caret = caret;
    }

    public Caret getCaret() {
        return caret;
    }

    @Override
    public TextView getView() {
        return view;
    }

    public void setView(final TextView view) {
        assert view != null;

        this.view = view;
    }

    public Collection<Symbol> getText() {
        assert symbols != null;

        return symbols;
    }

    public int size() {
        assert symbols != null;

        return symbols.size();
    }

    public boolean isEmpty() {
        assert symbols != null;

        return symbols.isEmpty();
    }

    public int getMaxLength() {
        return maxLength;
    }

    public Rectangle getPosition() {
        assert position != null;

        return position;
    }

    public void setPosition(final Rectangle position) {
        assert position != null;

        this.position = position;
    }

    public int getLeft() {
        assert position != null;

        return position.getX();
    }

    public int getTop() {
        assert position != null;

        return position.getY();
    }

    public int getWidth() {
        assert position != null;

        return position.getWidth();
    }

    public int getHeight() {
        assert position != null;

        return position.getHeight();
    }
}
