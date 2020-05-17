package app.components;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

public class Text {
    private JLabel mainText;
    private int textRed;
    private int textGreen;
    private int textBlue;
    private int sliderRed;
    private int sliderGreen;
    private int sliderBlue;
    private int sliderMax;
    private ColorPicker colorPicker = new ColorPicker();

    public Text(JLabel mainText, int sliderMax, int sliderRed, int sliderGreen, int sliderBlue) {
        this.mainText = mainText;
        this.sliderMax = sliderMax;
        this.sliderRed = sliderRed;
        this.sliderGreen = sliderGreen;
        this.sliderBlue = sliderBlue;
        textRed = this.sliderMax - this.sliderRed;
        textGreen = this.sliderMax - this.sliderGreen;
        textBlue = this.sliderMax - this.sliderBlue;
    }

    /**
     * This function returns a container which contains "Text", this "Text" color is
     * contrast against to the background color.
     * 
     * @return A container that contains a Text, with contrast color against the
     *         background
     */
    public JPanel getTextPanel() {
        JPanel panel = new JPanel();
        JLabel description = new JLabel("Click textbox to change its color.");
        description.setFont(new Font("sans-serif", Font.PLAIN, 36));
        mainText.setFont(new Font("serif", Font.PLAIN, 148));
        mainText.setForeground(new Color(textRed, textGreen, textBlue));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        panel.add(description);
        panel.add(mainText);
        panel.setOpaque(false);

        // Click on the Text, a color picker will show up
        mainText.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Color color = colorPicker.showColorPicker();
                if (color == null) {
                    return;
                }
                textRed = color.getRed();
                textGreen = color.getGreen();
                textBlue = color.getBlue();
                mainText.setForeground(new Color(textRed, textGreen, textBlue));
            }
        });

        return panel;
    }
}