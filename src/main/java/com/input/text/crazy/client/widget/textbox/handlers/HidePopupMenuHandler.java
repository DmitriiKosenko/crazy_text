package com.input.text.crazy.client.widget.textbox.handlers;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.user.client.ui.PopupPanel;

public class HidePopupMenuHandler implements ClickHandler, ContextMenuHandler {

    protected PopupPanel panel;

    public HidePopupMenuHandler(PopupPanel panel) {
        assert panel != null;

        this.panel = panel;
    }

    protected void hide() {
        assert panel != null;

        panel.hide();
    }

    @Override
    public void onClick(ClickEvent event) {
        hide();
    }

    @Override
    public void onContextMenu(ContextMenuEvent event) {
        hide();
    }
}
