import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleGUI {
        public static void main(String[] args) {
        JFrame frame = new JFrame("Simple GUI");
        JButton button = new JButton("Click Me");
        JLabel label = new JLabel("Hello, World!");

        button.addActionListener(e -> label.setText("Button Clicked!"));

        frame.setLayout(new FlowLayout());
        frame.add(button);
        frame.add(label);

        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
