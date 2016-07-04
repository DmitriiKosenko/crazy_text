package com.input.text.crazy.client.widget.textbox.caret;

import com.google.gwt.user.client.Timer;
import com.input.text.crazy.client.widget.textbox.event.EventListener;
import com.input.text.crazy.client.widget.textbox.event.EventType;
import com.input.text.crazy.client.widget.textbox.event.TextBoxEvent;

public class CursorTimer extends Timer {

    protected CaretView view;
    protected Caret caret;

    // TODO: ?? register / notifier
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

    public EventListener getListener() {
        return listener;
    }

    public void setListener(final EventListener listener) {
        this.listener = listener;
    }
}
