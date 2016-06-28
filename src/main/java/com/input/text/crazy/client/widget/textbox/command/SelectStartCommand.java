package com.input.text.crazy.client.widget.textbox.command;

import com.google.web.bindery.event.shared.Event;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;
import com.input.text.crazy.client.widget.textbox.caret.Side;

import javax.annotation.Nullable;

public class SelectStartCommand extends SimpleCommand {

    { type = CommandType.SELECT_START_COMMAND; }

    // state
    private static final int size = 1;

    public SelectStartCommand() {}

    public SelectStartCommand(DrawTextBox textBox, @Nullable Event event) {
        super(textBox, event);
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public boolean execute() {
        caret.setSelectionPositions(size, Side.START);
        return true;
    }

    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) {
        super.prototype(textBox, event);
        return new SelectStartCommand(textBox, event);
    }
}
