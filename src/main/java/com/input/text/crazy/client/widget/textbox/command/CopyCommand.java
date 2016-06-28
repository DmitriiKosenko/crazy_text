package com.input.text.crazy.client.widget.textbox.command;

import com.google.web.bindery.event.shared.Event;
import com.input.text.crazy.client.utils.ClipboardLocal;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;

import javax.annotation.Nullable;

public class CopyCommand extends SimpleCommand {

    { type = CommandType.COPY_COMMAND; }

    public CopyCommand() {}

    public CopyCommand(DrawTextBox textBox, @Nullable Event event) {
        super(textBox, event);
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public boolean execute() {
        assert ClipboardLocal.getInstance() != null;

        String text = textBox.getSelectedText();
        if (text != null) {
            ClipboardLocal.getInstance().put(text);
            return true;
        }

        return false;
    }

    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) {
        super.prototype(textBox, event);
        return new CopyCommand(textBox, null);
    }
}
