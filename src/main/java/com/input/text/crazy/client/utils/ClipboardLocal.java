package com.input.text.crazy.client.utils;

public class ClipboardLocal implements Clipboard {

    protected static Clipboard instance;
    protected String clipboard;

    public static Clipboard getInstance() {
        if (instance == null) {
            instance = new ClipboardLocal();
        }
        return instance;
    }

    @Override
    public void put(final String text) {
        assert text != null;

        clipboard = text;
    }

    @Override
    public String get() {
        return clipboard;
    }

    @Override
    public boolean isEmpty() {
        return clipboard == null || clipboard.isEmpty();
    }
}
