package com.input.text.crazy.client.widget.textbox.command;

import com.google.gwt.event.dom.client.MouseEvent;
import com.google.web.bindery.event.shared.Event;
import com.input.text.crazy.client.utils.Logger;
import com.input.text.crazy.client.utils.Pair;
import com.input.text.crazy.client.utils.Point;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;
import com.input.text.crazy.client.widget.textbox.Text;

import javax.annotation.Nonnull;

public class SelectToPointCommand extends SimpleCommand {

    { type = CommandType.SELECT_TO_POINT_COMMAND; }

    // state
    private Point point;

    public SelectToPointCommand() {}

    public SelectToPointCommand(DrawTextBox textBox, @Nonnull Event event) {
        super(textBox, event);

        assert event != null;
        try {
            MouseEvent mouseEvent = (MouseEvent) event;

            this.point = textBox.transfer(new Point(mouseEvent.getX(), mouseEvent.getY()));
            assert point != null;
        } catch (ClassCastException e) {
            Logger.errorLog("Can't cast Event to MouseEvent in SelectToPointCommand constructor");
        }
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public boolean execute() {
        int position = text.getAppropriatePosition(point);
        assert position >= Text.BEFORE_TEXT_POSITION;

        if (textBox.hasSelection()) {
            Pair<Integer, Integer> selection = caret.getSelectionPositions();
            int left = selection.getKey();
            int right = selection.getValue();

            if (right - left == 1 && position == right) {
                // clear selection
                caret.setCursorPosition(position);
                return true; // ??
            }

            if (position <= left) {
                caret.setSelectionPositions(new Pair<>(position, right));
                return true;
            }

            if (position >= right) {
                caret.setSelectionPositions(new Pair<>(left, position));
                return true;
            }

            // position between left & right bounds
            if (position - left > right - position) {
                caret.setSelectionPositions(new Pair<>(left, position));
                return true;
            }

            caret.setSelectionPositions(new Pair<>(position, right));
            return true;

        } else {

            int cursor = caret.getCursorPosition();
            if (position != cursor) {
                caret.setSelectionPositions(
                        new Pair<>(Math.min(cursor, position), Math.max(cursor, position))
                );

                return true;
            }
        }

        return false;
    }

    public Command prototype(final DrawTextBox textBox, @Nonnull final Event event) {
        super.prototype(textBox, event);
        assert event != null;
        return new SelectToPointCommand(textBox, event);
    }
}
