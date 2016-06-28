package com.input.text.crazy.client.widget.textbox.command;

import com.google.web.bindery.event.shared.Event;
import com.input.text.crazy.client.utils.Pair;
import com.input.text.crazy.client.utils.Utils;
import com.input.text.crazy.client.widget.textbox.DrawTextBox;
import com.input.text.crazy.client.widget.textbox.Symbol;

import javax.annotation.Nullable;
import java.util.List;

public class DeleteSelection extends DeleteCommand {

    protected Pair<Integer, Integer> positions;
    protected String removedSymbols;

    public DeleteSelection() {}

    public DeleteSelection(DrawTextBox textBox, @Nullable Event event) {
        super(textBox, event);

        positions = caret.getSelectionPositions();
    }

    @Override
    public boolean execute() {
        assert caret != null;

        int count = positions.getValue() - positions.getKey();
        assert count >= 1;

        List<Symbol> removedSymbols = text.remove(positions.getKey() + 1, count);
        assert removedSymbols != null;

        this.removedSymbols = Utils.textToString(removedSymbols);

        caret.setCursorPosition(positions.getKey());
        return true;
    }

    @Override
    public boolean unExecute() {
        assert removedSymbols.length() + text.size() <= text.getMaxLength();

        for (int i = 0; i < removedSymbols.length(); ++i) {

            int position = positions.getKey() + i + 1;
            Symbol symbol = text.createSymbol(removedSymbols.charAt(i));

            boolean result = text.add(position, symbol);
            assert result;
        }

        caret.setSelectionPositions(positions);

        return true;
    }
}