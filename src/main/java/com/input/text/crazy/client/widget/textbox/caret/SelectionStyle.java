package com.input.text.crazy.client.widget.textbox.caret;

import com.input.text.crazy.client.utils.Font;
import com.input.text.crazy.client.utils.FontMetrics;
import com.input.text.crazy.client.utils.Rectangle;

public class SelectionStyle extends CaretStyle {

    {
        color = "#99ccff";
    }

    @Override
    public Rectangle getRectangle(final Rectangle base) {
        assert style != null;
        assert base != null;

        Font font = style.getFont();
        assert font != null;
        FontMetrics fontMetrics = font.getFontMetrics();
        assert fontMetrics != null;

        int additional = fontMetrics.getDescent();

        return new Rectangle(
                base.getX(),
                base.getY() - additional,
                base.getWidth(),
                base.getHeight() + additional
        );
    }
}
