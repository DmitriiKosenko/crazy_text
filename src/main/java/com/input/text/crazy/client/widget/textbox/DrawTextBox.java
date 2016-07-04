package com.input.text.crazy.client.widget.textbox;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.user.client.ui.SimplePanel;
import com.input.text.crazy.client.exceptions.UnSupportedWidgetException;
import com.input.text.crazy.client.menu.PopupMenu;
import com.input.text.crazy.client.utils.Pair;
import com.input.text.crazy.client.utils.Point;
import com.input.text.crazy.client.utils.Utils;
import com.input.text.crazy.client.widget.textbox.caret.*;
import com.input.text.crazy.client.widget.textbox.command.CommandCreator;
import com.input.text.crazy.client.widget.textbox.command.CommandHandler;
import com.input.text.crazy.client.widget.textbox.command.CommandType;
import com.input.text.crazy.client.widget.textbox.command.PopupMenuCommand;
import com.input.text.crazy.client.widget.textbox.event.EventListener;
import com.input.text.crazy.client.widget.textbox.event.TextBoxEvent;

import javax.annotation.Nullable;
import java.util.Map;

public class DrawTextBox extends SimplePanel implements EventListener {

    protected Style style;
    protected Canvas canvas;
    protected Text text;
    protected TextView textView;
    protected SymbolFlyweightContainer flyweightContainer;
    protected Caret caret;
    protected Cursor cursor;
    protected CursorStyle cursorStyle;
    protected Selection selection;
    protected SelectionStyle selectionStyle;
    protected CommandHandler commandHandler;
    protected CommandCreator commandCreator;
    protected PopupMenu popupMenu;
    protected DrawTextBoxView view;
    protected Map<String, CommandType> popupMenuItems;

    public DrawTextBox(DrawTextBoxBuilder builder) throws UnSupportedWidgetException {
        assert builder != null;

        builder.init(this);
        builder.build();

        this.style = builder.getStyle();
        this.canvas = builder.getCanvas();
        this.text = builder.getText();
        this.textView = builder.getTextView();
        this.flyweightContainer = builder.getFlyweightContainer();
        this.caret = builder.getCaret();
        this.cursor = builder.getCursor();
        this.cursorStyle = builder.getCursorStyle();
        this.selection = builder.getSelection();
        this.selectionStyle = builder.getSelectionStyle();
        this.commandHandler = builder.getCommandHandler();
        this.commandCreator = builder.getCommandCreator();
        this.popupMenu = builder.getPopupMenu();
        this.popupMenuItems = builder.getPopupMenuItems();
        this.view = builder.getView();
    }

    @Override
    public void update(final TextBoxEvent event) {
        assert event != null;
        assert text != null;
        assert caret != null;
        assert view != null;
        assert canvas != null;

        text.update(event);
        caret.update(event);

        view.draw(canvas);
    }

    public boolean hasSelection() {
        assert caret != null;

        return caret.isSelection();
    }

    public @Nullable String getSelectedText() {
        if (!hasSelection()) {
            return null;
        }

        Pair<Integer, Integer> selection = caret.getSelectionPositions();
        assert selection != null;

        int start = selection.getKey();
        int end = selection.getValue();
        assert start >= Text.BEFORE_TEXT_POSITION;
        assert end < text.size();

        String string = Utils.textToString(text.getText());
        return string.substring(start + 1, end + 1);
    }

    // transfer canvas point coordinates to this input inner coordinates
    public Point transfer(final Point point) {
        assert point != null;
        assert style != null;

        return new Point(
                Utils.resolution(point.getX()) - style.getPaddingLeft(),
                Utils.resolution(point.getY()) - style.getPaddingTop()
        );
    }

    public Text getText() {
        return text;
    }

    public Caret getCaret() {
        return caret;
    }

    public PopupMenu getPopupMenu() throws Exception {
        assert popupMenu != null;
        assert commandCreator != null;
        assert commandHandler != null;

        popupMenu.clear();
        for (String key : popupMenuItems.keySet()) {
            assert key != null;

            PopupMenuCommand popupCommand = commandCreator.createPopup(
                    popupMenuItems.get(key), this, commandHandler
            );

            popupMenu.createItem(key, popupCommand);
        }

        return popupMenu;
    }

    public int getBlinkPeriod() {
        assert caret != null;

        return caret.getBlinkPeriod();
    }

    public void setBlinkPeriod(int blinkPeriod) {
        assert blinkPeriod > 0;
        assert caret != null;

        caret.setBlinkPeriod(blinkPeriod);
    }

    public int getBlinkVisible() {
        assert caret != null;

        return caret.getBlinkVisible();
    }

    public void setBlinkVisible(int blinkVisible) {
        assert blinkVisible > 0;
        assert caret != null;

        caret.setBlinkVisible(blinkVisible);
    }

    public Canvas getCanvas() {
        assert canvas != null;

        return canvas;
    }

    public Style getStyle() {
        assert style != null;

        return style;
    }

    public void setFocus(boolean focused) {
        assert canvas != null;

        canvas.setFocus(focused);
    }

    public boolean isEmpty() {
        return text.isEmpty();
    }
}
