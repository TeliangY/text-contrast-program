package app;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.MouseInputAdapter;

import app.components.ColorPicker;
import app.components.MenuBar;
import app.components.QuitButton;
import app.components.RGBSliders;
import app.components.Text;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;

public class TextContrastProgram {
    // General program components
    private ImageIcon icon = new ImageIcon("src/resources/icon.png");
    private String title = "Use sliders or click window to choose window color.";
    private JFrame mainFrame = new JFrame();
    private JLabel mainText = new JLabel("Text");
    private JPanel mainPanel = new JPanel();
    private ColorPicker colorPicker = new ColorPicker();

    // Slider parameters
    private RGBSliders rgbSliders = new RGBSliders(mainPanel, mainText);
    private int sliderMax = rgbSliders.getSliderMax();

    // RGB sliders
    private JSlider rSlider = rgbSliders.getRSlider();
    private JSlider gSlider = rgbSliders.getGSlider();
    private JSlider bSlider = rgbSliders.getBSlider();

    private Text textComponent = new Text(mainText, sliderMax, rgbSliders.getSliderRed(), rgbSliders.getSliderGreen(), rgbSliders.getSliderBlue());
    private JPanel quitButton = QuitButton.getQuitButton();
    private JMenuBar menubar = MenuBar.getMenuBar();

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
        rightPanel.add(quitButton);
        rightPanel.setOpaque(false);

        // The background color will change based on red, green and blue value
        mainPanel.setBackground(new Color(rgbSliders.getSliderRed(), rgbSliders.getSliderGreen(), rgbSliders.getSliderBlue()));
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 20));
        mainFrame.setIconImage(icon.getImage());
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle(this.title);
        mainFrame.setJMenuBar(menubar);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }


}