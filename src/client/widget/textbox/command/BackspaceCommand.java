package client.widget.textbox.command;

import client.widget.textbox.DrawTextBox;
import client.widget.textbox.Text;
import com.google.web.bindery.event.shared.Event;

import javax.annotation.Nullable;

public class BackspaceCommand extends DeleteCommand {

    { type = CommandType.BACKSPACE_COMMAND; }

    public BackspaceCommand() {}

    public BackspaceCommand(DrawTextBox textBox, @Nullable Event event) {
        super(textBox, event);
    }

    @Override
    public boolean execute() {
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

    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) {
        super.prototype(textBox, event);
        return new BackspaceCommand(textBox, event);
    }

    protected class BackspaceSimple extends DeleteSimple {

        public BackspaceSimple(DrawTextBox textBox, @Nullable Event event) {
            super(textBox, event);

            cursorPosition = caret.getCursorPosition(); // differences with delete is no increment by 1
            assert cursorPosition >= Text.BEFORE_TEXT_POSITION;
        }

        @Override
        public boolean execute() {
            if (cursorPosition > Text.BEFORE_TEXT_POSITION) {
                return super.execute();
            }
            return false;
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
