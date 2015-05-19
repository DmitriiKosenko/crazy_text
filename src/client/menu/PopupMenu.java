package client.menu;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.PopupPanel;

import javax.annotation.Nullable;

public class PopupMenu extends PopupPanel {

    protected MenuBar list = new MenuBar(true);

    public PopupMenu() {
        list.setStyleName("popup-panel");
        add(list);
    }

    // so incredible interface ;)
    public MenuItem createAndAddMenuItem(final String text, @Nullable final Scheduler.ScheduledCommand command) {
        assert text != null;
        assert list != null;

        SafeHtml html = new SafeHtmlBuilder().appendEscaped(text).toSafeHtml();
        MenuItem item = new MenuItem(html, command);

        if (command != null) {
            item.setStyleName("popup-panel-item popup-panel-item-active");
        } else {
            item.setStyleName("popup-panel-item popup-panel-item-disabled");
        }

        list.addItem(item);
        return item;
    }

    public void clear() {
        assert list != null;
        list.clearItems();
    }
}
