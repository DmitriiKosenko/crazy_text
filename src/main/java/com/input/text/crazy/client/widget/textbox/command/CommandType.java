package com.input.text.crazy.client.widget.textbox.command;

public enum CommandType {

    ADD_SYMBOL_COMMAND,     // tracked command
    BACKSPACE_COMMAND,      // tracked command
    DELETE_COMMAND,         // tracked command

    MOVE_TO_POINT,
    MOVE_CURSOR_START_COMMAND,
    MOVE_CURSOR_END_COMMAND,
    MOVE_CURSOR_START_FORCE_COMMAND,
    MOVE_CURSOR_END_FORCE_COMMAND,

    SELECT_TO_POINT_COMMAND,
    SELECT_START_COMMAND,
    SELECT_END_COMMAND,
    SELECT_WORD_COMMAND,

    COPY_COMMAND,
    PASTE_COMMAND,          // tracked command
    CUT_COMMAND,            // tracked command
    SELECT_ALL_COMMAND,

    UNDO_COMMAND,
    REDO_COMMAND,

    BOOKMARK

}
