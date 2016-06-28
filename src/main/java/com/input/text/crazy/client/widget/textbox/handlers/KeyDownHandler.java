package com.input.text.crazy.client.widget.textbox.handlers;

import com.input.text.crazy.client.widget.textbox.DrawTextBox;
import com.input.text.crazy.client.widget.textbox.actions.Bindings;
import com.input.text.crazy.client.widget.textbox.actions.KeyStroke;
import com.input.text.crazy.client.widget.textbox.command.*;
import com.google.gwt.event.dom.client.KeyDownEvent;

public class KeyDownHandler extends Handler implements com.google.gwt.event.dom.client.KeyDownHandler {

    public KeyDownHandler(DrawTextBox textBox, CommandHandler commandHandler, CommandCreator commandCreator) {
        super(textBox, commandHandler, commandCreator);
    }

    @Override
    public void onKeyDown(KeyDownEvent event) {
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
    }
}
