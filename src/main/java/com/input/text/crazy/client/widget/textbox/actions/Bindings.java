package com.input.text.crazy.client.widget.textbox.actions;

import com.google.gwt.event.dom.client.KeyCodes;
import com.input.text.crazy.client.widget.textbox.command.CommandType;

import java.util.HashMap;
import java.util.Map;

public class Bindings {

    // find CTRL + ALT + SHIFT + META
    public static final int NONE                = 0b0000;
    public static final int CTRL                = 0b0001;
    public static final int ALT                 = 0b0010;
    public static final int SHIFT               = 0b0100;
    public static final int META                = 0b1000;
    public static final int CTRL_ALT            = 0b0011;
    public static final int CTRL_SHIFT          = 0b0101;
    public static final int CTRL_META           = 0b1001;
    public static final int ALT_SHIFT           = 0b0110;
    public static final int ALT_META            = 0b1010;
    public static final int SHIFT_META          = 0b1100;
    public static final int CTRL_ALT_SHIFT      = 0b0111;
    public static final int CTRL_ALT_META       = 0b1011;
    public static final int ALT_SHIFT_META      = 0b1110;
    public static final int CTRL_ALT_SHIFT_META = 0b1111;


    public static Map<KeyStroke, CommandType> keyBindings = new HashMap<KeyStroke, CommandType>() {{
        put(new KeyStroke(KeyCodes.KEY_LEFT, NONE), CommandType.MOVE_CURSOR_START_COMMAND);
        put(new KeyStroke(KeyCodes.KEY_LEFT, SHIFT), CommandType.SELECT_START_COMMAND);

        put(new KeyStroke(KeyCodes.KEY_RIGHT, NONE), CommandType.MOVE_CURSOR_END_COMMAND);
        put(new KeyStroke(KeyCodes.KEY_RIGHT, SHIFT), CommandType.SELECT_END_COMMAND);

        put(new KeyStroke(KeyCodes.KEY_UP, NONE), CommandType.MOVE_CURSOR_START_FORCE_COMMAND);

        put(new KeyStroke(KeyCodes.KEY_DOWN, NONE), CommandType.MOVE_CURSOR_END_FORCE_COMMAND);

        put(new KeyStroke(KeyCodes.KEY_BACKSPACE, NONE), CommandType.BACKSPACE_COMMAND);

        put(new KeyStroke(KeyCodes.KEY_DELETE, NONE), CommandType.DELETE_COMMAND);


    }};

    public static Map<MouseStroke, CommandType> mouseBindings = new HashMap<MouseStroke, CommandType>() {{
        put(new MouseStroke(MouseStroke.LEFT_DOWN, NONE), CommandType.MOVE_TO_POINT);

        put(new MouseStroke(MouseStroke.LEFT_DOWN, SHIFT), CommandType.SELECT_TO_POINT_COMMAND);


    }};
}
