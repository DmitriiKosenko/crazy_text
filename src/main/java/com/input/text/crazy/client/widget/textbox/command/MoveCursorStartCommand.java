package com.input.text.crazy.client.widget.textbox.command;

import com.google.web.bindery.event.shared.Event;
import com.input.text.crazy.client.utils.Pair;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;
import com.input.text.crazy.client.widget.textbox.Text;

import javax.annotation.Nullable;

public class MoveCursorStartCommand extends SimpleCommand {

    { type = CommandType.MOVE_CURSOR_START_COMMAND; }

    private static final int size = 1;

    public MoveCursorStartCommand() {}

    public MoveCursorStartCommand(DrawTextBox textBox, @Nullable Event event) throws Exception {
        super(textBox, event);
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public boolean execute() throws Exception {
        if (textBox.hasSelection()) {
            Pair<Integer, Integer> selection = caret.getSelectionPositions();

            caret.setCursorPosition(selection.getKey());
            return true;

        } else {
            int position = caret.getCursorPosition();
            assert position >= Text.BEFORE_TEXT_POSITION;

            if (position > Text.BEFORE_TEXT_POSITION) {
                caret.setCursorPosition(position - size);
                return true;
            }
        }

        return false;
    }

    @Override
    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) throws Exception {
        return new MoveCursorStartCommand(textBox, event);
    }
}
