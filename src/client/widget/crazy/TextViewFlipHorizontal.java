package client.widget.crazy;

import client.widget.textbox.Text;

public class TextViewFlipHorizontal extends TextViewFlipVertical {

    public TextViewFlipHorizontal(Text text) {
        super(text);
        symbolViewFlip = new SymbolViewFlipHorizontal(null);
    }
}
