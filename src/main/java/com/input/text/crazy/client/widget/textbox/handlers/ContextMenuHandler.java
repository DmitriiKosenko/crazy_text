package com.input.text.crazy.client.widget.textbox.handlers;

import com.input.text.crazy.client.widget.textbox.DrawTextBox;
import com.input.text.crazy.client.widget.textbox.command.CommandCreator;
import com.input.text.crazy.client.widget.textbox.command.CommandHandler;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.user.client.ui.PopupPanel;

public class ContextMenuHandler extends Handler implements com.google.gwt.event.dom.client.ContextMenuHandler {

    public ContextMenuHandler(DrawTextBox textBox, CommandHandler commandHandler, CommandCreator commandCreator) {
        super(textBox, commandHandler, commandCreator);
    }

    @Override
    public void onContextMenu(ContextMenuEvent event) {

        assert textBox != null;
        // REFACTOR: Now on show context menu textBox lost focus. It's necessary for correct caret display
        // REFACTOR: After close context menu textBox hasn't focus if context command was executed or not
        textBox.setFocus(false);

        event.preventDefault();
        event.stopPropagation();

        PopupPanel popupMenu = textBox.getPopupMenu();
        assert popupMenu != null;

        int x = event.getNativeEvent().getClientX();
        int y = event.getNativeEvent().getClientY();
        popupMenu.setPopupPosition(x, y);
        popupMenu.show();
    }
}
