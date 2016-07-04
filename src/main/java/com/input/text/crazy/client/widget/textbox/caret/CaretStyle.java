package com.input.text.crazy.client.widget.textbox.caret;

import com.input.text.crazy.client.utils.Rectangle;
import com.input.text.crazy.client.widget.textbox.Style;

import javax.annotation.CheckForNull;

public abstract class CaretStyle {
    protected String color;
    protected Style style;

    public CaretStyle() {}

    public CaretStyle(String color) {
        this.color = color;
    }

    public void init(final Style style) {
        assert style != null;

        this.style = style;
    }

    public abstract Rectangle getRectangle(Rectangle baseRectangle);

    public @CheckForNull String getColor() {
        return color;
    }

    public void setColor(final String color) {
        this.color = color;
    }
}
