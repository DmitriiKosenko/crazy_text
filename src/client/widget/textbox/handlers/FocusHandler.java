package client.widget.textbox.handlers;

import client.widget.textbox.DrawTextBox;
import client.widget.textbox.command.CommandCreator;
import client.widget.textbox.command.CommandHandler;
import com.google.gwt.event.dom.client.FocusEvent;

public class FocusHandler extends Handler implements com.google.gwt.event.dom.client.FocusHandler {

    public FocusHandler(DrawTextBox textBox, CommandHandler commandHandler, CommandCreator commandCreator) {
        super(textBox, commandHandler, commandCreator);
    }

    @Override
    public void onFocus(FocusEvent event) {
        assert textBox != null;
        assert textBox.getCaret() != null;

        textBox.getCaret().show();

        updateTexBox();
    }
}
