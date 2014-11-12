
import java.awt.Dimension;
import javax.swing.JFrame;

public class FraMain extends JFrame {

    FraMain() {
        FraGui4 panMain = new FraGui4();
        setSize(1000, 400);
        setTitle("Black Jack");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //add(panMain);
        setVisible(true);

    }
}
