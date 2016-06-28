package com.input.text.crazy.client.widget.textbox.command;

import com.google.web.bindery.event.shared.Event;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;

import javax.annotation.Nullable;

public class DeleteCommand extends SimpleCommand {

    { type = CommandType.DELETE_COMMAND; }

    protected DeleteCommand state;

    public DeleteCommand() {}

    public DeleteCommand(DrawTextBox textBox, @Nullable Event event) {
        super(textBox, event);
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public boolean isUnExecutable() {
        return true;
    }

    @Override
    public boolean execute() {
        if (state != null) { // if redo
            return state.execute();
        }

        if (textBox.hasSelection()) {
            state = new DeleteSelection(textBox, null);
        } else {
            state = new DeleteSimple(textBox, null);
        }
        return state.execute();
    }

    @Override
    public boolean unExecute() {
        assert state != null;
        assert text != null;

        if (text.size() < text.getMaxLength()) {
            return state.unExecute();
        }
        
        return false;
    }

    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) {
        super.prototype(textBox, event);
        return new DeleteCommand(textBox, event);
    }
}
