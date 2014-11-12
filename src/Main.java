import javax.swing.*;
import java.awt.*;
public class Main {

    public static void main(String[] args) {
         FraGui4 myFrame = new FraGui4();
        myFrame.setPreferredSize(new Dimension(1000, 400));
        myFrame.pack();
        myFrame.setLocationRelativeTo(null);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);
        myFrame.setTitle("Black Jack");
    }
    }