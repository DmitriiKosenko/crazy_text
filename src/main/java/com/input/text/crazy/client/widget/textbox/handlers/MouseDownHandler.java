package com.input.text.crazy.client.widget.textbox.handlers;

import com.input.text.crazy.client.utils.Logger;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;
import com.input.text.crazy.client.widget.textbox.actions.Bindings;
import com.input.text.crazy.client.widget.textbox.actions.MouseAction;
import com.input.text.crazy.client.widget.textbox.actions.MouseStroke;
import com.input.text.crazy.client.widget.textbox.command.*;
import com.google.gwt.event.dom.client.MouseDownEvent;

public class MouseDownHandler extends Handler implements com.google.gwt.event.dom.client.MouseDownHandler {

    public MouseDownHandler(DrawTextBox textBox, CommandHandler commandHandler, CommandCreator commandCreator) {
        super(textBox, commandHandler, commandCreator);
    }

    @Override
    public void onMouseDown(MouseDownEvent event) {
        try {
            assert textBox != null;
            assert commandCreator != null;
            assert commandHandler != null;
            assert textBox.getCaret() != null;

            MouseStroke stroke = MouseStroke.create(event, MouseAction.DOWN);
            assert stroke != null;

            CommandType type = Bindings.mouseBindings.get(stroke);

            if (type != null) {
                Command command = commandCreator.create(type, textBox, event);
                assert command != null;

                commandHandler.handle(command);
            }

            textBox.getCaret().show();

            updateTexBox();
        } catch (Exception e) {
            Logger.errorLog(e.getMessage());
        }
    }
}
