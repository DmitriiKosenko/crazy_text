package com.input.text.crazy.client.widget.textbox.command;

import com.google.gwt.event.dom.client.KeyEvent;
import com.google.web.bindery.event.shared.Event;
import com.input.text.crazy.client.utils.Logger;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;
import com.input.text.crazy.client.widget.textbox.Symbol;

import javax.annotation.Nullable;

public class AddSymbolCommand extends SimpleCommand {

    { type = CommandType.ADD_SYMBOL_COMMAND; }

    private static final String ERROR = "KeyEvent have to used in AddSymbolCommand constructor";

    // saved state
    protected AddSymbolCommand state;
    protected int cursorPosition;
    protected Symbol addedSymbol;

    protected KeyEvent keyEvent;

    public AddSymbolCommand() {}

    public AddSymbolCommand(DrawTextBox textBox, @Nullable Event event) throws Exception {
        super(textBox, event);

        if (event == null || !(event instanceof KeyEvent)) {
            Logger.errorLog(ERROR);
            throw new Exception(ERROR);
        }

        keyEvent = (KeyEvent) event;
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
        assert keyEvent != null;

        if (state != null) { // if redo
            return state.execute();
        }

        if (textBox.hasSelection()) {
            state = new AddSymbolSelection(textBox, keyEvent);
        } else {
            state = new AddSymbolSimple(textBox, keyEvent);
        }
        return state.execute();
    }

    @Override
    public boolean unExecute() {
        return state.unExecute();
    }

    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) throws Exception {
        return new AddSymbolCommand(textBox, event);
    }

    protected class AddSymbolSimple extends AddSymbolCommand {

        public AddSymbolSimple (DrawTextBox textBox, @Nullable KeyEvent event) throws Exception{
            super(textBox, event);

            cursorPosition = caret.getCursorPosition() + 1;
            addedSymbol = text.createSymbol(event.getNativeEvent().getCharCode());
        }

        @Override
        public boolean execute() throws Exception {
            if (text.size() >= text.getMaxLength()) {
                return false;
            }

            if (!text.add(cursorPosition, addedSymbol)) {
                return false;
            }

            caret.setCursorPosition(cursorPosition);
            return true;
        }

        @Override
        public boolean unExecute() {
            assert text.size() > 0;

            Symbol removed = text.remove(cursorPosition);
            // TODO: is true? symbols mean not only visible string, but also position
//            assert removed.equals(addedSymbol);

            caret.setCursorPosition(cursorPosition - 1);

            return true;
        }
    }

    protected class AddSymbolSelection extends AddSymbolCommand {

        protected DeleteCommand delete;
        protected AddSymbolSimple add;

        public AddSymbolSelection (DrawTextBox textBox, @Nullable KeyEvent event) throws Exception {
            super(textBox, event);
        }

        @Override
        public boolean execute() throws Exception {

            boolean executed;
            if (add != null && delete != null) { // if redo

                return delete.execute() && add.execute();
            } else {
                assert textBox.hasSelection();

                delete = new DeleteCommand(textBox, keyEvent);
                executed = delete.execute();
                if (!executed) {
                    return false;
                }

                add = new AddSymbolSimple(textBox, keyEvent);

                return add.execute();
            }
        }

        @Override
        public boolean unExecute() {

            return add.unExecute() && delete.unExecute();
        }
    }
}
