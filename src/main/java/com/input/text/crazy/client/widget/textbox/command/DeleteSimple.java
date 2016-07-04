package com.input.text.crazy.client.widget.textbox.command;

import com.google.web.bindery.event.shared.Event;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;
import com.input.text.crazy.client.widget.textbox.Symbol;
import com.input.text.crazy.client.widget.textbox.Text;

import javax.annotation.Nullable;

public class DeleteSimple extends DeleteCommand {

    protected int cursorPosition; // first removed symbol position
    protected Symbol removed;

    public DeleteSimple(DrawTextBox textBox, @Nullable Event event) throws Exception {
        super(textBox, event);

        cursorPosition = caret.getCursorPosition() + 1;
        assert cursorPosition >= Text.BEFORE_TEXT_POSITION;
    }

    @Override
    public boolean execute() throws Exception {
        if (cursorPosition >= text.size()) {
            return false;
        }

        this.removed = text.remove(cursorPosition);

        caret.setCursorPosition(cursorPosition - 1);
        
        return true;
    }

    @Override
    public boolean unExecute() {
        boolean result = text.add(cursorPosition, removed);

        caret.setCursorPosition(cursorPosition - 1);
        
        return result;
    }
}
