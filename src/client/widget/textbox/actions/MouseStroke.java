package client.widget.textbox.actions;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.MouseEvent;

public class MouseStroke extends Stroke {

    public static int LEFT_DOWN         = 0;
    public static int LEFT_UP           = 1;
    public static int LEFT_CLICK        = 2;
    public static int LEFT_DBL_CLICK    = 3;
    public static int RIGHT_DOWN        = 4;
    public static int RIGHT_UP          = 5;
    public static int RIGHT_CLICK       = 6;
    public static int RIGHT_DBL_CLICK   = 7;
    public static int MIDDLE_DOWN       = 8;
    public static int MIDDLE_UP         = 9;
    public static int MIDDLE_CLICK      = 10;
    public static int MIDDLE_DBL_CLICK  = 11;

    public MouseStroke(Integer code, Integer mask) {
        super(code, mask);
    }

    public static MouseStroke create(final MouseEvent event, final MouseAction action) {
        assert event != null;
        assert action != null;

        NativeEvent nativeEvent = event.getNativeEvent();

        MouseButton button = getButton(nativeEvent);
        assert button != null;

        return new MouseStroke(button.getIndex() + action.getIndex(), getMask(nativeEvent));
    }

    public static MouseButton getButton(final NativeEvent event) {
        assert event != null;

        switch (event.getButton()) {
            case NativeEvent.BUTTON_LEFT:
                return MouseButton.LEFT;

            case NativeEvent.BUTTON_RIGHT:
                return MouseButton.RIGHT;

            case NativeEvent.BUTTON_MIDDLE:
                return MouseButton.MIDDLE;

            default:
                return null;
        }
    }
}
