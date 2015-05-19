package client.widget.textbox.command;

import client.widget.textbox.DrawTextBox;
import com.google.web.bindery.event.shared.Event;

public interface Command {

    CommandType getType();

    void setType(CommandType type);

    boolean isExecutable();

    boolean isUnExecutable();

    boolean execute();

    boolean unExecute();

    boolean isCompositeCommand();

    boolean add(Command command);

    Command prototype(DrawTextBox textBox, Event event);
}
