package com.input.text.crazy.client.widget.textbox.command;

import com.google.gwt.event.dom.client.MouseEvent;
import com.google.web.bindery.event.shared.Event;
import com.input.text.crazy.client.utils.Logger;
import com.input.text.crazy.client.utils.Point;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;
import com.input.text.crazy.client.widget.textbox.Text;

import javax.annotation.Nullable;

public class MoveToPointCommand extends SimpleCommand {

    { type = CommandType.MOVE_TO_POINT; }

    private static final String ERROR = "MouseEvent have to be used in MoveToPointCommand constructor";

    // state
    private Point point;

    public MoveToPointCommand() {}

    public MoveToPointCommand(DrawTextBox textBox, @Nullable Event event) throws Exception {
        super(textBox, event);

        if (event == null || !(event instanceof MouseEvent)) {
            Logger.errorLog(ERROR);
            throw new Exception(ERROR);
        }

        MouseEvent mouseEvent = (MouseEvent) event;

        this.point = textBox.transfer(new Point(mouseEvent.getX(), mouseEvent.getY()));
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public boolean execute() throws Exception {

        int position = text.getAppropriatePosition(point);
        assert position >= Text.BEFORE_TEXT_POSITION;

        caret.setCursorPosition(position);

        return true;
    }

    @Override
    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) throws Exception {
        return new MoveToPointCommand(textBox, event);
    }
}
