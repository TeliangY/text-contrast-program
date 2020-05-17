package app.components;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionListener;

public class MenuBar {
    // Text message displayed on About window
    private static String aboutText = "<html>Use the sliders to change the window color, <br /> or click on the window to use a color picker. <br /><br /> The text will change to the window's complementary color. <br /><br /> Click on the text to manually change it. <br /> Use the sliders to change the window color, <br /> or click on the window to use a color picker.</html>";

    /**
     * This function returns a menubar component
     * 
     * @return A menu bar component
     */
    public static JMenuBar getMenuBar() {
        JMenu menu = new JMenu("Help");
        JMenuItem menuItem = new JMenuItem("About");
        JLabel aboutContent = new JLabel(aboutText);
        JPanel contentPanel = new JPanel();
        JMenuBar menuBar = new JMenuBar();

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