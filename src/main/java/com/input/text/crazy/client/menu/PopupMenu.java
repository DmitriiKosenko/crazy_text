package com.input.text.crazy.client.menu;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.PopupPanel;

import javax.annotation.CheckForNull;

public class PopupMenu extends PopupPanel {

    protected MenuBar list = new MenuBar(true);

    public PopupMenu() {
        list.setStyleName("popup-panel");
        add(list);
    }

    /**
     * Also add item to the menu
     * @param text
     * @param command
     * @return
     */
    public MenuItem createItem(final String text, @CheckForNull final Scheduler.ScheduledCommand command) {
        assert text != null;

        SafeHtml html = new SafeHtmlBuilder().appendEscaped(text).toSafeHtml();
        MenuItem item = new MenuItem(html, command);

        String style = command != null ?
                "popup-panel-item popup-panel-item-active" :
                "popup-panel-item popup-panel-item-disabled";

        item.setStyleName(style);

        list.addItem(item);
        return item;
    }

    public void clear() {
        list.clearItems();
    }
}
