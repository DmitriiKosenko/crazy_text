package com.input.text.crazy.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.input.text.crazy.client.exceptions.UnSupportedWidgetException;
import com.input.text.crazy.client.utils.Logger;
import com.input.text.crazy.client.widget.crazy.DrawTextBoxFlipHorizontalBuilder;
import com.input.text.crazy.client.widget.crazy.DrawTextBoxFlipVerticalBuilder;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;
import com.input.text.crazy.client.widget.textbox.DrawTextBoxBuilder;
import com.input.text.crazy.client.widget.textbox.actions.Bindings;
import com.input.text.crazy.client.widget.textbox.actions.DefaultBindings;
import com.input.text.crazy.client.widget.textbox.actions.MacBindings;


public class Demo implements EntryPoint {

    static {

        String platform = Window.Navigator.getPlatform();

        if (platform.matches("Mac(.*)")) {
            Bindings.keyBindings.putAll(MacBindings.keyBindings);
            Bindings.mouseBindings.putAll(MacBindings.mouseBindings);
        } else {
            Bindings.keyBindings.putAll(DefaultBindings.keyBindings);
            Bindings.mouseBindings.putAll(DefaultBindings.mouseBindings);
        }
    }

    public void onModuleLoad() {

        Panel panel;

        try {
            Panel panel1 = createDescriptionPanel();
            Panel panel2 = createDemoPanel();
            panel = new VerticalPanel();
            panel.setStyleName("main");
            panel.add(panel1);
            panel.add(panel2);

        } catch (UnSupportedWidgetException e) {
            Logger.errorLog(e.getMessage());
            panel = createErrorPanel("Sorry, " + e.getMessage());
        }

        RootPanel.get().add(panel);
    }

    protected Panel createDemoPanel() throws UnSupportedWidgetException {
        DrawTextBoxBuilder builder1 = new DrawTextBoxBuilder("text-box");
        DrawTextBoxBuilder builder2 = new DrawTextBoxFlipVerticalBuilder("text-box");
        DrawTextBoxBuilder builder3 = new DrawTextBoxFlipHorizontalBuilder("text-box");
        Widget widget1 = new DrawTextBox(builder1);
        Widget widget2 = new DrawTextBox(builder2);
        Widget widget3 = new DrawTextBox(builder3);

        Label label1 = new Label("Simple draw textBox");
        Label label2 = new Label("Draw textBox with vertical symbol flip");
        Label label3 = new Label("Draw textBox with horizontal symbol flip");
        label1.setStyleName("header");
        label2.setStyleName("header");
        label3.setStyleName("header");

        VerticalPanel panel = new VerticalPanel();
        panel.setStyleName("main");

        panel.add(label1);
        panel.add(widget1);
        panel.add(label2);
        panel.add(widget2);
        panel.add(label3);
        panel.add(widget3);

        return panel;
    }

    protected Panel createDescriptionPanel() {
        VerticalPanel panel = new VerticalPanel();
        panel.setStyleName("main");

        Label label1 = new Label("This is implementation text input on canvas");
        Label label2 = new Label(" - You can type text");
        Label label3 = new Label(" - You can navigate on text with keyboard and mouse");
        Label label4 = new Label(" - You can select text with keyboard and mouse via shift + click");
        Label label5 = new Label(" - You can copy, cut, paste text pieces between inputs");
        Label label6 = new Label("via short cut and context menu");

        label1.setStyleName("description-item");
        label2.setStyleName("description-item");
        label3.setStyleName("description-item");
        label4.setStyleName("description-item");
        label5.setStyleName("description-item");
        label6.setStyleName("description-item");

        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);

        return panel;
    }

    protected Panel createErrorPanel(String message) {
        VerticalPanel panel = new VerticalPanel();
        panel.setStyleName("main");

        Label label1 = new Label(message);
        panel.add(label1);

        return panel;
    }
}
