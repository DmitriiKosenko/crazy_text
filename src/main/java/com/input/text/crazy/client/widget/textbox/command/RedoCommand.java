package com.input.text.crazy.client.widget.textbox.command;

import com.google.web.bindery.event.shared.Event;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;

import javax.annotation.Nullable;

public class RedoCommand extends SimpleCommand {

    { type = CommandType.REDO_COMMAND; }

    public RedoCommand() {}

    public RedoCommand(DrawTextBox textBox, @Nullable Event event) {}

    @Override
    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) throws Exception {
        return new RedoCommand(textBox, null);
    }
}
