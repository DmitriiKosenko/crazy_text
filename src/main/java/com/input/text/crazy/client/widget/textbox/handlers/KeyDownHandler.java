package com.input.text.crazy.client.widget.textbox.handlers;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.input.text.crazy.client.utils.Logger;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;
import com.input.text.crazy.client.widget.textbox.actions.Bindings;
import com.input.text.crazy.client.widget.textbox.actions.KeyStroke;
import com.input.text.crazy.client.widget.textbox.command.Command;
import com.input.text.crazy.client.widget.textbox.command.CommandCreator;
import com.input.text.crazy.client.widget.textbox.command.CommandHandler;
import com.input.text.crazy.client.widget.textbox.command.CommandType;

public class KeyDownHandler extends Handler implements com.google.gwt.event.dom.client.KeyDownHandler {

    public KeyDownHandler(DrawTextBox textBox, CommandHandler commandHandler, CommandCreator commandCreator) {
        super(textBox, commandHandler, commandCreator);
    }

    @Override
    public void onKeyDown(KeyDownEvent event) {
        try {
            assert textBox != null;
            assert commandCreator != null;
            assert commandHandler != null;
            assert textBox.getCaret() != null;

            KeyStroke stroke = KeyStroke.create(event);
            assert stroke != null;

            CommandType type = Bindings.keyBindings.get(stroke);

            if (type != null) {
                Command command = commandCreator.create(type, textBox, event);

                if (command != null) {
                    commandHandler.handle(command);
                    event.getNativeEvent().preventDefault();
                }
            }

            textBox.getCaret().show();

            updateTexBox();
        } catch (Exception e) {
            Logger.errorLog(e.getMessage());
        }
    }
}
