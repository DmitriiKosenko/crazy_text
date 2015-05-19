package client.widget.textbox;

import client.widget.textbox.caret.Caret;
import com.google.gwt.canvas.client.Canvas;

import java.util.LinkedList;
import java.util.Queue;

public class DrawTextBoxView implements ElementView {

    protected DrawTextBox textBox;
    protected Text text;
    protected Caret caret;

    protected Queue<Element> elements = new LinkedList<>();

    public DrawTextBoxView(DrawTextBox textBox) {
        assert textBox != null;
        this.textBox = textBox;
    }

    @Override
    public void draw(final Canvas canvas) {
        assert canvas != null;

        int width = canvas.getCoordinateSpaceWidth();
        int height = canvas.getCoordinateSpaceHeight();
        canvas.getCanvasElement().getContext2d().clearRect(0, 0, width, height);

        for (Element element : elements) {
            assert element.getView() != null;

            element.getView().draw(canvas);
        }
    }

    // Push elements ot this view. Items will be rendered in the order of adding
    public void push(final Element element) {
        assert element != null;
        assert elements != null;

        elements.add(element);
    }

    public void reset() {
        elements.clear();
    }

    public DrawTextBox getTextBox() {
        return textBox;
    }

    public void setTextBox(final DrawTextBox textBox) {
        assert textBox != null;

        this.textBox = textBox;
    }
}
