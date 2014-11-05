
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;


public class Main {
     public static void main(String[] args) {
         FraGui4 myFrame = new FraGui4();
        //PanMenu panMenu = new PanMenu();
        myFrame.setPreferredSize(new Dimension(1000, 400));
        myFrame.pack();
        myFrame.getContentPane().setBackground(Color.green);
        myFrame.setLocationRelativeTo(null);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);
        myFrame.setTitle("Black Jack");
}
}