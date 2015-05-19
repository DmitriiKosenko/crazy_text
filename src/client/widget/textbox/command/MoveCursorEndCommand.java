package client.widget.textbox.command;

import client.utils.Pair;
import client.widget.textbox.DrawTextBox;
import client.widget.textbox.Text;
import com.google.web.bindery.event.shared.Event;

import javax.annotation.Nullable;

public class MoveCursorEndCommand extends SimpleCommand {

    { type = CommandType.MOVE_CURSOR_END_COMMAND; }

    private static final int size = 1;

    public MoveCursorEndCommand() {}

    public MoveCursorEndCommand(DrawTextBox textBox, @Nullable Event event) {
        super(textBox, event);
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public boolean execute() {
        if (textBox.hasSelection()) {
            Pair<Integer, Integer> selection = caret.getSelectionPositions();
            assert selection != null;

            caret.setCursorPosition(selection.getValue());

            return true;

        } else {
            int position = caret.getCursorPosition();
            assert position >= Text.BEFORE_TEXT_POSITION;

            if (position < text.size() - 1) {
                caret.setCursorPosition(position + size);
                return true;
            }
        }

        return false;
    }

    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) {
        super.prototype(textBox, event);
        return new MoveCursorEndCommand(textBox, event);
    }
}
