package com.input.text.crazy.client.widget.textbox.caret;

import com.input.text.crazy.client.utils.Logger;
import com.input.text.crazy.client.utils.Pair;
import com.input.text.crazy.client.utils.Rectangle;
import com.input.text.crazy.client.utils.Utils;
import com.input.text.crazy.client.widget.textbox.Text;
import com.input.text.crazy.client.widget.textbox.event.EventListener;

import javax.annotation.Nullable;

public class Selection extends Caret {

    protected Caret caret;

    protected Pair<Integer, Integer> selection = new Pair<>(
            Text.BEFORE_TEXT_POSITION, Text.BEFORE_TEXT_POSITION
    );

    protected Side direction = Side.NONE;
    protected Side activeBound = Side.NONE;

    public Selection(Caret caret) {
        this.caret = caret;
    }

    public void init(int position) {
        assert position >= Text.BEFORE_TEXT_POSITION;

        selection = new Pair<>(position, position);
    }

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void blink(@Nullable final EventListener listener) {}

    @Override
    public @Nullable Rectangle getVisibleRectangle() {
        assert caret != null;
        assert caretStyle != null;
        assert activeBound != null;

        if (activeBound == Side.NONE) {
            return null;
        }

        Text text = caret.getText();
        assert text != null;

        int index = activeBound == Side.START ? selection.getKey() + 1 : selection.getValue();
        assert index >= Text.BEFORE_TEXT_POSITION;
        assert index < text.size();

        Rectangle rectangle = Utils.getRectangle(text.get(index));
        assert rectangle != null;

        return rectangle;
    }

    @Override
    public Rectangle getDrawingRectangle() {
        assert caret != null;
        assert selection != null;
        assert caretStyle != null;

        Text text = caret.getText();
        assert text != null;

        Rectangle rectangle1 = Utils.getRectangle(text.get(selection.getKey() + 1));
        Rectangle rectangle2 = Utils.getRectangle(text.get(selection.getValue()));
        Rectangle rectangle = Utils.getRectangle(rectangle1, rectangle2);
        assert rectangle1 != null;
        assert rectangle2 != null;
        assert rectangle != null;

        return caretStyle.getRectangle(rectangle);
    }

    @Override
    public int getCursorPosition() {
        Logger.errorLog("You try to use cursor methods in Selection");
        return -5000; // it doesn't make sense in Selection
    }

    @Override
    public void setCursorPosition(final int index) {
        Logger.errorLog("You try to use cursor methods in Selection");
        // do nothing
    }

    public Pair<Integer, Integer> getSelectionPositions() {
        assert selection != null;

        return selection;
    }

    public void setSelectionPositions(final Pair<Integer, Integer> positions) {
        assert positions != null;

        setSelectionPositions(positions, Side.NONE, Side.NONE);
    }

    public void setSelectionPositions(final int size, final Side direction) {
        assert size > 0;
        assert direction != null;
        assert direction != Side.NONE : "You can't use directional selection without directional";

        Side newDirection = this.direction == Side.NONE ? direction : this.direction;
        setSelectionPositions(createPositionForDirection(size, direction), newDirection, newDirection);
    }

    public void setSelectionPositions(final Pair<Integer, Integer> positions, final Side activeBound) {
        assert positions != null;
        assert activeBound != null;
        assert activeBound != Side.NONE : "You can't use selection with active bound without active bound";

        setSelectionPositions(positions, activeBound, Side.NONE);
    }

    protected void setSelectionPositions(
            Pair<Integer, Integer> positions, final Side activeBound, final Side direction
    ) {
        assert positions != null;
        assert activeBound != null;
        assert direction != null;
        assert caret != null;

        positions = correctPositions(positions);

        if (positions.getKey() == (int) positions.getValue()) {
            caret.setCursorPosition(positions.getKey());
            return;
        }

        assert positions.getKey() < positions.getValue();

        this.activeBound = activeBound;
        this.direction = direction;
        this.selection = positions;
    }

    protected Pair<Integer, Integer> correctPositions(final Pair<Integer, Integer> positions) {
        assert positions != null;
        int start = positions.getKey();
        int end = positions.getValue();

        if (!caret.checkPosition(start)) {
            Logger.errorLog("Incorrect start selection position");
        }
        if (!caret.checkPosition(end)) {
            Logger.errorLog("Incorrect end selection position");
        }

        if (start < Text.BEFORE_TEXT_POSITION) {
            start = Text.BEFORE_TEXT_POSITION;
        }
        if (end < Text.BEFORE_TEXT_POSITION) {
            end = Text.BEFORE_TEXT_POSITION;
        }

        int size = caret.getText().size() - 1;

        if (start > size) {
            start = size;
        }
        if (end > size) {
            end = size;
        }

        return new Pair<>(start, end);
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
        assert selection != null;
        assert direction != null;
        assert activeBound != null;

        selection = new Pair<>(Text.BEFORE_TEXT_POSITION, Text.BEFORE_TEXT_POSITION);
        direction = Side.NONE;
        activeBound = Side.NONE;
    }


    protected Pair<Integer, Integer> createPositionForDirection(final int size, final Side direction) {
        assert size > 0;
        assert direction != null;

        switch (this.direction) {
            case NONE:
                if (direction == Side.START) {
                    return new Pair<>(selection.getKey() - size, selection.getValue());
                }

                if (direction == Side.END) {
                    return new Pair<>(selection.getKey(), selection.getValue() + size);
                }
                break;

            case START:
                if (direction == Side.START) {
                    return new Pair<>(selection.getKey() - size, selection.getValue());
                }

                if (direction == Side.END) {
                    assert size <= selection.getValue() - selection.getKey();
                    return new Pair<>(selection.getKey() + size, selection.getValue());
                }
                break;

            case END:
                if (direction == Side.END) {
                    return new Pair<>(selection.getKey(), selection.getValue() + size);
                }

                if (direction == Side.START) {
                    assert size <= selection.getValue() - selection.getKey();
                    return new Pair<>(selection.getKey(), selection.getValue() - size);
                }
                break;
        }

        return null;
    }
}
