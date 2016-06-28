package com.input.text.crazy.client.widget.textbox.command;

import com.input.text.crazy.client.utils.Pair;

import java.util.*;

public class CommandHandler {

    private Stack<Command> executed = new Stack<>();
    private Stack<Command> canceled = new Stack<>();

    public CommandHandler() {}

    public void handle(final Command command) {
        assert command != null;

        if (handleUndoRedo(command)) {
            return;
        }

        if (command.execute()) {

            if (command.isUnExecutable()) {
                addCommand(command);
                canceled.clear();
            } else {
                addBookmark();
            }

        }
    }

    // if command type == UNDO_COMMAND || REDO_COMMAND handle specially
    protected boolean handleUndoRedo(final Command command) {
        assert command != null;
        assert command.getType() != null;

        if (command.getType() == CommandType.UNDO_COMMAND) {
            undo();
            return true;
        }

        if (command.getType() == CommandType.REDO_COMMAND) {
            redo();
            return true;
        }

        return false;
    }

    public boolean hasExecuted() {
        return executed.size() > 0;
    }

    public void undo() {
        if (executed.size() > 0) {
            Command command = executed.pop();
            assert command != null;

            if (command.getType() == CommandType.BOOKMARK) {
                if (executed.isEmpty()) {
                    return;
                }

                command = executed.pop();
            }

            assert command.isUnExecutable() : "You try undo not canceled command";

            command.unExecute();
            canceled.push(command);
        }
    }

    public boolean hasCanceled() {
        return canceled.size() > 0;
    }

    public void redo() {
        if (canceled.size() > 0) {
            Command command = canceled.pop();
            assert command != null;
            assert command.isExecutable() : "You try execute not executable command";

            command.execute();
            executed.push(command);
        }
    }

    private void addCommand(final Command command) {
        assert command != null;
        assert executed != null;
        assert bookmark != null;

        if (!executed.isEmpty()) {
            Command last = executed.peek();

            if (glueCommand(last, command)) {
                return;
            }

            if (last == bookmark) {
                executed.pop();
            }
        }

        // if command not glue with last command
        Command newCommand = new CompositeCommand();
        newCommand.setType(command.getType());
        newCommand.add(command);

        executed.push(newCommand);
    }

    protected static final Set<Pair<CommandType, CommandType>> gluePairs =
            new HashSet<Pair<CommandType, CommandType>>()
    {{
        add(new Pair<>(CommandType.BACKSPACE_COMMAND, CommandType.DELETE_COMMAND));
        add(new Pair<>(CommandType.DELETE_COMMAND, CommandType.BACKSPACE_COMMAND));
        add(new Pair<>(CommandType.BACKSPACE_COMMAND, CommandType.ADD_SYMBOL_COMMAND));
        add(new Pair<>(CommandType.DELETE_COMMAND, CommandType.ADD_SYMBOL_COMMAND));
    }};

    protected boolean glueCommand(Command last, Command next) {
        assert last != null;
        assert next != null;

        CommandType lastType = last.getType();
        CommandType nextType = next.getType();
        assert lastType != null;
        assert nextType != null;
        assert gluePairs != null;

        if (lastType == nextType || gluePairs.contains(new Pair<>(lastType, nextType))) {
            last.add(next);
            last.setType(nextType);

            return true;
        }

        return false;
    }

    protected void addBookmark() {
        assert executed != null;
        assert bookmark != null;

        if (executed.isEmpty()) {
            return;
        }

        if (executed.peek() == bookmark) {
            return;
        }

        executed.push(bookmark);
    }

    protected class Bookmark extends SimpleCommand {
        { type = CommandType.BOOKMARK; }
    }

    protected final Command bookmark = new Bookmark();
}
