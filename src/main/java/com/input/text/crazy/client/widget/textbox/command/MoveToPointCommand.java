package com.input.text.crazy.client.widget.textbox.command;

import com.google.gwt.event.dom.client.MouseEvent;
import com.google.web.bindery.event.shared.Event;
import com.input.text.crazy.client.utils.Logger;
import com.input.text.crazy.client.utils.Point;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;
import com.input.text.crazy.client.widget.textbox.Text;

import javax.annotation.Nonnull;

public class MoveToPointCommand extends SimpleCommand {

    { type = CommandType.MOVE_TO_POINT; }

    // state
    private Point point;

    public MoveToPointCommand() {}

    public MoveToPointCommand(DrawTextBox textBox, @Nonnull Event event) {
        super(textBox, event);

        assert event != null;
        try {
            MouseEvent mouseEvent = (MouseEvent) event;

            this.point = textBox.transfer(new Point(mouseEvent.getX(), mouseEvent.getY()));
            assert point != null;

        } catch (ClassCastException e) {
            Logger.errorLog("Can't cast Event to MouseEvent in MoveToPointCommand constructor");
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

        caret.setCursorPosition(position);

        return true;
    }

    public Command prototype(final DrawTextBox textBox, @Nonnull final Event event) {
        super.prototype(textBox, event);
        assert event != null;
        return new MoveToPointCommand(textBox, event);
    }
}
