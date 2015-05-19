package client.widget.textbox.command;

import client.widget.textbox.DrawTextBox;
import com.google.gwt.core.client.Scheduler;

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
