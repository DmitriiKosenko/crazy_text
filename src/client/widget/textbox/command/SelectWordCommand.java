package client.widget.textbox.command;

import client.utils.Logger;
import client.utils.Pair;
import client.utils.Point;
import client.utils.Utils;
import client.widget.textbox.DrawTextBox;
import client.widget.textbox.Text;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.web.bindery.event.shared.Event;

import javax.annotation.Nonnull;

public class SelectWordCommand extends SimpleCommand {

    { type = CommandType.SELECT_WORD_COMMAND; }

    // state
    private Point point;

    public SelectWordCommand() {}

    public SelectWordCommand(DrawTextBox textBox, @Nonnull Event event) {
        super(textBox, event);

        assert event != null;
        try {
            DoubleClickEvent dblClickEvent = (DoubleClickEvent) event;

            this.point = textBox.transfer(new Point(dblClickEvent.getX(), dblClickEvent.getY()));
            assert point != null;
        } catch (ClassCastException e) {
            Logger.errorLog("Can't cast Event to DoubleClickEvent in SelectWordCommand constructor");
        }
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public boolean execute() {
        int position = text.getAppropriatePosition(point);
        assert position >= Text.BEFORE_TEXT_POSITION;

        if (text.isEmpty()) {
            return false;
        }

        Pair<Integer, Integer> wordPosition = Utils.getWordPosition(
                Utils.textToString(text.getText()), position
        );
        assert wordPosition != null;
        caret.setSelectionPositions(wordPosition);

        return true;
    }

    public Command prototype(final DrawTextBox textBox, @Nonnull final Event event) {
        super.prototype(textBox, event);
        return new SelectWordCommand(textBox, event);
    }
}
