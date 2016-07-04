package com.input.text.crazy.client.widget.textbox.command;

import com.google.web.bindery.event.shared.Event;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;
import com.input.text.crazy.client.widget.textbox.Text;
import com.input.text.crazy.client.widget.textbox.caret.Caret;

import javax.annotation.Nullable;

public abstract class SimpleCommand implements Command {

    protected CommandType type;

    protected DrawTextBox textBox;
    protected Text text;
    protected Caret caret;

    public SimpleCommand() {}

    public SimpleCommand(DrawTextBox textBox, @Nullable Event event) throws Exception {
        this.textBox = textBox;
        this.text = textBox.getText();
        this.caret = textBox.getCaret();
    }

    @Override
    public CommandType getType() {
        assert type != null;
        return type;
    }

    @Override
    public void setType(final CommandType type) {
        this.type = type;
    }

    @Override
    public boolean isExecutable() {
        return false;
    }

    @Override
    public boolean isUnExecutable() {
        return false;
    }

    @Override
    public boolean execute() throws Exception {
        return false;
    }

    @Override
    public boolean unExecute() {
        return false;
    }

    @Override
    public boolean isCompositeCommand() {
        return false;
    }

    @Override
    public boolean add(final Command command) {
        return false;
    }

    @Override
    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) throws Exception {
        return null;
    }
}
