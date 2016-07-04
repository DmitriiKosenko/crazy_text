package com.input.text.crazy.client.widget.textbox.command;

import com.google.web.bindery.event.shared.Event;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;
import com.input.text.crazy.client.widget.textbox.Text;

import javax.annotation.Nullable;

public class BackspaceCommand extends DeleteCommand {

    { type = CommandType.BACKSPACE_COMMAND; }

    public BackspaceCommand() {}

    public BackspaceCommand(DrawTextBox textBox, @Nullable Event event) throws Exception {
        super(textBox, event);
    }

    @Override
    public boolean execute() throws Exception {
        if (state != null) { // if redo
            return state.execute();
        }

        if (textBox.hasSelection()) {
            state = new DeleteSelection(textBox, null);
        } else {
            state = new BackspaceSimple(textBox, null);
        }
        return state.execute();
    }

    @Override
    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) throws Exception {
        return new BackspaceCommand(textBox, event);
    }

    protected class BackspaceSimple extends DeleteSimple {

        public BackspaceSimple(DrawTextBox textBox, @Nullable Event event) throws Exception{
            super(textBox, event);

            cursorPosition = caret.getCursorPosition(); // differences with delete is no increment by 1
            assert cursorPosition >= Text.BEFORE_TEXT_POSITION;
        }

        @Override
        public boolean execute() throws Exception {

            return cursorPosition > Text.BEFORE_TEXT_POSITION && super.execute();
        }

        @Override
        public boolean unExecute() {
            if (super.unExecute()) {
                caret.setCursorPosition(cursorPosition);
                return true;
            }

            return false;
        }
    }
}
