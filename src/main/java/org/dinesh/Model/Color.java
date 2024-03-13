package org.dinesh.Model;


public enum Color {
    BLUE_SOLOON("blue"),
    RED_SOLOON("red"),
    PURPLE_SOLOON("purple"),
    WHITE_SOLOON("white");

    private final String colorValue;

    Color(String colorValue) {
        this.colorValue = colorValue;
    }

    public String getColorValue() {
        return colorValue;
    }

    public static Color getByColorValue(String value) {
        for (Color color : Color.values()) {
            if (color.name().equals(value)) {
                return color;
            }
        }
        throw new IllegalArgumentException("Invalid color value: " + value);
    }
}

