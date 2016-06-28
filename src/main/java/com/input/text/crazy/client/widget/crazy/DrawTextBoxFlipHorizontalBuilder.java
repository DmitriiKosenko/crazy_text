package com.input.text.crazy.client.widget.crazy;


import com.input.text.crazy.client.widget.textbox.DrawTextBoxBuilder;

public class DrawTextBoxFlipHorizontalBuilder extends DrawTextBoxBuilder {

    public DrawTextBoxFlipHorizontalBuilder(String styleName) {
        super(styleName);
    }

    protected void createTextView() {
        assert text != null;

        textView = new TextViewFlipHorizontal(text);
    }
}
