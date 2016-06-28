package com.input.text.crazy.client.widget.crazy;


import com.input.text.crazy.client.widget.textbox.DrawTextBoxBuilder;

public class DrawTextBoxFlipVerticalBuilder extends DrawTextBoxBuilder {

    public DrawTextBoxFlipVerticalBuilder(String styleName) {
        super(styleName);
    }

    protected void createTextView() {
        assert text != null;

        textView = new TextViewFlipVertical(text);
    }
}
