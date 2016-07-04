package com.input.text.crazy.client.widget.textbox.command;

import com.google.gwt.event.dom.client.KeyEvent;
import com.google.web.bindery.event.shared.Event;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;

import javax.annotation.Nullable;

public class CutCommand extends SimpleCommand {

    { type = CommandType.CUT_COMMAND; }

    protected CopyCommand copy;
    protected DeleteCommand delete;

    protected KeyEvent event;

    public CutCommand() {}

    public CutCommand(DrawTextBox textBox, @Nullable Event event) throws Exception {
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
    public boolean execute() throws Exception {

        if (delete != null) { // if redo

            return delete.execute();
        } else {

            copy = new CopyCommand(textBox, null);
            if (!copy.execute()) {
                return false;
            }

            delete = new DeleteCommand(textBox, null);
            return delete.execute();
        }
    }

    @Override
    public boolean unExecute() {
        return delete.unExecute();
    }

    @Override
    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) throws Exception {
        return new CutCommand(textBox, event);
    }
}
