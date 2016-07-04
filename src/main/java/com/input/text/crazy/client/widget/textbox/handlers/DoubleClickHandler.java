package com.input.text.crazy.client.widget.textbox.handlers;

import com.input.text.crazy.client.utils.Logger;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;
import com.input.text.crazy.client.widget.textbox.command.*;
import com.google.gwt.event.dom.client.DoubleClickEvent;

public class DoubleClickHandler extends Handler implements com.google.gwt.event.dom.client.DoubleClickHandler {

    public DoubleClickHandler(DrawTextBox textBox, CommandHandler commandHandler, CommandCreator commandCreator) {
        super(textBox, commandHandler, commandCreator);
    }

    @Override
    public void onDoubleClick(DoubleClickEvent event) {
        try {
            assert textBox != null;
            assert commandCreator != null;
            assert commandHandler != null;

            Command command = commandCreator.create(CommandType.SELECT_WORD_COMMAND, textBox, event);
            if (command != null) {
                commandHandler.handle(command);
            }

            updateTexBox();
        } catch (Exception e) {
            Logger.errorLog(e.getMessage());
        }
    }
}
