package com.input.text.crazy.client.widget.textbox.caret;


import com.input.text.crazy.client.widget.textbox.ElementView;

public abstract class CaretView implements ElementView {

    protected Caret caret;

    public CaretView(Caret caret) {
        assert caret != null;

        this.caret = caret;
    }

    public Caret getCaret() {
        assert caret != null;

        return caret;
    }

    public void setCaret(final Caret caret) {
        assert caret != null;

        this.caret = caret;
    }
}
