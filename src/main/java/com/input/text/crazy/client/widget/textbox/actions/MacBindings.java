package com.input.text.crazy.client.widget.textbox.actions;

import com.input.text.crazy.client.widget.textbox.command.CommandType;
import com.google.gwt.event.dom.client.KeyCodes;

import java.util.HashMap;
import java.util.Map;

public class MacBindings extends Bindings {

    public static final Map<KeyStroke, CommandType> keyBindings = new HashMap<KeyStroke, CommandType>() {{
        put(new KeyStroke(KeyCodes.KEY_C, META), CommandType.COPY_COMMAND);
        put(new KeyStroke(KeyCodes.KEY_V, META), CommandType.PASTE_COMMAND);
        put(new KeyStroke(KeyCodes.KEY_X, META), CommandType.CUT_COMMAND);
        put(new KeyStroke(KeyCodes.KEY_A, META), CommandType.SELECT_ALL_COMMAND);

        put(new KeyStroke(KeyCodes.KEY_Z, META), CommandType.UNDO_COMMAND);
        put(new KeyStroke(KeyCodes.KEY_Z, SHIFT_META), CommandType.REDO_COMMAND);

    }};

    public static final Map<MouseStroke, CommandType> mouseBindings = new HashMap<MouseStroke, CommandType>() {{


    }};
}
