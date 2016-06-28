package com.input.text.crazy.client.widget.textbox.caret;

import com.input.text.crazy.client.utils.Logger;
import com.input.text.crazy.client.utils.Pair;
import com.input.text.crazy.client.utils.Rectangle;
import com.input.text.crazy.client.utils.Utils;
import com.input.text.crazy.client.widget.textbox.Text;
import com.input.text.crazy.client.widget.textbox.event.EventListener;

import javax.annotation.Nullable;

public class Cursor extends Caret {

    protected Caret caret;

    protected CursorTimer hideTimer;
    protected CursorTimer showTimer;

    protected int index = Text.BEFORE_TEXT_POSITION;

    public Cursor(Caret caret) {
        assert caret != null;

        this.caret = caret;
    }


    public void init(CaretView showView, CaretView hideView) {
        assert showView != null;
        assert hideView != null;
        assert caret != null;

        hideTimer = new CursorTimer(caret, hideView);
        showTimer = new CursorShowTimer(caret, showView, hideTimer, caret.getBlinkVisible());
    }

    @Override
    public void show() {
        stopTimers();
    }

    @Override
    public void hide() {
        stopTimers();
    }

    @Override
    public void blink(@Nullable final EventListener listener) {
        assert showTimer != null;
        assert hideTimer != null;
        assert caret != null;

        stopTimers();

        hideTimer.setListener(listener);
        showTimer.setListener(listener);

        showTimer.run();
        showTimer.scheduleRepeating(caret.getBlinkPeriod());
    }

    @Override
    public @Nullable Rectangle getVisibleRectangle() {
        return getRectangle();
    }

    @Override
    public Rectangle getDrawingRectangle() {
        return getRectangle();
    }

    private Rectangle getRectangle() {
        assert caret != null;
        assert caretStyle != null;

        Text text = caret.getText();
        assert text != null;
        assert index >= Text.BEFORE_TEXT_POSITION;
        assert index < text.size();

        Rectangle rectangle = Utils.getRectangle(text.get(index));
        assert rectangle != null;

        return caretStyle.getRectangle(rectangle);
    }

    @Override
    public int getCursorPosition() {
        return index;
    }

    @Override
    public void setCursorPosition(int index) {
        assert caret != null;

        if (!caret.checkPosition(index)) {
            Logger.errorLog("You try to set incorrect position");
        }

        if (index < Text.BEFORE_TEXT_POSITION) {
            this.index = Text.BEFORE_TEXT_POSITION;
            return;
        }

        if (index > caret.getText().size() - 1) {
            this.index = caret.getText().size() - 1;
            return;
        }

        this.index = index;
    }

    public Pair<Integer, Integer> getSelectionPositions() {
        Logger.errorLog("You try to use selection methods in Cursor");
        return null; // it doesn't make sense in Cursor
    }

    public void setSelectionPositions(final Pair<Integer, Integer> positions) {
        Logger.errorLog("You try to use selection methods in Cursor");
        // do nothing
    }

    public void setSelectionPositions(final int size, final Side direction) {
        Logger.errorLog("You try to use selection methods in Cursor");
        // do nothing
    }

    public void setSelectionPositions(final Pair<Integer, Integer> positions, final Side activeBound) {
        Logger.errorLog("You try to use selection methods in Cursor");
        // do nothing
    }

    public CaretStyle getCaretStyle() {
        assert caretStyle != null;

        return caretStyle;
    }

    public void setCaretStyle(final CaretStyle caretStyle) {
        assert caretStyle != null;

        this.caretStyle = caretStyle;
    }

    public void reset() {
        index = Text.BEFORE_TEXT_POSITION;
        stopTimers();
    }

    private void stopTimers() {
        assert showTimer != null;
        assert hideTimer != null;

        showTimer.cancel();
        hideTimer.cancel();
    }
}
