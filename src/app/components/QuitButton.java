package app.components;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class QuitButton {
    /**
     * 
     * @return A JButton in a contianer, and when clicked, the program exits
     */
    public static JPanel getQuitButton() {
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
}