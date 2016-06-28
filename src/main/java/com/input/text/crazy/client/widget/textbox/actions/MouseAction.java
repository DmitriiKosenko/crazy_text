package com.input.text.crazy.client.widget.textbox.actions;

public enum MouseAction {
    DOWN(0), UP(1), CLICK(2), DBL_CLICK(3);

    private int index;

    MouseAction(int index) {
        assert index >= 0 && index <= 3;

        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
