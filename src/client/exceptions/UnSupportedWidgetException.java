package client.exceptions;

public class UnSupportedWidgetException extends Exception {

    private final static String unsupportedBrowser = "Your browser does not support the HTML5 Canvas";

    public UnSupportedWidgetException() {}

    public String getMessage() {
        return unsupportedBrowser;
    }
}
