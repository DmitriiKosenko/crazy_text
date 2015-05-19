package client.widget.textbox.handlers;

import client.widget.textbox.DrawTextBox;
import client.widget.textbox.command.CommandCreator;
import client.widget.textbox.command.CommandHandler;
import client.widget.textbox.event.EventType;
import client.widget.textbox.event.TextBoxEvent;

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
