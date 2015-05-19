package client.widget.crazy;

import client.widget.textbox.DrawTextBoxBuilder;

public class DrawTextBoxFlipVerticalBuilder extends DrawTextBoxBuilder {

    public DrawTextBoxFlipVerticalBuilder(String styleName) {
        super(styleName);
    }

    protected void createTextView() {
        assert text != null;

        textView = new TextViewFlipVertical(text);
    }
}
