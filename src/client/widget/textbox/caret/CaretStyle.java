package client.widget.textbox.caret;

import client.utils.Rectangle;
import client.widget.textbox.Style;

import javax.annotation.Nullable;

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

    public @Nullable String getColor() {
        return color;
    }

    public void setColor(@Nullable final String color) {
        this.color = color;
    }
}
