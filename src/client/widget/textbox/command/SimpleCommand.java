package client.widget.textbox.command;

import client.widget.textbox.DrawTextBox;
import client.widget.textbox.Text;
import client.widget.textbox.caret.Caret;
import com.google.web.bindery.event.shared.Event;

import javax.annotation.Nullable;

public abstract class SimpleCommand implements Command {

    protected CommandType type;

    protected DrawTextBox textBox;
    protected Text text;
    protected Caret caret;

    public SimpleCommand() {}

    public SimpleCommand(DrawTextBox textBox, @Nullable Event event) {
        assert textBox != null;
        this.textBox = textBox;

        this.text = textBox.getText();
        this.caret = textBox.getCaret();
        assert text != null;
        assert caret != null;
    }

    @Override
    public CommandType getType() {
        assert type != null;
        return type;
    }

    @Override
    public void setType(final CommandType type) {}

    @Override
    public boolean isExecutable() {
        return false;
    }

    @Override
    public boolean isUnExecutable() {
        return false;
    }

    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public boolean unExecute() {
        return false;
    }

    @Override
    public boolean isCompositeCommand() {
        return false;
    }

    @Override
    public boolean add(final Command command) {
        return false;
    }

    @Override
    public Command prototype(final DrawTextBox textBox, @Nullable Event event) {
        assert textBox != null;
        return null;
    }
}
