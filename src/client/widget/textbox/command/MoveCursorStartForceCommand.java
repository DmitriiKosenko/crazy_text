package client.widget.textbox.command;

import client.widget.textbox.DrawTextBox;
import client.widget.textbox.Text;
import com.google.web.bindery.event.shared.Event;

import javax.annotation.Nullable;

public class MoveCursorStartForceCommand extends SimpleCommand {

    { type = CommandType.MOVE_CURSOR_START_FORCE_COMMAND; }

    public MoveCursorStartForceCommand() {}

    public MoveCursorStartForceCommand(DrawTextBox textBox, @Nullable Event event) {
        super(textBox, event);
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public boolean execute() {
        caret.setCursorPosition(Text.BEFORE_TEXT_POSITION);
        return true;
    }

    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) {
        super.prototype(textBox, event);
        return new MoveCursorStartForceCommand(textBox, event);
    }
}
