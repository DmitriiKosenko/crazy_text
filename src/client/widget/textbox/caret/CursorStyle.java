package client.widget.textbox.caret;

import client.utils.Font;
import client.utils.FontMetrics;
import client.utils.Rectangle;
import client.utils.Utils;

public class CursorStyle extends CaretStyle {

    @Override
    public Rectangle getRectangle(final Rectangle base) {
        assert style != null;
        assert base != null;

        Font font = style.getFont();
        assert font != null;
        FontMetrics fontMetrics = font.getFontMetrics();
        assert fontMetrics != null;

        int additional = fontMetrics.getDescent();

        int width = Utils.resolution(1);
        int height = font.getFontSize() + additional;

        int symbolWidth = base.getWidth();
        int x = base.getX() + (symbolWidth == 0 ? 0 : symbolWidth - width);
        int y = base.getY() - additional;

        return new Rectangle(x, y, width, height);
    }
}