package app.components;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import app.types.Colors;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Hashtable;

public class RGBSliders {
    // mainPanel is the background of the program, and mainText is the text that changes color.
    // We need to pass these two instances when creating sliders, so that when sliders change value,
    // the colors of background and text also change.
    private JPanel mainPanel;
    private JLabel mainText;

    // Slider parameters
    private int sliderDirection = JSlider.HORIZONTAL;
    private int sliderMin = 0;
    private int sliderMax = 255;
    private int sliderFPS = 15;

    // Slider RGB color values
    private int sliderRed = 255;
    private int sliderGreen = 255;
    private int sliderBlue = 255;

    // Text RGB color values
    private int textRed = 0;
    private int textGreen = 0;
    private int textBlue = 0;

    // RGB sliders
    private JSlider rSlider;
    private JSlider gSlider;
    private JSlider bSlider;

    // Textfields to display RGB slider values
    private JTextField rTextField;
    private JTextField gTextField;
    private JTextField bTextField;

    // Square to display RBG slider current colors
    private JPanel rColorSquare;
    private JPanel gColorSquare;
    private JPanel bColorSquare;

    private JPanel slidersPanel;

    public RGBSliders(JPanel mainPanel, JLabel mainText) {
        this.mainPanel = mainPanel;
        this.mainText = mainText;
        initSliders();
    }

    /**
     * This function packs RGB sliders components together, such as labels, textfields that display values,
     * and square components that display current color.
     * 
     */
    private void initSliders() {
        // Main container with sliders
        slidersPanel = new JPanel();

        // Individual container for each RGB sliders
        JPanel rPanel = new JPanel();
        JPanel gPanel = new JPanel();
        JPanel bPanel = new JPanel();

        // Build RGB sliders
        rSlider = buildSliders(Colors.RED);
        gSlider = buildSliders(Colors.GREEN);
        bSlider = buildSliders(Colors.BLUE);

        // Text fields that display the values of sliders
        rTextField = new JTextField(3);
        gTextField = new JTextField(3);
        bTextField = new JTextField(3);
        rTextField.setText(String.valueOf(sliderRed));
        rTextField.setFont(new Font("sans-serif", Font.PLAIN, 26));
        gTextField.setText(String.valueOf(sliderGreen));
        gTextField.setFont(new Font("sans-serif", Font.PLAIN, 26));
        bTextField.setText(String.valueOf(sliderBlue));
        bTextField.setFont(new Font("sans-serif", Font.PLAIN, 26));

        // Add action event listener to text fields
        addTextFieldCL(rTextField, Colors.RED);
        addTextFieldCL(gTextField, Colors.GREEN);
        addTextFieldCL(bTextField, Colors.BLUE);

        // These squares display the current color for each RBG sliders
        rColorSquare = new JPanel();
        gColorSquare = new JPanel();
        bColorSquare = new JPanel();
        rColorSquare.setBackground(new Color(sliderRed, 0, 0));
        rColorSquare.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gColorSquare.setBackground(new Color(0, sliderGreen, 0));
        gColorSquare.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bColorSquare.setBackground(new Color(0, 0, sliderBlue));
        bColorSquare.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create RBG sliders labels
        JPanel rLabelPanel = new JPanel();
        JPanel gLabelPanel = new JPanel();
        JPanel bLabelPanel = new JPanel();
        rLabelPanel.setOpaque(false);
        gLabelPanel.setOpaque(false);
        bLabelPanel.setOpaque(false);
        JLabel rLabel = new JLabel("R");
        JLabel gLabel = new JLabel("G");
        JLabel bLabel = new JLabel("B");
        rLabel.setFont(new Font("sans-serif", Font.PLAIN, 26));
        gLabel.setFont(new Font("sans-serif", Font.PLAIN, 26));
        bLabel.setFont(new Font("sans-serif", Font.PLAIN, 26));
        rLabelPanel.add(rLabel);
        gLabelPanel.add(gLabel);
        bLabelPanel.add(bLabel);

        // Pack RGB labels, sliders, textfields and color squares into individual container
        rPanel.add(rLabelPanel);
        rPanel.add(rSlider);
        rPanel.add(rTextField);
        rPanel.add(rColorSquare);
        gPanel.add(gLabelPanel);
        gPanel.add(gSlider);
        gPanel.add(gTextField);
        gPanel.add(gColorSquare);
        bPanel.add(bLabelPanel);
        bPanel.add(bSlider);
        bPanel.add(bTextField);
        bPanel.add(bColorSquare);
        rPanel.setOpaque(false);
        gPanel.setOpaque(false);
        bPanel.setOpaque(false);

        // Set layout and background color of the main container, then add RBG sliders containers
        slidersPanel.setLayout(new BoxLayout(slidersPanel, BoxLayout.Y_AXIS));
        slidersPanel.setBackground(Color.WHITE);
        slidersPanel.add(rPanel);
        slidersPanel.add(gPanel);
        slidersPanel.add(bPanel);
    }

    /**
     * This function accepts a color enumeration value, and according to that value,
     * it returns corrresponding slider. For example, if the color parameter is
     * Colors.RED, then this function returns a slider that controls RED value
     * 
     * @param color Color enumeration, defined which color of sliders we want
     * @return A JSlider that controls the value of RGB
     */
    private JSlider buildSliders(Colors color) {
        JSlider slider = new JSlider(sliderDirection, sliderMin, sliderMax, sliderFPS);
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        JLabel minLabel = new JLabel(Integer.toString(sliderMin));
        JLabel maxLabel = new JLabel(Integer.toString(sliderMax));

        minLabel.setFont(new Font("sans-serif", Font.PLAIN, 18));
        maxLabel.setFont(new Font("sans-serif", Font.PLAIN, 18));
        labelTable.put(new Integer(sliderMin), minLabel);
        labelTable.put(new Integer(sliderMax), maxLabel);

        slider.setMajorTickSpacing(50);
        slider.setLabelTable(labelTable);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setOpaque(false);

        switch(color) {
            case RED:
                slider.setValue(sliderRed);
                addSliderCL(slider, Colors.RED);
                break;
            case GREEN:
                slider.setValue(sliderGreen);
                addSliderCL(slider, Colors.GREEN);
                break;
            case BLUE:
                slider.setValue(sliderBlue);
                addSliderCL(slider, Colors.BLUE);
                break;
            default:
                break;
        }
        return slider;
    }

    /**
     * This function accepts a RGB slider, and add a change listener to the slider.
     * When we change the value of the slider, the corresponding color value also changes.
     * 
     * @param slider RBG slider which we want to add a change listener for
     * @param color Color enumeration to define which RGB slider is being passed
     */
    private void addSliderCL(JSlider slider, Colors color) {
        switch(color) {
            case RED:
                slider.addChangeListener(new ChangeListener(){
            
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        JSlider slider = (JSlider) e.getSource();
                        sliderRed = slider.getValue();
                        mainPanel.setBackground(new Color(sliderRed, sliderGreen, sliderBlue));
                        textRed = sliderMax - sliderRed;
                        textGreen = sliderMax - sliderGreen;
                        textBlue = sliderMax - sliderBlue;
                        mainText.setForeground(new Color(textRed, textGreen, textBlue));
                        rTextField.setText(String.valueOf(sliderRed));
                        rColorSquare.setBackground(new Color(sliderRed, 0, 0));
                    }
                });
                break;

            case GREEN:
                slider.addChangeListener(new ChangeListener(){
                
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        JSlider slider = (JSlider) e.getSource();
                        sliderGreen = slider.getValue();
                        mainPanel.setBackground(new Color(sliderRed, sliderGreen, sliderBlue));
                        textRed = sliderMax - sliderRed;
                        textGreen = sliderMax - sliderGreen;
                        textBlue = sliderMax - sliderBlue;
                        mainText.setForeground(new Color(textRed, textGreen, textBlue));
                        gTextField.setText(String.valueOf(sliderGreen));
                        gColorSquare.setBackground(new Color(0, sliderGreen, 0));
                    }
                });
                break;

            case BLUE:
                slider.addChangeListener(new ChangeListener(){
                    
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        JSlider slider = (JSlider) e.getSource();
                        sliderBlue = slider.getValue();
                        mainPanel.setBackground(new Color(sliderRed, sliderGreen, sliderBlue));
                        textRed = sliderMax - sliderRed;
                        textGreen = sliderMax - sliderGreen;
                        textBlue = sliderMax - sliderBlue;
                        mainText.setForeground(new Color(textRed, textGreen, textBlue));
                        bTextField.setText(String.valueOf(sliderBlue));
                        bColorSquare.setBackground(new Color(0, 0, sliderBlue));
                    }
                });
                break;

            default:
                break;
        }
    }

    /**
     * This function adds action listener to the textfields that display color values for sliders.
     * When change values in textfields and hit enter, the color value changes.
     * 
     * @param tf Textfield that displays the RGB value for sliders
     * @param color Colors enumeration to define which RGB textfield we are adding listeners to
     */
    private void addTextFieldCL(JTextField tf, Colors color) {
        switch(color) {
            case RED:
                tf.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            sliderRed = Integer.parseInt(tf.getText());
                        } catch (NumberFormatException exception) {

                        } finally {
                            rTextField.setText(String.valueOf(sliderRed));
                            rSlider.setValue(sliderRed);
                        }
                    }
                });
                break;
            case GREEN:
                tf.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            sliderGreen = Integer.parseInt(tf.getText());
                        } catch (NumberFormatException exception) {

                        } finally {
                            gTextField.setText(String.valueOf(sliderGreen));
                            gSlider.setValue(sliderGreen);
                        }
                    }
                });
                break;
            case BLUE:
                tf.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            sliderBlue = Integer.parseInt(tf.getText());
                        } catch (NumberFormatException exception) {

                        } finally {
                            bTextField.setText(String.valueOf(sliderBlue));
                            bSlider.setValue(sliderBlue);
                        }
                    }
                });
                break;
            default:
                break;
        }
    }

    public int getSliderMax() {
        return sliderMax;
    }

    public int getSliderRed() {
        return sliderRed;
    }

    public int getSliderGreen() {
        return sliderGreen;
    }

    public int getSliderBlue() {
        return sliderBlue;
    }

    public JSlider getRSlider() {
        return rSlider;
    }

    public JSlider getGSlider() {
        return gSlider;
    }

    public JSlider getBSlider() {
        return bSlider;
    }

    public JPanel getSlidersPanel() {
        return slidersPanel;
    }
}