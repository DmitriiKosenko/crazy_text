package client.widget.textbox.command;

import client.utils.Pair;
import client.widget.textbox.DrawTextBox;
import client.widget.textbox.Text;
import com.google.web.bindery.event.shared.Event;

import javax.annotation.Nullable;

public class SelectAllCommand extends SimpleCommand {

    { type = CommandType.SELECT_ALL_COMMAND; }

    public SelectAllCommand() {}

    public SelectAllCommand(DrawTextBox textBox, @Nullable Event event) {
        super(textBox, event);
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public boolean execute() {
        caret.setSelectionPositions(new Pair<>(Text.BEFORE_TEXT_POSITION, text.size() - 1));
        return true;
    }

    public Command prototype(final DrawTextBox textBox, @Nullable final Event event) {
        super.prototype(textBox, event);
        return new SelectAllCommand(textBox, event);
    }
}
