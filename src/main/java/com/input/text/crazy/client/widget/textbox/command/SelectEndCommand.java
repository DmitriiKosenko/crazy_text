package com.input.text.crazy.client.widget.textbox.command;

import com.google.web.bindery.event.shared.Event;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;
import com.input.text.crazy.client.widget.textbox.caret.Side;

import javax.annotation.Nullable;

public class SelectEndCommand extends SimpleCommand {

    { type = CommandType.SELECT_END_COMMAND; }

    // state
    private static final int size = 1;

    public SelectEndCommand() {}

    public SelectEndCommand(DrawTextBox textBox, @Nullable Event event) throws Exception {
        super(textBox, event);
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public boolean execute() throws Exception {
        caret.setSelectionPositions(size, Side.END);
        return true;
    }

    @Override
    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) throws Exception {
        return new SelectEndCommand(textBox, event);
    }
}
