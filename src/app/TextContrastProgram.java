package app;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.MouseInputAdapter;

import app.components.ColorPicker;
import app.components.RGBSliders;
import app.components.Text;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;

public class TextContrastProgram {
    // General program components
    ImageIcon icon = new ImageIcon("src/resources/icon.png");
    private String title = "Use sliders or click window to choose window color.";
    private JFrame mainFrame = new JFrame();
    private JLabel mainText = new JLabel("Text");
    private JPanel mainPanel = new JPanel();
    private JMenuBar menuBar;
    private ColorPicker colorPicker = new ColorPicker();

    // Slider parameters
    private RGBSliders rgbSliders = new RGBSliders(mainPanel, mainText);
    private int sliderMax = rgbSliders.getSliderMax();

    // RGB sliders
    private JSlider rSlider = rgbSliders.getRSlider();
    private JSlider gSlider = rgbSliders.getGSlider();
    private JSlider bSlider = rgbSliders.getBSlider();

    private Text textComponent = new Text(mainText, sliderMax, rgbSliders.getSliderRed(), rgbSliders.getSliderGreen(), rgbSliders.getSliderBlue());

    // Text message displayed on About window
    private String aboutText = "<html>Use the sliders to change the window color, <br /> or click on the window to use a color picker. <br /><br /> The text will change to the window's complementary color. <br /><br /> Click on the text to manually change it. <br /> Use the sliders to change the window color, <br /> or click on the window to use a color picker.</html>";

    /**
     * Constructor that initializes everything
     */
    public TextContrastProgram() {
        // Click on the background, a color pick will show up for background color
        mainPanel.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Color color = colorPicker.showColorPicker();
                if (color == null) {
                    return;
                }
                rSlider.setValue(color.getRed());
                gSlider.setValue(color.getGreen());
                bSlider.setValue(color.getBlue());
                mainPanel.setBackground(new Color(rgbSliders.getSliderRed(), rgbSliders.getSliderGreen(), rgbSliders.getSliderBlue()));
            }
        });

        // Divide application into left side, and right side
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        // Add sliders to left container, and the contrast text to the right container
        leftPanel.add(rgbSliders.getSlidersPanel());
        leftPanel.setOpaque(false);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(textComponent.getTextPanel());
        rightPanel.add(getQuitButton());
        rightPanel.setOpaque(false);

        // The background color will change based on red, green and blue value
        mainPanel.setBackground(new Color(rgbSliders.getSliderRed(), rgbSliders.getSliderGreen(), rgbSliders.getSliderBlue()));
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
     * @return A JButton in a contianer, and when clicked, the program exits
     */
    private JPanel getQuitButton() {
        String name = "Quit";
        JPanel panel = new JPanel();
        JButton button = new JButton(name);
        button.setFont(new Font("sans-serif", Font.PLAIN, 36));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 40));
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(button);
        panel.setOpaque(false);

        // When clicking the button, program exits
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        return panel;
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
