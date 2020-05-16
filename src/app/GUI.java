package app;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.awt.FlowLayout;

public class GUI {
    
    // General program components
    ImageIcon icon = new ImageIcon("src/resources/icon.png");
    private String title = "Use sliders or click window to choose window color.";
    private JFrame mainFrame = new JFrame();
    private JLabel mainText = new JLabel();
    private JPanel mainPanel;
    private JMenuBar menuBar;

    // Slider parameters
    private int sliderDirection = JSlider.HORIZONTAL;
    private int sliderMin = 0;
    private int sliderMax = 255;
    private int sliderFPS = 15;

    // Slider RGB color values
    private int red = 255;
    private int green = 255;
    private int blue = 255;

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

    // Text message displayed on About window
    private String aboutText = "<html>Use the sliders to change the window color, <br /> or click on the window to use a color picker. <br /><br /> The text will change to the window's complementary color. <br /><br /> Click on the text to manually change it. <br /> Use the sliders to change the window color, <br /> or click on the window to use a color picker.</html>";

    /**
     * Constructor that initializes everything
     */
    public GUI() {
        // Create a main container
        mainPanel = new JPanel();

        // Divide application into left side, and right side
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        // Add sliders to left container, and the contrast text to the right container
        leftPanel.add(getSliders());
        leftPanel.setOpaque(false);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(getTextPanel());
        rightPanel.add(getButton("Quit"));
        rightPanel.setOpaque(false);

        // The background color will change based on red, green and blue value
        mainPanel.setBackground(new Color(red, green, blue));
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 20));
        mainFrame.setIconImage(icon.getImage());
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle(this.title);
        mainFrame.setJMenuBar(getMenuBar());
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    /**
     * 
     * @param name The text displayed on the button
     * @return A JButton component
     */
    private JComponent getButton(String name) {
        JPanel panel = new JPanel();
        JButton button = new JButton(name);
        button.setFont(new Font("sans-serif", Font.PLAIN, 36));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 40));
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(button);
        panel.setOpaque(false);
        return panel;
    }

    /**
     * This function returns a container which contains "Text", this "Text" color is contrast
     * against to the background color.
     * 
     * @return A container that contains a Text, with contrast color against the background
     */
    private JPanel getTextPanel() {
        JPanel panel = new JPanel();
        JLabel description = new JLabel("Click textbox to change its color.");
        this.mainText = new JLabel("Text");
        description.setFont(new Font("sans-serif", Font.PLAIN, 36));
        mainText.setFont(new Font("serif", Font.PLAIN, 148));
        mainText.setForeground(new Color(255 - red, 255 - green, 255 - blue));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        panel.add(description);
        panel.add(mainText);
        panel.setOpaque(false);
        return panel;
    }

    /**
     * This function packs RGB sliders components together, such as labels, textfields that display values,
     * and square components that display current color. Then, return all of them in a single container.
     * 
     * @return A container with three RGB sliders
     */
    private JPanel getSliders() {
        // Main container with sliders
        JPanel panel = new JPanel();

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
        rTextField.setText(String.valueOf(red));
        rTextField.setFont(new Font("sans-serif", Font.PLAIN, 36));
        gTextField.setText(String.valueOf(green));
        gTextField.setFont(new Font("sans-serif", Font.PLAIN, 36));
        bTextField.setText(String.valueOf(blue));
        bTextField.setFont(new Font("sans-serif", Font.PLAIN, 36));

        // These squares display the current color for each RBG sliders
        rColorSquare = new JPanel();
        gColorSquare = new JPanel();
        bColorSquare = new JPanel();
        rColorSquare.setBackground(new Color(red, 0, 0));
        rColorSquare.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gColorSquare.setBackground(new Color(0, green, 0));
        gColorSquare.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bColorSquare.setBackground(new Color(0, 0, blue));
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
        rLabel.setFont(new Font("sans-serif", Font.PLAIN, 36));
        gLabel.setFont(new Font("sans-serif", Font.PLAIN, 36));
        bLabel.setFont(new Font("sans-serif", Font.PLAIN, 36));
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
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.add(rPanel);
        panel.add(gPanel);
        panel.add(bPanel);

        return panel;
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
                slider.setValue(red);
                addSliderCL(slider, Colors.RED);
                break;
            case GREEN:
                slider.setValue(green);
                addSliderCL(slider, Colors.GREEN);
                break;
            case BLUE:
                slider.setValue(blue);
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
                        red = slider.getValue();
                        mainPanel.setBackground(new Color(red, green, blue));
                        mainText.setForeground(new Color(255 - red, 255 - green, 255 - blue));
                        rTextField.setText(String.valueOf(red));
                        rColorSquare.setBackground(new Color(red, 0, 0));
                    }
                });
                break;

            case GREEN:
                slider.addChangeListener(new ChangeListener(){
                
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        JSlider slider = (JSlider) e.getSource();
                        green = slider.getValue();
                        mainPanel.setBackground(new Color(red, green, blue));
                        mainText.setForeground(new Color(255 - red, 255 - green, 255 - blue));
                        gTextField.setText(String.valueOf(green));
                        gColorSquare.setBackground(new Color(0, green, 0));
                    }
                });
                break;

            case BLUE:
                slider.addChangeListener(new ChangeListener(){
                    
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        JSlider slider = (JSlider) e.getSource();
                        blue = slider.getValue();
                        mainPanel.setBackground(new Color(red, green, blue));
                        mainText.setForeground(new Color(255 - red, 255 - green, 255 - blue));
                        bTextField.setText(String.valueOf(blue));
                        bColorSquare.setBackground(new Color(0, 0, blue));
                    }
                });
                break;

            default:
                break;
        }
    }

    /**
     * This function returns a menubar component
     * 
     * @return A menu bar component
     */
    private JMenuBar getMenuBar() {
        JMenu menu = new JMenu("Help");
        JMenuItem menuItem = new JMenuItem("About");
        JLabel aboutContent = new JLabel(aboutText);
        JPanel contentPanel = new JPanel();
        this.menuBar = new JMenuBar();

        menuBar.add(menu);
        menu.add(menuItem);
        contentPanel.add(aboutContent);
        menu.setFont(new Font("sans-serif", Font.PLAIN, 24));
        menuItem.setFont(new Font("sans-serif", Font.PLAIN, 24));
        aboutContent.setFont(new Font("sans-serif", Font.LAYOUT_LEFT_TO_RIGHT, 26));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));

        // When click on About menu item, pop up a new window with information message
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JOptionPane.showOptionDialog(
                    null, 
                    contentPanel, 
                    "About", JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.PLAIN_MESSAGE, 
                    null, 
                    new Object[]{}, 
                    null
                );
            }
        });

        return menuBar;
    }
}
