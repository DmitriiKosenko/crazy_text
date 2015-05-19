package client.utils;

public interface Clipboard {

    public void put(String text);

    public String get();

    public boolean isEmpty();
}
