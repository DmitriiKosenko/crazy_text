package com.input.text.crazy.client.utils;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class Font {

    public static final String STYLE_FONT_STYLE = "fontStyle";
    public static final String STYLE_FONT_VARIANT = "fontVariant";
    public static final String STYLE_FONT_WEIGHT = "fontWeight";
    public static final String STYLE_FONT_SIZE = "fontSize";
    public static final String STYLE_FONT_FAMILY = "fontFamily";

    public static final String STYLE_COLOR = "color";


    protected Map<String, String> features = new HashMap<String, String>() {{
        put(STYLE_FONT_STYLE, "normal");
        put(STYLE_FONT_VARIANT, "normal");
        put(STYLE_FONT_WEIGHT, "normal");
        put(STYLE_FONT_SIZE, "13px");
        put(STYLE_FONT_FAMILY, "Arial, sans-serif");

        put(STYLE_COLOR, "grey");
    }};

    protected FontMetrics fontMetrics;

    protected int fontSize;

    public Font(@Nullable final Map<String, String> features) {
        assert this.features != null;

        if (features != null) {
            for (String feature : features.keySet()) {
                String value = features.get(feature);
                if (value != null) {
                    this.features.put(feature, value);
                }
            }

            fontSize = Utils.getSize(features.get(STYLE_FONT_SIZE));
            fontMetrics = new FontMetrics(this);
        }
    }


    public String getFontStyle() {
        return features.get(STYLE_FONT_STYLE);
    }

    public String getFontVariant() {
        return features.get(STYLE_FONT_VARIANT);
    }

    public String getFontWeight() {
        return features.get(STYLE_FONT_WEIGHT);
    }

    public int getFontSize() {
        return fontSize;
    }

    public String getFontFamily() {
        return features.get(STYLE_FONT_FAMILY);
    }

    public String getColor() {
        return features.get(STYLE_COLOR);
    }

    public FontMetrics getFontMetrics() {
        assert fontMetrics!= null;

        return fontMetrics;
    }

    public String getFont() {
        assert features != null;

        return features.get(STYLE_FONT_STYLE) + " " +
                features.get(STYLE_FONT_VARIANT) + " " +
                features.get(STYLE_FONT_WEIGHT) + " " +
                fontSize + Utils.UNIT_PX + " " +
                features.get(STYLE_FONT_FAMILY);
    }

    @Override
    public int hashCode() {
        int result = features.hashCode();
        result = 31 * result + fontMetrics.hashCode();
        result = 31 * result + fontSize;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Font)) return false;

        Font font = (Font) o;

        if (fontSize != font.fontSize) return false;
        if (!features.equals(font.features)) return false;
        if (!fontMetrics.equals(font.fontMetrics)) return false;

        return true;
    }
}
