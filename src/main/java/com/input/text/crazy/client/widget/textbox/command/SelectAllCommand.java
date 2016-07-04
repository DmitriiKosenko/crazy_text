package com.input.text.crazy.client.widget.textbox.command;

import com.google.web.bindery.event.shared.Event;
import com.input.text.crazy.client.utils.Pair;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;
import com.input.text.crazy.client.widget.textbox.Text;

import javax.annotation.Nullable;

public class SelectAllCommand extends SimpleCommand {

    { type = CommandType.SELECT_ALL_COMMAND; }

    public SelectAllCommand() {}

    public SelectAllCommand(DrawTextBox textBox, @Nullable Event event) throws Exception {
        super(textBox, event);
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public boolean execute() throws Exception {
        caret.setSelectionPositions(new Pair<>(Text.BEFORE_TEXT_POSITION, text.size() - 1));
        return true;
    }

    @Override
    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) throws Exception {
        return new SelectAllCommand(textBox, event);
    }
}
