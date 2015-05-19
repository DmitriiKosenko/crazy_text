package client.widget.textbox.command;

import client.widget.textbox.DrawTextBox;
import com.google.web.bindery.event.shared.Event;

import javax.annotation.Nullable;

public class RedoCommand extends SimpleCommand {

    { type = CommandType.REDO_COMMAND; }

    public RedoCommand() {}

    public RedoCommand(DrawTextBox textBox, @Nullable Event event) {}

    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) {
        super.prototype(textBox, event);
        return new RedoCommand(textBox, null);
    }
}
