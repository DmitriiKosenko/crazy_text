package com.input.text.crazy.client.widget.textbox.command;

import com.google.web.bindery.event.shared.Event;
import com.input.text.crazy.client.utils.ClipboardLocal;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;
import com.input.text.crazy.client.widget.textbox.Text;

import javax.annotation.Nullable;

public class PasteCommand extends SimpleCommand {

    { type = CommandType.PASTE_COMMAND; }

    protected PasteCommand state;
    protected int pastePosition; // paste position
    protected String addedString;

    public PasteCommand() {}

    public PasteCommand(DrawTextBox textBox, @Nullable Event event) throws Exception {
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

        if (state != null) { // if redo

            return state.execute();
        } else {

            if (textBox.hasSelection()) {
                state = new PasteSelection(textBox, null);
            } else {
                state = new PasteSimple(textBox, null);
            }

            return state.execute();
        }
    }

    @Override
    public boolean unExecute() {
        assert state != null;
        return state.unExecute();
    }

    @Override
    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) throws Exception {
        return new PasteCommand(textBox, event);
    }

    protected class PasteSimple extends PasteCommand {

        public PasteSimple(DrawTextBox textBox, @Nullable Event event) throws Exception {
            super(textBox, event);

            pastePosition = caret.getCursorPosition() + 1;
            assert pastePosition >= Text.BEFORE_TEXT_POSITION;

            if (ClipboardLocal.getInstance().isEmpty()) {
                // means addedString = null;
                return;
            }

            String string = ClipboardLocal.getInstance().get();

            int strLength = string.length();
            int availableLength = text.getMaxLength() - text.size(); // can be < 0
            int count = strLength > availableLength ? availableLength : strLength;

            addedString = string.substring(0, count);
        }

        @Override
        public boolean execute() throws Exception {
            if (addedString == null) { // nothing in Clipboard
                return false;
            }

            assert text.size() < text.getMaxLength();

            for (int i = 0; i < addedString.length(); ++i) {
                text.add(pastePosition + i, text.createSymbol(addedString.charAt(i)));
            }

            caret.setCursorPosition(pastePosition + addedString.length() - 1);
            return true;
        }

        @Override
        public boolean unExecute() {

            text.remove(pastePosition, addedString.length());
            caret.setCursorPosition(pastePosition - 1);

            return true;
        }
    }

    protected class PasteSelection extends PasteCommand {

        protected DeleteCommand delete;
        protected PasteSimple paste;

        public PasteSelection(DrawTextBox textBox, @Nullable Event event) throws Exception {
            super(textBox, event);
        }

        @Override
        public boolean execute() throws Exception {

            boolean executed;
            if (delete != null && paste != null) { // for undo

                return delete.execute() && paste.execute();
            } else {
                assert textBox.hasSelection();

                delete = new DeleteCommand(textBox, null);
                executed = delete.execute();
                if (!executed) {
                    return false;
                }

                paste = new PasteSimple(textBox, null);
                return paste.execute();
            }
        }

        @Override
        public boolean unExecute() {

            return paste.unExecute() && delete.unExecute();
        }
    }
}
