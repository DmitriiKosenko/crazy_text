package com.input.text.crazy.client.widget.textbox.handlers;

import com.input.text.crazy.client.widget.textbox.DrawTextBox;
import com.input.text.crazy.client.widget.textbox.command.CommandCreator;
import com.input.text.crazy.client.widget.textbox.command.CommandHandler;
import com.google.gwt.event.dom.client.BlurEvent;

public class BlurHandler extends Handler implements com.google.gwt.event.dom.client.BlurHandler {

    public BlurHandler(DrawTextBox textBox, CommandHandler commandHandler, CommandCreator commandCreator) {
        super(textBox, commandHandler, commandCreator);
    }

    @Override
    public void onBlur(BlurEvent event) {
        assert textBox != null;
        assert textBox.getCaret() != null;

        textBox.getCaret().hide();

        updateTexBox();
    }
}
