package client.widget.textbox.caret;

import client.widget.textbox.event.EventListener;
import client.widget.textbox.event.EventType;
import client.widget.textbox.event.TextBoxEvent;
import com.google.gwt.user.client.Timer;

import javax.annotation.Nullable;

public class CursorTimer extends Timer {

    protected CaretView view;
    protected Caret caret;

    protected EventListener listener;

    public CursorTimer(Caret caret, CaretView view) {
        assert caret != null;
        assert view != null;

        this.caret = caret;
        this.view = view;
    }

    @Override
    public void run() {
        assert caret != null;
        assert view != null;
        caret.setView(view);

        if (listener != null) {
            listener.update(new TextBoxEvent(EventType.TIMER_EVENT));
        }
    }

    public @Nullable EventListener getListener() {
        return listener;
    }

    public void setListener(@Nullable final EventListener listener) {
        this.listener = listener;
    }
}
