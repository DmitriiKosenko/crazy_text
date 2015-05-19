package client.widget.textbox.command;

import client.widget.textbox.DrawTextBox;
import com.google.web.bindery.event.shared.Event;

import javax.annotation.Nullable;

public class MoveCursorEndForceCommand extends SimpleCommand {

    { type = CommandType.MOVE_CURSOR_END_FORCE_COMMAND; }

    public MoveCursorEndForceCommand() {}

    public MoveCursorEndForceCommand(DrawTextBox textBox, @Nullable Event event) {
        super(textBox, event);
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public boolean execute() {
        caret.setCursorPosition(text.size() - 1);
        return true;
    }

    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) {
        super.prototype(textBox, event);
        return new MoveCursorEndForceCommand(textBox, event);
    }
}
