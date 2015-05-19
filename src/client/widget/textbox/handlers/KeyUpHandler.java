package client.widget.textbox.handlers;

import client.widget.textbox.DrawTextBox;
import client.widget.textbox.command.CommandCreator;
import client.widget.textbox.command.CommandHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;

public class KeyUpHandler extends Handler implements com.google.gwt.event.dom.client.KeyUpHandler {

    public KeyUpHandler(DrawTextBox textBox, CommandHandler commandHandler, CommandCreator commandCreator) {
        super(textBox, commandHandler, commandCreator);
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        assert textBox != null;
        assert textBox.getCaret() != null;

        textBox.getCaret().blink(textBox);

        updateTexBox();
    }
}
