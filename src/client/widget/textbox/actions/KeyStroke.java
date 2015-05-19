package client.widget.textbox.actions;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.KeyEvent;

public class KeyStroke extends Stroke {

    public KeyStroke(int code, int mask) {
        super(code, mask);
    }

    public static KeyStroke create(final KeyEvent event) {
        assert event != null;

        NativeEvent nativeEvent = event.getNativeEvent();

        Integer code = nativeEvent.getKeyCode();
        Integer mask = getMask(nativeEvent);

        return new KeyStroke(code, mask);
    }


}
