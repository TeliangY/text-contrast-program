package app.components;

import javax.swing.JColorChooser;
import java.awt.Color;

public class ColorPicker {

    private String title = "Please select a color for text";
    private Color initialColor = Color.BLACK;

    public Color showColorPicker() {
        return JColorChooser.showDialog(null, title, initialColor);
    }
}
