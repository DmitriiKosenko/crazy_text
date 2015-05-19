package client.widget.textbox;

import client.utils.Font;
import client.utils.Utils;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.user.client.ui.Widget;

import java.util.HashMap;
import java.util.Map;

import static com.google.gwt.query.client.GQuery.$;

public class Style {

    // special
    public static final String STYLE_CURSOR = "cursor";
    public static final String CURSOR_TEXT = "text";

    public static final String STYLE_LETTER_SPACING = "letterSpacing"; // should be adjusted
    public static final Integer LETTER_SPACING_VALUE = 1;

    public static final String STYLE_BORDER_WIDTH = "borderWidth";
    public static final String STYLE_OUTLINE_STYLE = "outlineStyle";
    public static final String STYLE_MARGIN = "margin";
    public static final String OUTLINE_STYLE_NONE = "none";
    public static final String ZERO_SIZE = "0px";


    // style properties
    public static final String STYLE_WIDTH = "width";
    public static final String STYLE_HEIGHT = "height";
    public static final String STYLE_PADDING_TOP = "paddingTop";
    public static final String STYLE_PADDING_RIGHT = "paddingRight";
    public static final String STYLE_PADDING_LEFT = "paddingLeft";
    public static final String STYLE_PADDING_BOTTOM = "paddingBottom";


    // don't work now
    public static final String STYLE_TEXT_INDENT = "textIndent";

    public static final String STYLE_TEXT_TRANSFORM = "textTransform";
    public static final String TEXT_TRANSFORM_CAPITALIZE = "capitalize";
    public static final String TEXT_TRANSFORM_NONE = "none";
    public static final String TEXT_TRANSFORM_LOWERCASE = "lowercase";
    public static final String TEXT_TRANSFORM_UPPERCASE = "uppercase";

    public static final String STYLE_TEXT_DECORATION = "textDecoration";
    public static final String TEXT_DECORATION_BLINK = "blink";
    public static final String TEXT_DECORATION_LINE_THROUGH = "line-through";
    public static final String TEXT_DECORATION_NONE = "none";
    public static final String TEXT_DECORATION_OVERLINE = "overline";
    public static final String TEXT_DECORATION_UNDERLINE = "underline";

    public static final String STYLE_LINE_HEIGHT = "lineHeight";

    public static final String STYLE_TEXT_ALIGN = "textAlign"; // also as bidirectional
    public static final String TEXT_ALIGN_CENTER = "center";
    public static final String TEXT_ALIGN_JUSTIFY = "justify";
    public static final String TEXT_ALIGN_LEFT = "left";
    public static final String TEXT_ALIGN_RIGHT = "right";
    //

    protected Font font;

    protected Map<String, Integer> style = new HashMap<String, Integer>(){{
        put(STYLE_LETTER_SPACING, LETTER_SPACING_VALUE);
    }};

    protected Map<String, String> fontProperties = new HashMap<String, String>() {{
        put(Font.STYLE_FONT_STYLE, "");
        put(Font.STYLE_FONT_VARIANT, "");
        put(Font.STYLE_FONT_WEIGHT, "");
        put(Font.STYLE_FONT_SIZE, "");
        put(Font.STYLE_FONT_FAMILY, "");
        put(Font.STYLE_COLOR, "");
    }};

    private Widget wrapper;
    private Canvas canvas;

    public Style(Widget wrapper, Canvas canvas, String styleName) {
        assert wrapper != null;
        assert canvas != null;
        assert styleName != null;
        assert fontProperties != null;

        this.wrapper = wrapper;
        this.canvas = canvas;

        wrapper.setStyleName(styleName);

        collectProperties();
        preparePanelAndCanvas();

        recountForHighResolution();

        font = new Font(fontProperties);
    }


    protected void collectProperties() {
        collectProperty(STYLE_WIDTH);
        collectProperty(STYLE_HEIGHT);
        collectProperty(STYLE_PADDING_TOP);
        collectProperty(STYLE_PADDING_RIGHT);
        collectProperty(STYLE_PADDING_LEFT);
        collectProperty(STYLE_PADDING_BOTTOM);

        for (String property : fontProperties.keySet()) {
            fontProperties.put(property, $(wrapper).css(property));
        }
    }

    protected void collectProperty(final String property) {
        assert style != null;
        assert wrapper != null;
        assert property != null;

        style.put(property, Utils.getSize($(wrapper).css(property)));
    }

    protected void recountForHighResolution() {
        assert style != null;
        assert fontProperties != null;

        for (String propertyName : style.keySet()) {
            int property = Utils.resolution(style.get(propertyName));
            style.put(propertyName, property);
        }

        // get fontSize like a String, recount and put back like String
        int property = Utils.getSize(fontProperties.get(Font.STYLE_FONT_SIZE));
        property = Utils.resolution(property);
        fontProperties.put(Font.STYLE_FONT_SIZE, property + Utils.UNIT_PX);
    }

    protected void preparePanelAndCanvas() {
        int width = style.get(STYLE_WIDTH) + style.get(STYLE_PADDING_LEFT) + style.get(STYLE_PADDING_RIGHT);
        int height = style.get(STYLE_HEIGHT) + style.get(STYLE_PADDING_TOP) + style.get(STYLE_PADDING_BOTTOM);

        $(wrapper).css(STYLE_WIDTH, width + Utils.UNIT_PX);
        $(wrapper).css(STYLE_HEIGHT, height + Utils.UNIT_PX);

        $(wrapper).css(STYLE_PADDING_TOP, ZERO_SIZE);
        $(wrapper).css(STYLE_PADDING_LEFT, ZERO_SIZE);
        $(wrapper).css(STYLE_PADDING_BOTTOM, ZERO_SIZE);
        $(wrapper).css(STYLE_PADDING_RIGHT, ZERO_SIZE);


        $(canvas).css(STYLE_WIDTH, style.get(STYLE_WIDTH) + Utils.UNIT_PX);
        $(canvas).css(STYLE_HEIGHT, style.get(STYLE_HEIGHT) + Utils.UNIT_PX);

        $(canvas).css(STYLE_PADDING_TOP, style.get(STYLE_PADDING_TOP) + "");
        $(canvas).css(STYLE_PADDING_LEFT, style.get(STYLE_PADDING_LEFT) + "");
        $(canvas).css(STYLE_PADDING_BOTTOM, style.get(STYLE_PADDING_BOTTOM) + "");
        $(canvas).css(STYLE_PADDING_RIGHT, style.get(STYLE_PADDING_RIGHT) + "");

        $(canvas).css(STYLE_OUTLINE_STYLE, OUTLINE_STYLE_NONE);
        $(canvas).css(STYLE_BORDER_WIDTH, ZERO_SIZE);
        $(canvas).css(STYLE_MARGIN, ZERO_SIZE);
        $(canvas).css(STYLE_CURSOR, CURSOR_TEXT);
    }



    public int getPaddingLeft() {
        return style.get(STYLE_PADDING_LEFT);
    }

    public int getPaddingTop() {
        return style.get(STYLE_PADDING_TOP);
    }

    public int getPaddingRight() {
        return style.get(STYLE_PADDING_RIGHT);
    }

    public int getPaddingBottom() {
        return style.get(STYLE_PADDING_BOTTOM);
    }

    public int getWidth() {
        return style.get(STYLE_WIDTH);
    }

    public int getHeight() {
        return style.get(STYLE_HEIGHT);
    }

    public int getLetterSpacing() {
        return style.get(STYLE_LETTER_SPACING);
    }

    public Font getFont() {
        assert font != null;

        return font;
    }
}
