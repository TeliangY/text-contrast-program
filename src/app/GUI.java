package app;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.awt.FlowLayout;
import java.awt.Graphics;

public class GUI {
    
    private String title = "Use sliders or click window to choose window color.";
    private JFrame mainFrame = new JFrame();
    private JPanel mainPanel = new JPanel();
    private JLabel mainText = new JLabel();
    private Color bgColor = new Color(84, 213, 233);
    private Color textBgColor = new Color(84, 213, 233);
    private int sliderDirection = JSlider.HORIZONTAL;
    private int sliderMin = 0;
    private int sliderMax = 255;
    private int sliderFPS = 15;

    private int red = 255;
    private int green = 255;
    private int blue = 255;

    private JSlider rSlider = new JSlider();
    private JSlider gSlider = new JSlider();
    private JSlider bSlider = new JSlider();

    private JTextField rTextField = new JTextField();
    private JTextField gTextField = new JTextField();
    private JTextField bTextField = new JTextField();

    private JPanel rColorSquare = new JPanel();
    private JPanel gColorSquare = new JPanel();
    private JPanel bColorSquare = new JPanel();

    private JMenuBar menuBar = null;

    private String aboutText = "<html>Use the sliders to change the window color, <br /> or click on the window to use a color picker. <br /><br /> The text will change to the window's complementary color. <br /><br /> Click on the text to manually change it. <br /> Use the sliders to change the window color, <br /> or click on the window to use a color picker.</html>";

    public GUI() {
        mainPanel.setBackground(new Color(red, green, blue));
        // mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.setLayout(new FlowLayout(5));

        JPanel leftPanel = new JPanel();
        leftPanel.add(getSliders());
        leftPanel.setOpaque(false);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(getTextPanel());
        rightPanel.add(getButton("Quit"));
        rightPanel.setOpaque(false);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        ImageIcon icon = new ImageIcon("src/resources/java-icon.png");

        mainFrame.setIconImage(icon.getImage());
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle(this.title);
        mainFrame.setJMenuBar(getMenuBar());
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

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

    private JComponent getTextPanel() {
        JPanel panel = new JPanel();
        JPanel textPanel = new JPanel();
        JLabel description = new JLabel("Click textbox to change its color.");
        JLabel text = new JLabel("Text");

        description.setFont(new Font("sans-serif", Font.PLAIN, 36));
        text.setFont(new Font("serif", Font.PLAIN, 108));
        text.setForeground(new Color(255 - red, 255 - green, 255 - blue));

        textPanel.add(text);
        // textPanel.setBackground(textBgColor);
        textPanel.setOpaque(false);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(description);
        panel.add(textPanel);
        panel.setOpaque(false);
 
        this.mainText = text;
        return panel;
    }

    private JComponent getSliders() {
        JPanel panel = new JPanel();

        rSlider = buildSliders("red");
        gSlider = buildSliders("green");
        bSlider = buildSliders("blue");

        addSliderCL(rSlider, "red");
        addSliderCL(gSlider, "green");
        addSliderCL(bSlider, "blue");

        JPanel rPanel = new JPanel();
        JPanel gPanel = new JPanel();
        JPanel bPanel = new JPanel();

        rTextField = new JTextField(3);
        gTextField = new JTextField(3);
        bTextField = new JTextField(3);

        rTextField.setText(String.valueOf(red));
        gTextField.setText(String.valueOf(green));
        bTextField.setText(String.valueOf(blue));

        // addTextFieldCL(rTextField, "red");
        // addTextFieldCL(gTextField, "green");
        // addTextFieldCL(bTextField, "blue");

        rColorSquare.setBackground(new Color(red, 0, 0));
        gColorSquare.setBackground(new Color(0, green, 0));
        bColorSquare.setBackground(new Color(0, 0, blue));

        JLabel rLabel = new JLabel("R");
        JLabel gLabel = new JLabel("G");
        JLabel bLabel = new JLabel("B");

        rLabel.setFont(new Font("sans-serif", Font.PLAIN, 24));
        gLabel.setFont(new Font("sans-serif", Font.PLAIN, 24));
        bLabel.setFont(new Font("sans-serif", Font.PLAIN, 24));

        JPanel rLabelPanel = new JPanel();
        JPanel gLabelPanel = new JPanel();
        JPanel bLabelPanel = new JPanel();

        rLabelPanel.setOpaque(false);
        gLabelPanel.setOpaque(false);
        bLabelPanel.setOpaque(false);

        rLabelPanel.add(rLabel);
        gLabelPanel.add(gLabel);
        bLabelPanel.add(bLabel);

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

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setBackground(Color.WHITE);
        panel.add(rPanel);
        panel.add(gPanel);
        panel.add(bPanel);

        return panel;
    }

    private JSlider buildSliders(String color) {
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        JLabel minLabel = new JLabel("0");
        JLabel maxLabel = new JLabel("255");
        labelTable.put(new Integer(sliderMin), minLabel);
        labelTable.put(new Integer(sliderMax), maxLabel);

        JSlider slider = new JSlider(sliderDirection, sliderMin, sliderMax, sliderFPS);
        slider.setMajorTickSpacing(50);
        slider.setLabelTable(labelTable);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setOpaque(false);
        
        switch(color) {
            case "red":
                slider.setValue(red);
                break;
            case "green":
                slider.setValue(green);
                break;
            case "blue":
                slider.setValue(blue);
                break;
            default:
                break;
        }

        return slider;
    }

    // Add slider change listener
    private void addSliderCL(JSlider slider, String color) {
        switch(color) {
            case "red":
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
            case "green":
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
            case "blue":
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

    private void addTextFieldCL(JTextField tf, String color) {
        switch(color) {
            case "red":
                tf.getDocument().addDocumentListener(new DocumentListener(){
                
                    @Override
                    public void removeUpdate(DocumentEvent e) {
                       
                    }
                
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        // changeColor();
                    }
                
                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        changeColor();
                    }

                    private void changeColor() {
                        try {
                            red = Integer.parseInt(tf.getText());
                        } catch (NumberFormatException e) {
                            
                        } finally {
                            rTextField.setText(String.valueOf(red));
                            rSlider.setValue(red);
                        }
                    }
                });
                break;
            case "green":
                tf.getDocument().addDocumentListener(new DocumentListener(){
                    
                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        
                    }
                
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        // changeColor();
                    }
                
                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        changeColor();
                    }

                    private void changeColor() {
                        try {
                            green = Integer.parseInt(tf.getText());
                        } catch (NumberFormatException e) {
                            
                        } finally {
                            gTextField.setText(String.valueOf(green));
                            gSlider.setValue(green);
                        }
                    }
                });
                break;
            case "blue":
                tf.getDocument().addDocumentListener(new DocumentListener(){
                    
                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        
                    }
                
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        // changeColor();
                    }
                
                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        changeColor();
                    }

                    private void changeColor() {
                        try {
                            blue = Integer.parseInt(tf.getText());
                        } catch (NumberFormatException e) {
                            
                        } finally {
                            bTextField.setText(String.valueOf(blue));
                            bSlider.setValue(blue);
                        }
                    }
                });
                break;
            default:
                break;
        }
    }

    private JMenuBar getMenuBar() {
        menuBar = new JMenuBar();
        JMenu menu = new JMenu("Help");
        JMenuItem menuItem = new JMenuItem("About");
        menu.add(menuItem);
        menuItem.setFont(new Font("sans-serif", Font.PLAIN, 24));
        menu.setFont(new Font("sans-serif", Font.PLAIN, 24));
        menuBar.add(menu);

        JLabel aboutContent = new JLabel(aboutText);
        JPanel contentPanel = new JPanel();
        aboutContent.setFont(new Font("sans-serif", Font.LAYOUT_LEFT_TO_RIGHT, 16));
        contentPanel.add(aboutContent);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JOptionPane.showMessageDialog(mainFrame, contentPanel, "About", JOptionPane.PLAIN_MESSAGE);
            }
        });

        return menuBar;
    }
}