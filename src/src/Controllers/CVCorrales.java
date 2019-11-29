package Controllers;

import Models.MVCorrales;
import Views.UIVCorrales;

public class CVCorrales {

    private UIVCorrales view;
    private MVCorrales model;

    public CVCorrales(){
        view=new UIVCorrales();
        model=new MVCorrales();
        view.llenarTabla( model.obtenerDatos() );
        view.setVisible(true);
    }
}
