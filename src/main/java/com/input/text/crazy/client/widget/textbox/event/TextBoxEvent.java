package com.input.text.crazy.client.widget.textbox.event;

import com.google.web.bindery.event.shared.Event;

public class TextBoxEvent extends Event {

    protected EventType type;

    public TextBoxEvent(EventType type) {
        this.type = type;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    @Override
    public Type getAssociatedType() {
        return null;
    }

    @Override
    protected void dispatch(Object handler) {}
}
