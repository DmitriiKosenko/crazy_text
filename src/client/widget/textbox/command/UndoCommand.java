package client.widget.textbox.command;

import client.widget.textbox.DrawTextBox;
import com.google.web.bindery.event.shared.Event;

import javax.annotation.Nullable;

public class UndoCommand extends SimpleCommand {

    { type = CommandType.UNDO_COMMAND; }

    public UndoCommand() {}

    public UndoCommand(final DrawTextBox textBox, final Event event) {}

    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) {
        super.prototype(textBox, event);
        return new UndoCommand(textBox, event);
    }
}
