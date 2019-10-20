import javax.swing.*;

import Controllers.CVPrincipal;
import Models.MPrincipal;
import Views.*;

public class App {
    public static void main(String [] a){
        UIPrincipal VPrincipal = new UIPrincipal();
        MPrincipal MPrincipal = new MPrincipal();
        CVPrincipal CPrincipal = new CVPrincipal(VPrincipal,MPrincipal);

        //PPrincipal.
    }
}
