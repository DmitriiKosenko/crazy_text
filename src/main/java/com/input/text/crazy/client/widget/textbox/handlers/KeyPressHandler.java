package com.input.text.crazy.client.widget.textbox.handlers;

import com.input.text.crazy.client.utils.Logger;
import com.input.text.crazy.client.utils.Utils;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;
import com.input.text.crazy.client.widget.textbox.command.*;
import com.google.gwt.event.dom.client.KeyPressEvent;

public class KeyPressHandler extends Handler implements com.google.gwt.event.dom.client.KeyPressHandler {

    public KeyPressHandler(DrawTextBox textBox, CommandHandler commandHandler, CommandCreator commandCreator) {
        super(textBox, commandHandler, commandCreator);
    }

    @Override
    public void onKeyPress(KeyPressEvent event) {
        try {
            assert textBox != null;
            assert commandCreator != null;
            assert commandHandler != null;

            if (event.getCharCode() >= Utils.NON_PRINTING) {
                Command command = commandCreator.create(CommandType.ADD_SYMBOL_COMMAND, textBox, event);

                if (command != null) {
                    commandHandler.handle(command);
                }
            }

            updateTexBox();
        } catch (Exception e) {
            Logger.errorLog(e.getMessage());
        }
    }
}
