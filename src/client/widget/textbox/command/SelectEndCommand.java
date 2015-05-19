package client.widget.textbox.command;

import client.widget.textbox.DrawTextBox;
import client.widget.textbox.caret.Side;
import com.google.web.bindery.event.shared.Event;

import javax.annotation.Nullable;

public class SelectEndCommand extends SimpleCommand {

    { type = CommandType.SELECT_END_COMMAND; }

    // state
    private static final int size = 1;

    public SelectEndCommand() {}

    public SelectEndCommand(DrawTextBox textBox, @Nullable Event event) {
        super(textBox, event);
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public boolean execute() {
        caret.setSelectionPositions(size, Side.END);
        return true;
    }

    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) {
        super.prototype(textBox, event);
        return new SelectEndCommand(textBox, event);
    }
}
