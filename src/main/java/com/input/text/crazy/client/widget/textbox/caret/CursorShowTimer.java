package com.input.text.crazy.client.widget.textbox.caret;

public class CursorShowTimer extends CursorTimer {

    private CursorTimer hideTimer;
    private int blinkVisible;

    public CursorShowTimer(Caret caret, CaretView view, CursorTimer hideTimer, int blinkVisible) {
        super(caret, view);

        assert hideTimer != null;
        assert blinkVisible > 0;

        this.hideTimer = hideTimer;
        this.blinkVisible = blinkVisible;
    }

    @Override
    public void run() {
        assert hideTimer != null;
        assert blinkVisible > 0;

        super.run();
        hideTimer.schedule(blinkVisible);
    }
}