package com.nnpg.argon.gui.themes.argon;

import com.nnpg.argon.gui.widgets.WRandomBetweenSlider;
import meteordevelopment.meteorclient.gui.renderer.GuiRenderer;
import meteordevelopment.meteorclient.utils.render.color.Color;

public class WArgonRandomBetweenSlider extends WRandomBetweenSlider {
    private final double min, max;
    private final int decimalPlaces;

    private boolean hoverMin, hoverMax;
    private boolean draggingMin, draggingMax;

    public WArgonRandomBetweenSlider(double valueMin, double valueMax, double min, double max, int decimalPlaces) {
        super(valueMin, valueMax, min, max, decimalPlaces);
        this.min = min;
        this.max = max;
        this.decimalPlaces = decimalPlaces;
    }

    @Override
    protected void onRender(GuiRenderer r, double mouseX, double mouseY, double delta) {
        Color track = new Color(80, 80, 80, 255);
        Color range = theme.textColor();
        Color handle = theme.textSecondaryColor();
        Color active = theme.textColor();

        // Track
        r.quad(x, y + height / 2 - 1, width, 2, track);

        double minX = getMinHandleX();
        double maxX = getMaxHandleX();

        // Active range
        if (maxX > minX) {
            r.quad(x + minX, y + height / 2 - 1, maxX - minX, 2, range);
        }

        // Handles
        r.quad(x + minX - 3, y + height / 2 - 6, 6, 12,
            hoverMin || draggingMin ? active : handle);

        r.quad(x + maxX - 3, y + height / 2 - 6, 6, 12,
            hoverMax || draggingMax ? active : handle);

        // Values
        String minText = format(getMin());
        String maxText = format(getMax());

        double textY = y + height + 2;
        r.text(minText, x + minX, textY, theme.textColor(), false);
        r.text(maxText, x + maxX, textY, theme.textColor(), false);
    }

    private String format(double value) {
        return String.format("%." + decimalPlaces + "f", value);
    }

    private double getMinHandleX() {
        return (getMin() - min) / (max - min) * width;
    }

    private double getMaxHandleX() {
        return (getMax() - min) / (max - min) * width;
    }
}
