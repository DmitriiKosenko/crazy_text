package client.widget.textbox.actions;

import client.utils.Pair;
import com.google.gwt.dom.client.NativeEvent;

public abstract class Stroke extends Pair<Integer, Integer> {

    public static final int CTRL_MASK = 0;
    public static final int ALT_MASK = 1;
    public static final int SHIFT_MASK = 2;
    public static final int META_MASK = 3;

    public Stroke(Integer code, Integer mask) {
        super(code, mask);
    }

    public static int getMask(final NativeEvent event) {
        assert event != null;

        int mask = 0;

        if (event.getCtrlKey()) {
            mask |= 1 << CTRL_MASK;
        }

        if (event.getAltKey()) {
            mask |= 1 << ALT_MASK;
        }

        if (event.getShiftKey()) {
            mask |= 1 << SHIFT_MASK;
        }

        if (event.getMetaKey()) {
            mask |= 1 << META_MASK;
        }

        return mask;
    }
}
