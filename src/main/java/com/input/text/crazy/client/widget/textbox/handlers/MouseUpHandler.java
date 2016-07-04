package com.input.text.crazy.client.widget.textbox.handlers;

import com.input.text.crazy.client.utils.Logger;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;
import com.input.text.crazy.client.widget.textbox.actions.Bindings;
import com.input.text.crazy.client.widget.textbox.actions.MouseAction;
import com.input.text.crazy.client.widget.textbox.actions.MouseStroke;
import com.input.text.crazy.client.widget.textbox.command.Command;
import com.input.text.crazy.client.widget.textbox.command.CommandCreator;
import com.input.text.crazy.client.widget.textbox.command.CommandHandler;
import com.input.text.crazy.client.widget.textbox.command.CommandType;
import com.google.gwt.event.dom.client.MouseUpEvent;

public class MouseUpHandler extends Handler implements com.google.gwt.event.dom.client.MouseUpHandler {

    public MouseUpHandler(DrawTextBox textBox, CommandHandler commandHandler, CommandCreator commandCreator) {
        super(textBox, commandHandler, commandCreator);
    }

    @Override
    public void onMouseUp(MouseUpEvent event) {
        try {
            assert textBox != null;
            assert commandCreator != null;
            assert commandHandler != null;
            assert textBox.getCaret() != null;

            MouseStroke stroke = MouseStroke.create(event, MouseAction.UP);
            assert stroke != null;

            CommandType type = Bindings.mouseBindings.get(stroke);

            if (type != null) {
                Command command = commandCreator.create(type, textBox, event);
                assert command != null;

                commandHandler.handle(command);
            }

            textBox.getCaret().blink(textBox);

            updateTexBox();
        } catch (Exception e) {
            Logger.errorLog(e.getMessage());
        }
    }
}
