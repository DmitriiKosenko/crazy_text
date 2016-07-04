package com.input.text.crazy.client.widget.textbox.command;

import com.google.web.bindery.event.shared.Event;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;

import javax.annotation.Nullable;

public interface Command {

    CommandType getType();

    void setType(CommandType type);

    boolean isExecutable();

    boolean isUnExecutable();

    boolean execute() throws Exception;

    boolean unExecute();

    boolean isCompositeCommand();

    boolean add(Command command);

    Command prototype(DrawTextBox textBox, @Nullable Event event) throws Exception;
}
