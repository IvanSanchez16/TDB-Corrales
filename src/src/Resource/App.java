package Resource;

import javax.swing.*;
import Views.*;

public class App {
    public static void main(String [] a){
        JFrame mainview = new JFrame("Corrales Ternero");
        mainview.setResizable(false);
        mainview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainview.setContentPane(new MainView().getJPPrincipal());
        mainview.setSize(400,300);
        mainview.setVisible(true);
    }
}
