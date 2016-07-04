package com.input.text.crazy.client.widget.textbox.command;

import com.google.gwt.event.dom.client.MouseEvent;
import com.google.web.bindery.event.shared.Event;
import com.input.text.crazy.client.utils.Logger;
import com.input.text.crazy.client.utils.Pair;
import com.input.text.crazy.client.utils.Point;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;
import com.input.text.crazy.client.widget.textbox.Text;

import javax.annotation.Nullable;

public class SelectToPointCommand extends SimpleCommand {

    { type = CommandType.SELECT_TO_POINT_COMMAND; }

    private static final String ERROR = "MouseEvent have to be used in SelectToPointCommand constructor";

    // state
    private Point point;

    public SelectToPointCommand() {}

    public SelectToPointCommand(DrawTextBox textBox, @Nullable Event event) throws Exception {
        super(textBox, event);

        if (event == null || !(event instanceof MouseEvent)) {
            Logger.errorLog(ERROR);
            throw new Exception(ERROR);
        }

        MouseEvent mouseEvent = (MouseEvent) event;
        this.point = textBox.transfer(new Point(mouseEvent.getX(), mouseEvent.getY()));
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public boolean execute() throws Exception {
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

    @Override
    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) throws Exception {
        return new SelectToPointCommand(textBox, event);
    }
}
