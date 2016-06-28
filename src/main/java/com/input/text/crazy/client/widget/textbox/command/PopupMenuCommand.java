package com.input.text.crazy.client.widget.textbox.command;

import com.google.gwt.core.client.Scheduler;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;

public class PopupMenuCommand implements Scheduler.ScheduledCommand {

    protected DrawTextBox textBox;
    protected Command command;
    protected CommandHandler commandHandler;

    public PopupMenuCommand(
            DrawTextBox textBox, Command command, CommandHandler commandHandler
    ) {
        assert textBox != null;
        assert command != null;
        assert commandHandler != null;

        this.textBox = textBox;
        this.command = command;
        this.commandHandler = commandHandler;
    }

    @Override
    public void execute() {
        commandHandler.handle(command);
        textBox.setFocus(true);
    }
}
