package com.input.text.crazy.client.widget.textbox.caret;

import com.input.text.crazy.client.utils.Pair;
import com.input.text.crazy.client.utils.Rectangle;
import com.input.text.crazy.client.widget.textbox.Element;
import com.input.text.crazy.client.widget.textbox.Text;
import com.input.text.crazy.client.widget.textbox.event.EventListener;
import com.input.text.crazy.client.widget.textbox.event.TextBoxEvent;

/**
 * After create instance of this class should init it by {@link Cursor} and {@link Selection}
 *
 * @see Caret#initCursor
 * @see Caret#initSelection
 */
public class Caret implements Element, EventListener {

    protected Text text;

    protected Cursor cursor; // means kind of state
    protected Selection selection; // means kind of state
    protected Caret state;

    protected CaretView showView;
    protected CaretView hideView;
    protected CaretView view;

    protected int blinkPeriod = 1000; // 1s
    protected int blinkVisible = 300; // 0.3s

    protected CaretStyle caretStyle; // use in descenders

    public Caret() {
        this.hideView = new CaretViewHide(this);
        this.showView = new CaretViewShow(this);

        setView(hideView);
    }

    public Cursor getCursor() {
        assert cursor != null;

        return cursor;
    }

    public void initCursor(final Cursor cursor) {
        assert cursor != null;
        assert showView != null;
        assert hideView != null;

        this.cursor = cursor;
        cursor.init(showView, hideView);
    }

    public Selection getSelection() {
        assert selection != null;

        return selection;
    }

    public void initSelection(final Selection selection) {
        assert selection != null;

        this.selection = selection;
        selection.init(Text.BEFORE_TEXT_POSITION);
    }


    public Caret getState() {
        assert state != null;

        return state;
    }

    public void setState(final Caret state) {
        assert state != null;
        this.state = state;
    }

    public boolean isSelection() {
        assert state != null;
        assert selection != null;

        return state == selection;
    }

    public void show() {
        assert showView != null;
        assert state != null;

        setView(showView);
        state.show();
    }

    public void hide() {
        assert hideView != null;
        assert state != null;

        setView(hideView);
        state.hide();
    }

    public void blink(final EventListener listener) {
        assert state != null;

        state.blink(listener);
    }

    public Rectangle getVisibleRectangle() {
        assert state != null;

        return state.getVisibleRectangle();
    }

    public Rectangle getDrawingRectangle() {
        assert state != null;

        return state.getDrawingRectangle();
    }

    public int getCursorPosition() {
        assert state != null;

        return state.getCursorPosition();
    }

    public void setCursorPosition(final int index) {
        assert state != null;

        setCursorState();
        state.setCursorPosition(index);
    }

    public Pair<Integer, Integer> getSelectionPositions() {
        assert state != null;

        return state.getSelectionPositions();
    }

    public void setSelectionPositions(final Pair<Integer, Integer> positions) {
        assert state != null;

        setSelectionState();
        state.setSelectionPositions(positions);
    }

    public void setSelectionPositions(final int size, final Side direction) {
        assert direction != null;
        assert state != null;

        setSelectionState();
        state.setSelectionPositions(size, direction);
    }

    public void setSelectionPositions(final Pair<Integer, Integer> positions, final Side activeBound) {
        assert positions != null;
        assert activeBound != null;
        assert state != null;

        setSelectionState();
        state.setSelectionPositions(positions, activeBound);
    }

    public CaretStyle getCaretStyle() {
        assert state != null;

        return state.getCaretStyle();
    }

    public void setCaretStyle(final CaretStyle caretStyle) {
        assert state != null;

        state.setCaretStyle(caretStyle);
    }

    protected void reset() {
        assert state != null;

        state.reset();
    }

    @Override
    public void update(final TextBoxEvent event) {}

    @Override
    public CaretView getView() {
        assert view != null;

        return view;
    }

    public void setView(final CaretView view) {
        assert view != null;

        this.view = view;
    }

    public Text getText() {
        assert text != null;

        return text;
    }

    public void setText(final Text text) {
        assert text != null;

        this.text = text;
    }

    public int getBlinkPeriod() {
        return blinkPeriod;
    }

    public void setBlinkPeriod(final int blinkPeriod) {
        assert blinkPeriod > 0;

        this.blinkPeriod = blinkPeriod;
    }

    public int getBlinkVisible() {
        return blinkVisible;
    }

    public void setBlinkVisible(final int blinkVisible) {
        assert blinkVisible > 0;

        this.blinkVisible = blinkVisible;
    }

    protected boolean checkPosition(final int position) {
        assert text != null;

        return position >= Text.BEFORE_TEXT_POSITION || position < text.size() - 1;
    }

    protected void setCursorState() {
        assert state != null;
        assert cursor != null;

        if (isSelection()) {
            state.reset();
            setState(cursor);
        }
    }

    protected void setSelectionState() {
        assert state != null;
        assert selection != null;

        if (!isSelection()) {
            int position = this.getCursorPosition();

            state.reset();
            setState(selection);

            selection.init(position);
        }
    }
}
