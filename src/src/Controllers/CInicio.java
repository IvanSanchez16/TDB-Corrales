package Controllers;
import Views.UIMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CInicio implements ActionListener {

    private UIMenu view;

    public CInicio(){
        view=new UIMenu();
        view.asignarControladorP(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getBtnRegistrarCria()){
            CCria C=new CCria();
            return;
        }
        if(e.getSource()==view.getBtnRegistrarCorral()){
            CCorral c=new CCorral();
            return;
        }
        if(e.getSource()==view.getBtnClasificar()){
            CClasificar cl=new CClasificar();
            return;
        }
        if(e.getSource()==view.getBtnIdentificar()){
            CCuarentena cc=new CCuarentena();
            return;
        }
        if(e.getSource()==view.getBtnDieta()){
            CDietas d=new CDietas();
            return;
        }
        if(e.getSource()==view.getBtnProcesar()){
            CProceso p=new CProceso();
            return;
        }
        if(e.getSource()==view.getBtnVerCorrales()){
            CVCorrales vc=new CVCorrales();
            return;
        }
        if(e.getSource()==view.getBtnGenerarInforme()){

            return;
        }
    }
}
