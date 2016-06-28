package com.input.text.crazy.client.widget.crazy;


import com.input.text.crazy.client.widget.textbox.Text;

public class TextViewFlipHorizontal extends TextViewFlipVertical {

    public TextViewFlipHorizontal(Text text) {
        super(text);
        symbolViewFlip = new SymbolViewFlipHorizontal(null);
    }
}
