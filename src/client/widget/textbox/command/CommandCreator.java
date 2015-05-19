package client.widget.textbox.command;

import client.utils.ClipboardLocal;
import client.widget.textbox.DrawTextBox;
import com.google.web.bindery.event.shared.Event;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class CommandCreator {

    protected CommandHandler handler; // needs for getting info about UNDO/REDO commands

    protected Map<CommandType, Command> commands = new HashMap<CommandType, Command>() {{
        put(CommandType.ADD_SYMBOL_COMMAND, new AddSymbolCommand());
        put(CommandType.BACKSPACE_COMMAND, new BackspaceCommand());
        put(CommandType.DELETE_COMMAND, new DeleteCommand());

        put(CommandType.MOVE_TO_POINT, new MoveToPointCommand());
        put(CommandType.MOVE_CURSOR_START_COMMAND, new MoveCursorStartCommand());
        put(CommandType.MOVE_CURSOR_END_COMMAND, new MoveCursorEndCommand());
        put(CommandType.MOVE_CURSOR_START_FORCE_COMMAND, new MoveCursorStartForceCommand());
        put(CommandType.MOVE_CURSOR_END_FORCE_COMMAND, new MoveCursorEndForceCommand());

        put(CommandType.SELECT_TO_POINT_COMMAND, new SelectToPointCommand());
        put(CommandType.SELECT_WORD_COMMAND, new SelectWordCommand());
        put(CommandType.SELECT_START_COMMAND, new SelectStartCommand());
        put(CommandType.SELECT_END_COMMAND, new SelectEndCommand());

        put(CommandType.COPY_COMMAND, new CopyCommand());               // null if no selected text
        put(CommandType.PASTE_COMMAND, new PasteCommand());             // null if paste buffer is empty
        put(CommandType.CUT_COMMAND, new CutCommand());                 // null if no selected text
        put(CommandType.SELECT_ALL_COMMAND, new SelectAllCommand());    // null if textBox hasn't text

        put(CommandType.UNDO_COMMAND, new UndoCommand());               // null if no executed commands
        put(CommandType.REDO_COMMAND, new RedoCommand());               // null if no canceled commands

    }};

    public CommandCreator(CommandHandler handler) {
        assert handler != null;
        this.handler = handler;
    }

    public @Nullable Command getPrototype(final CommandType type, final DrawTextBox textBox) {
        assert type != null;
        assert textBox != null;
        assert commands != null;
        assert handler != null;

        switch (type) {
            case COPY_COMMAND:
            case CUT_COMMAND:
                if (!textBox.hasSelection()) {
                    return null;
                }
                break;

            case PASTE_COMMAND:
                assert ClipboardLocal.getInstance() != null;

                if (ClipboardLocal.getInstance().isEmpty()) {
                    return null;
                }
                break;

            case SELECT_ALL_COMMAND:
                if (textBox.isEmpty()) {
                    return null;
                }
                break;

            case UNDO_COMMAND:
                if (!handler.hasExecuted()) {
                    return null;
                }
                break;

            case REDO_COMMAND:
                if (!handler.hasCanceled()) {
                    return null;
                }
                break;
        }

        return commands.get(type);
    }

    public @Nullable Command create(
            final CommandType type, final DrawTextBox textBox, @Nullable final Event event
    ) {
        assert type != null;
        assert textBox != null;

        Command command = getPrototype(type, textBox);
        if (command == null) {
            return null;
        }

        Command protoCommand = command.prototype(textBox, event);
        assert protoCommand != null;

        return protoCommand;
    }

    public @Nullable PopupMenuCommand createPopup(
            final CommandType type, final DrawTextBox textBox, final CommandHandler handler
    ) {
        assert type != null;
        assert textBox != null;
        assert handler != null;

        Command command = create(type, textBox, null);

        return command == null ? null : new PopupMenuCommand(textBox, command, handler);
    }
}
