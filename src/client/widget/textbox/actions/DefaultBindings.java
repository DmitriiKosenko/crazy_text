package client.widget.textbox.actions;

import client.widget.textbox.command.CommandType;
import com.google.gwt.event.dom.client.KeyCodes;

import java.util.HashMap;
import java.util.Map;


// Only cross platform dependent key binding
// In class file for Win & Unix
public class DefaultBindings extends Bindings {

    public static final Map<KeyStroke, CommandType> keyBindings = new HashMap<KeyStroke, CommandType>() {{
        put(new KeyStroke(KeyCodes.KEY_C, CTRL), CommandType.COPY_COMMAND);
        put(new KeyStroke(KeyCodes.KEY_V, CTRL), CommandType.PASTE_COMMAND);
        put(new KeyStroke(KeyCodes.KEY_X, CTRL), CommandType.CUT_COMMAND);
        put(new KeyStroke(KeyCodes.KEY_A, CTRL), CommandType.SELECT_ALL_COMMAND);

        put(new KeyStroke(KeyCodes.KEY_Z, CTRL), CommandType.UNDO_COMMAND);
        put(new KeyStroke(KeyCodes.KEY_Y, CTRL), CommandType.REDO_COMMAND);

    }};

    public static final Map<MouseStroke, CommandType> mouseBindings = new HashMap<MouseStroke, CommandType>() {{


    }};
}
