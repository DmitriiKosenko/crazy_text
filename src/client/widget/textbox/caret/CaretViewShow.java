package client.widget.textbox.caret;

import client.utils.Rectangle;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;

public class CaretViewShow extends CaretView {

    public CaretViewShow(Caret caret) {
        super(caret);
    }

    @Override
    public void draw(final Canvas canvas) {
        assert canvas != null;
        assert caret != null;
        assert caret.getCaretStyle() != null;

        Context2d context = canvas.getContext2d();
        Rectangle r = caret.getDrawingRectangle();
        assert r != null : "May be something wrong, if you want to draw nothing?";

        context.save();

        String color = caret.getCaretStyle().getColor();
        if (color != null) {
            context.setFillStyle(color);
        }
        context.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());

        context.restore();
    }
}