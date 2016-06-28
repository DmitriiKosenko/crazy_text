package com.input.text.crazy.client.widget.textbox.actions;

public enum MouseButton {
    LEFT(0), RIGHT(4), MIDDLE(8);

    private int index;

    MouseButton(int index) {
        assert index == 0 || index == 4 || index == 8;

        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
