package com.input.text.crazy.client.widget.textbox.command;

import com.google.web.bindery.event.shared.Event;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;

import javax.annotation.Nullable;

public class UndoCommand extends SimpleCommand {

    { type = CommandType.UNDO_COMMAND; }

    public UndoCommand() {}

    public UndoCommand(final DrawTextBox textBox, @Nullable final Event event) {}

    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) throws Exception {
        return new UndoCommand(textBox, event);
    }
}
