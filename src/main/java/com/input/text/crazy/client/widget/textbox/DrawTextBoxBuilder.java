package com.input.text.crazy.client.widget.textbox;


import com.input.text.crazy.client.exceptions.UnSupportedWidgetException;
import com.input.text.crazy.client.menu.PopupMenu;
import com.input.text.crazy.client.utils.Rectangle;
import com.input.text.crazy.client.widget.textbox.caret.*;
import com.input.text.crazy.client.widget.textbox.command.*;
import com.input.text.crazy.client.widget.textbox.handlers.*;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.user.client.ui.RootPanel;

import java.util.HashMap;
import java.util.Map;

public class DrawTextBoxBuilder {

    protected String styleName;

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

    protected DrawTextBox textBox;

    protected Map<String, CommandType> popupMenuItems = new HashMap<String, CommandType>() {{
        put("copy", CommandType.COPY_COMMAND);
        put("paste", CommandType.PASTE_COMMAND);
        put("cut", CommandType.CUT_COMMAND);
        put("select all", CommandType.SELECT_ALL_COMMAND);
        // ---
        put("undo", CommandType.UNDO_COMMAND);
        put("redo", CommandType.REDO_COMMAND);
    }};

    public DrawTextBoxBuilder(String styleName) {
        assert styleName != null;

        this.styleName = styleName;
    }

    public void init(final DrawTextBox textBox) {
        assert textBox != null;

        this.textBox = textBox;

    }

    public void build() throws UnSupportedWidgetException {
        assert textBox != null;

        createCanvas();
        createText();
        createCaret();

        linkTextAndCaret();

        createDrawTextBoxView();

        createCommandHandler();
        createCommandCreator();

        createPopupMenu();

        createListeners();
    }



    protected void createCanvas() throws UnSupportedWidgetException {
        createCanvasObject();
        setCanvas();

        createStyle();

        adjustCanvas();
    }

    protected void createCanvasObject() throws UnSupportedWidgetException {
        canvas = Canvas.createIfSupported();
        if (canvas == null) {
            throw new UnSupportedWidgetException();
        }
    }

    protected void setCanvas() {
        assert textBox != null;
        assert canvas != null;

        textBox.add(canvas);
    }

    protected void createStyle() {
        assert textBox != null;
        assert canvas != null;
        assert styleName != null;

        style = new Style(textBox, canvas, styleName);
    }

    protected void adjustCanvas() {
        assert canvas != null;
        assert style != null;
        assert style.getFont() != null;
        assert style.getFont().getFont() != null;

        canvas.setCoordinateSpaceWidth(style.getWidth());
        canvas.setCoordinateSpaceHeight(style.getHeight());
        canvas.getContext2d().setFont(style.getFont().getFont());
        canvas.getContext2d().setFillStyle(style.getFont().getColor());
    }



    protected void createText() {
        createTextObject();
        createFlyweightContainer();
        initFlyweightContainer();
        initText();

        createTextView();
        initTextView();

        linkTextAndTextView();
    }

    protected void createTextObject() {
        assert style != null;

        text = new Text(new Rectangle(0, 0, style.getWidth(), style.getHeight()));
    }

    protected void createFlyweightContainer() {
        assert textBox != null;

        flyweightContainer = new SymbolFlyweightContainer(textBox);
    }

    protected void initFlyweightContainer() {
        assert flyweightContainer != null;
        assert style != null;

        flyweightContainer.init(style);
    }

    protected void initText() {
        assert text != null;
        assert flyweightContainer != null;

        text.init(flyweightContainer);
    }

    protected void createTextView() {
        assert text != null;

        textView = new TextView(text);
    }

    protected void initTextView() {
        assert style != null;

        textView.init(style);
    }

    protected void linkTextAndTextView() {
        assert text != null;
        assert textView != null;

        text.setView(textView);
        textView.setText(text);
    }



    protected void createCaret() {
        createCaretObject();

        createCursor();
        createCursorStyle();
        initCursorStyle();
        setCursorStyleToCursor();
        initCaretByCursor();

        createSelection();
        createSelectionStyle();
        initSelectionStyle();
        setSelectionStyleToSelection();
        initCaretBySelection();

        setDefaultCaretState();
    }

    protected void createCaretObject() {
        caret = new Caret();
    }

    protected void createCursor() {
        assert caret != null;

        cursor = new Cursor(caret);
    }

    protected void createCursorStyle() {
        cursorStyle = new CursorStyle();
    }

    protected void initCursorStyle() {
        assert cursorStyle != null;
        assert style != null;

        cursorStyle.init(style);
    }

    protected void setCursorStyleToCursor() {
        assert cursor != null;
        assert cursorStyle != null;

        cursor.setCaretStyle(cursorStyle);
    }

    protected void initCaretByCursor() {
        assert caret != null;
        assert cursor != null;

        caret.initCursor(cursor);
    }

    protected void createSelection() {
        assert caret != null;

        selection = new Selection(caret);
    }

    protected void createSelectionStyle() {
        selectionStyle = new SelectionStyle();
    }

    protected void initSelectionStyle() {
        assert selectionStyle != null;
        assert style != null;

        selectionStyle.init(style);
    }

    protected void setSelectionStyleToSelection() {
        assert selection != null;
        assert selectionStyle != null;

        selection.setCaretStyle(selectionStyle);
    }

    protected void initCaretBySelection() {
        assert caret != null;
        assert selection != null;

        caret.initSelection(selection);
    }

    protected void setDefaultCaretState() {
        assert caret != null;
        assert cursor != null;

        caret.setState(cursor);
    }



    protected void linkTextAndCaret() {
        assert text!= null;
        assert caret != null;

        text.setCaret(caret);
        caret.setText(text);
    }



    protected void createDrawTextBoxView() {
        createDrawTextBoxViewObject();
        setDrawTextBoxPartsToView();
    }

    protected void createDrawTextBoxViewObject() {
        assert textBox != null;

        view = new DrawTextBoxView(textBox);
    }

    protected void setDrawTextBoxPartsToView() {
        assert view != null;
        assert caret != null;
        assert text != null;

        view.push(caret);
        view.push(text);
    }



    protected void createCommandHandler() {
        commandHandler = new CommandHandler();
    }

    protected void createCommandCreator() {
        assert commandHandler != null;

        commandCreator = new CommandCreator(commandHandler);
    }



    protected void createPopupMenu() {
        createPopupMenuObject();
        // init menu items
        setPopupMenuDefaultState();
    }

    protected void createPopupMenuObject() {
        popupMenu = new PopupMenu();
    }

    protected void setPopupMenuDefaultState() {
        assert popupMenu != null;

        popupMenu.hide();
    }



    protected void createListeners() {
        assert canvas != null;
        assert textBox != null;
        assert commandHandler != null;
        assert commandCreator != null;
        assert popupMenu != null;

        canvas.addMouseDownHandler(new MouseDownHandler(textBox, commandHandler, commandCreator));
        canvas.addMouseUpHandler(new MouseUpHandler(textBox, commandHandler, commandCreator));

        canvas.addDoubleClickHandler(new DoubleClickHandler(textBox, commandHandler, commandCreator));

        canvas.addFocusHandler(new FocusHandler(textBox, commandHandler, commandCreator));
        canvas.addBlurHandler(new BlurHandler(textBox, commandHandler, commandCreator));

        canvas.addKeyDownHandler(new KeyDownHandler(textBox, commandHandler, commandCreator));
        canvas.addKeyUpHandler(new KeyUpHandler(textBox, commandHandler, commandCreator));
        canvas.addKeyPressHandler(new KeyPressHandler(textBox, commandHandler, commandCreator));

        canvas.addDomHandler(new ContextMenuHandler(textBox, commandHandler, commandCreator),
                ContextMenuEvent.getType());
        HidePopupMenuHandler hideHandler = new HidePopupMenuHandler(popupMenu);
        RootPanel.get().addDomHandler(hideHandler, ClickEvent.getType());
        RootPanel.get().addDomHandler(hideHandler, ContextMenuEvent.getType());
    }





    public Style getStyle() {
        assert style != null;
        return style;
    }

    public Canvas getCanvas() {
        assert canvas != null;
        return canvas;
    }

    public Text getText() {
        assert text != null;
        return text;
    }

    public TextView getTextView() {
        assert textView != null;
        return textView;
    }

    public SymbolFlyweightContainer getFlyweightContainer() {
        assert flyweightContainer != null;
        return flyweightContainer;
    }

    public Caret getCaret() {
        assert caret != null;
        return caret;
    }

    public Cursor getCursor() {
        assert cursor != null;
        return cursor;
    }

    public CursorStyle getCursorStyle() {
        assert cursorStyle != null;
        return cursorStyle;
    }

    public Selection getSelection() {
        assert selection != null;
        return selection;
    }

    public SelectionStyle getSelectionStyle() {
        assert selectionStyle != null;
        return selectionStyle;
    }

    public CommandHandler getCommandHandler() {
        assert commandHandler != null;
        return commandHandler;
    }

    public CommandCreator getCommandCreator() {
        assert commandCreator != null;
        return commandCreator;
    }

    public PopupMenu getPopupMenu() {
        assert popupMenu != null;
        return popupMenu;
    }

    public Map<String, CommandType> getPopupMenuItems() {
        assert popupMenuItems != null;
        return popupMenuItems;
    }

    public DrawTextBoxView getView() {
        assert view != null;
        return view;
    }

}
