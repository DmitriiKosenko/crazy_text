package com.input.text.crazy.client.widget.textbox.command;

import com.google.web.bindery.event.shared.Event;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;

import javax.annotation.Nullable;

public class MoveCursorEndForceCommand extends SimpleCommand {

    { type = CommandType.MOVE_CURSOR_END_FORCE_COMMAND; }

    public MoveCursorEndForceCommand() {}

    public MoveCursorEndForceCommand(DrawTextBox textBox, @Nullable Event event) throws Exception {
        super(textBox, event);
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public boolean execute() throws Exception {
        caret.setCursorPosition(text.size() - 1);
        return true;
    }

    @Override
    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) throws Exception {
        return new MoveCursorEndForceCommand(textBox, event);
    }
}
