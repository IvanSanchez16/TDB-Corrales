package Controllers;

import Models.MCuarentena;
import Views.UICuarentena;
import Views.UIPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CCuarentena implements ActionListener {
    UICuarentena view;
    MCuarentena model;

    public CCuarentena(){
        view=new UICuarentena();
        model=new MCuarentena();
        view.asignarControlador(this);
//        view.llenarCriasEn( model.obtenerCriasEn() );
//        view.llenarCorralesEn( model.obtenerCorralesEn() );
        view.llenarCriasG2( model.obtenerCriasG2() );
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getBtnMoverCriaA()){
            view.mostrarModal( model.moverACuarentena(view.getCria1(),view.getCorral1(), UIPrincipal.getFechaActual(),view.getMedicamento(),view.getEnfermedad()) );
            return;
        }
        if(e.getSource()==view.getBtnMarcarEnRiesgo()){
            view.marcarCriasRiesgo( model.marcarCriasEnRiesgo( view.getDatosEG2() ) );
            return;
        }
    }
}
