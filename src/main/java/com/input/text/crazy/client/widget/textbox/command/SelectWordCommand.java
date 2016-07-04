package com.input.text.crazy.client.widget.textbox.command;

import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.web.bindery.event.shared.Event;
import com.input.text.crazy.client.utils.Logger;
import com.input.text.crazy.client.utils.Pair;
import com.input.text.crazy.client.utils.Point;
import com.input.text.crazy.client.utils.Utils;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;
import com.input.text.crazy.client.widget.textbox.Text;

import javax.annotation.Nullable;

public class SelectWordCommand extends SimpleCommand {

    { type = CommandType.SELECT_WORD_COMMAND; }

    private static final String ERROR = "DoubleClickEvent have to be use in SelectWordCommand constructor";

    // state
    private Point point;

    public SelectWordCommand() {}

    public SelectWordCommand(DrawTextBox textBox, @Nullable Event event) throws Exception {
        super(textBox, event);

        if (event == null || !(event instanceof DoubleClickEvent)) {
            Logger.errorLog(ERROR);
            throw new Exception(ERROR);
        }

        DoubleClickEvent dblClickEvent = (DoubleClickEvent) event;

        this.point = textBox.transfer(new Point(dblClickEvent.getX(), dblClickEvent.getY()));
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public boolean execute() throws Exception {
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

    @Override
    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) throws Exception {
        return new SelectWordCommand(textBox, event);
    }
}
