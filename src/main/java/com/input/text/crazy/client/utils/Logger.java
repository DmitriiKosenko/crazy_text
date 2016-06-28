package com.input.text.crazy.client.utils;

import com.google.gwt.logging.client.ConsoleLogHandler;

import java.util.logging.Level;
import java.util.logging.LogRecord;

public final class Logger {

    private Logger() {}

    protected static ConsoleLogHandler consoleLogHandler = new ConsoleLogHandler();

    public static void infoLog(String message) {
        consoleLogHandler.publish(new LogRecord(Level.INFO, message));
    }

    public static void errorLog(String message) {
        consoleLogHandler.publish(new LogRecord(Level.SEVERE, message));
    }
}
