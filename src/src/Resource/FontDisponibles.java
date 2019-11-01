package Resource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class FontDisponibles extends JFrame {
    public FontDisponibles(){
        super("Font disponibles");
        HazInterfaz();
    }
    private void HazInterfaz(){
        JLabel Leyenda = new JLabel();
        Leyenda.setHorizontalAlignment(SwingConstants.CENTER);
        add(Leyenda);
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        String[] Nombrefonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        Arrays.sort(Nombrefonts,String::compareTo);


        for(int i=0 ; i<Nombrefonts.length ; i++)

            System.out.println(Nombrefonts[i]);

        for (String font : Nombrefonts) {

            Leyenda.setFont(new Font(font, Font.PLAIN, 25));
            Leyenda.setText("Font=> " + font + " 0 1 2 3 4 5 6 7 8 9");
            try {
                Thread.sleep(2000);
            } catch (Exception e){}
        }
    }

    public static void main(String [] a ){
        new FontDisponibles();
    }
}