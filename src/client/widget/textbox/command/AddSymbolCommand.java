package client.widget.textbox.command;

import client.utils.Logger;
import client.widget.textbox.DrawTextBox;
import client.widget.textbox.Symbol;
import com.google.gwt.event.dom.client.KeyEvent;
import com.google.web.bindery.event.shared.Event;

import javax.annotation.Nonnull;

public class AddSymbolCommand extends SimpleCommand {

    { type = CommandType.ADD_SYMBOL_COMMAND; }

    // saved state
    protected AddSymbolCommand state;
    protected int cursorPosition;
    protected Symbol addedSymbol;

    protected KeyEvent keyEvent;

    public AddSymbolCommand() {}

    public AddSymbolCommand(DrawTextBox textBox, @Nonnull Event event) {
        super(textBox, event);

        assert event != null;
        try {
            keyEvent = (KeyEvent) event;

        } catch (ClassCastException e) {
            Logger.errorLog("Can't cast Event to KeyEvent in AddSymbolCommand constructor");
        }
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
    public boolean execute() {
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

    public Command prototype(final DrawTextBox textBox, @Nonnull final Event event) {
        assert event != null;
        super.prototype(textBox, event);
        return new AddSymbolCommand(textBox, event);
    }

    protected class AddSymbolSimple extends AddSymbolCommand {

        public AddSymbolSimple (DrawTextBox textBox, @Nonnull KeyEvent event) {
            super(textBox, event);

            cursorPosition = caret.getCursorPosition() + 1;
            addedSymbol = text.createSymbol(event.getNativeEvent().getCharCode());
        }

        @Override
        public boolean execute() {
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
            // THINK: is true? symbols mean not only visible string, but also position
//            assert removed.equals(addedSymbol);

            caret.setCursorPosition(cursorPosition - 1);

            return true;
        }
    }

    protected class AddSymbolSelection extends AddSymbolCommand {

        protected DeleteCommand delete;
        protected AddSymbolSimple add;

        public AddSymbolSelection (DrawTextBox textBox, @Nonnull KeyEvent event) {
            super(textBox, event);
        }

        @Override
        public boolean execute() {

            boolean executed;
            if (add != null && delete != null) { // if redo

                executed = delete.execute();
                assert executed : "Can't execute delete command still one time in AddSymbolSelection";

                executed = add.execute();
                assert executed : "Can't execute add command still one time in AddSymbolSelection";

                return true;
            } else {
                assert textBox.hasSelection();

                delete = new DeleteCommand(textBox, keyEvent);
                executed = delete.execute();
                if (!executed) {
                    return false;
                }

                add = new AddSymbolSimple(textBox, keyEvent);
                executed = add.execute();
                assert executed : "You execute delete selected text, " +
                        "but something wrong with adding text in AddSymbolSelection";

                return true;
            }
        }

        @Override
        public boolean unExecute() {

            boolean executed;

            executed = add.unExecute();
            if (!executed) {
                return false;
            }

            executed = delete.unExecute();
            assert executed : "You can undo add text, " +
                    "but something wrong with undo deleting text in AddSymbolSelection";

            return true;
        }
    }
}
