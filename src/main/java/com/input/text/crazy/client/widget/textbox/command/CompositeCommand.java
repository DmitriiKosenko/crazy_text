package com.input.text.crazy.client.widget.textbox.command;

import com.google.web.bindery.event.shared.Event;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;

import javax.annotation.Nullable;
import java.util.Deque;
import java.util.LinkedList;

public class CompositeCommand implements Command {

    protected CommandType type;

    protected Deque<Command> commands = new LinkedList<>();

    public CompositeCommand() {}

    @Override
    public CommandType getType() {
        assert type != null;
        return type;
    }

    @Override
    public void setType(final CommandType type) {
        assert type != null;

        this.type = type;
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public boolean isUnExecutable() {
        return true;
    }

    @Override
    public boolean execute() {
        assert commands != null;

        boolean executed = true;
        for (int i = 0; i < commands.size() && executed; ++i) {
            Command command = commands.removeFirst();

            if (command.isExecutable()) {
                executed = command.execute();
            }

            if (executed) {
                commands.addLast(command);
            }
        }

        return executed;
    }

    @Override
    public boolean unExecute() {
        assert commands != null;

        boolean executed = true;
        for (int i = 0; i < commands.size() && executed; ++i) {
            Command command = commands.removeLast();

            if (command.isUnExecutable()) {
                executed = command.unExecute();
            }

            if (executed) {
                commands.addFirst(command);
            }
        }

        return executed;
    }

    @Override
    public boolean isCompositeCommand() {
        return true;
    }

    @Override
    public boolean add(final Command command) {
        assert commands != null;
        assert command != null;

        return commands.add(command);
    }

    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) {
        return null;
    }
}
