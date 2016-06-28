package com.input.text.crazy.client.widget.textbox.handlers;

import com.input.text.crazy.client.widget.textbox.DrawTextBox;
import com.input.text.crazy.client.widget.textbox.command.CommandCreator;
import com.input.text.crazy.client.widget.textbox.command.CommandHandler;
import com.input.text.crazy.client.widget.textbox.event.EventType;
import com.input.text.crazy.client.widget.textbox.event.TextBoxEvent;

public abstract class Handler {

    protected DrawTextBox textBox;
    protected CommandHandler commandHandler;
    protected CommandCreator commandCreator;

    public Handler(
            final DrawTextBox textBox, final CommandHandler commandHandler, final CommandCreator commandCreator
    ) {
        assert textBox != null;
        assert commandHandler != null;
        assert commandCreator != null;

        this.textBox = textBox;
        this.commandHandler = commandHandler;
        this.commandCreator = commandCreator;
    }

    protected void updateTexBox() {
        textBox.update(new TextBoxEvent(EventType.EMPTY_EVENT));
    }
}
