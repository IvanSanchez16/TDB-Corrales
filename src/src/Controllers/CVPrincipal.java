package Controllers;

import Models.MPrincipal;
import Views.UIPrincipal;
import java.awt.event.*;

public class CVPrincipal implements ActionListener {
    UIPrincipal view;
    MPrincipal model;

    public CVPrincipal(UIPrincipal v,MPrincipal m){
        view=v;
        model=m;
        view.asignarControladorP(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {

    }
}
