package Controllers;

import Models.MCuarentena;
import Views.UICuarentena;
import Views.UIMenu;

import java.awt.event.*;

public class CCuarentena implements ActionListener, MouseListener, KeyListener {
    UICuarentena view;
    MCuarentena model;

    public CCuarentena(){
        view=new UICuarentena();
        model=new MCuarentena();
        view.asignarControlador(this);
        view.llenarCriasEn( model.obtenerCriasEn() );
        view.llenarCorralesEn( model.obtenerCorralesEn() );
        view.llenarCriasG2( model.obtenerCriasG2() );
        view.llenarCriasSa( model.obtenerCriasCuarentena() );
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getBtnMoverCriaA()){
            if(view.getCria1().equals("") || view.getCorral1().equals("")){
                view.mostrarModal("Faltan campos por llenar");
                return;
            }
            view.mostrarModal( model.moverACuarentena(view.getCria1(),view.getCorral1(), UIMenu.getFechaActual()) );
            view.llenarCriasEn( model.obtenerCriasEn() );
            view.llenarCorralesEn( model.obtenerCorralesEn() );
            view.llenarCriasG2( model.obtenerCriasG2() );
            view.llenarCriasSa( model.obtenerCriasCuarentena() );
            return;
        }
        if(e.getSource()==view.getBtnMarcarEnRiesgo()){
            view.marcarCriasRiesgo( model.marcarCriasEnRiesgo( view.getDatosEG2() ) );
            return;
        }
        if (e.getSource()==view.getBtnMarcarSanas()){
            view.marcarCriasAliviadas( model.marcarCriasAliviadas( view.getDatosSA() ) );
            return;
        }
        if(e.getSource()==view.getBtnMarcarC40()){
            view.marcarCriasAliviadas( model.marcarCrias40D( view.getDatosSA() ) );
            return;
        }

        if(e.getSource()==view.getT()){
            view.modalCarga();
            model.recuperarTemperaturas();
            view.llenarCriasG2( model.obtenerCriasG2() );
            view.llenarCriasEn( model.obtenerCriasEn() );
            view.cerrarDialog();
            return;
        }
        if(e.getSource()==view.getTe()){
            view.modalCarga();
            model.recuperarTemperaturasEn();
            view.llenarCriasSa( model.obtenerCriasCuarentena() );
            view.cerrarDialog();
            return;
        }

        String cria=view.getCria2();
        if(cria.equals("")){
            view.mostrarModal("Seleccione una cría");
            return;
        }
        if (e.getSource()==view.getBtnSacrificar()){
            if( model.comprobarDias( cria,view.getDatosSA() )<40 ){
                view.mostrarModal("La cría necesita estar 40+ días en cuarentena");
                return;
            }
            if (view.mostrarAdvertencia("¿Está seguro que quiere sacrificar la cría?","Sacrificar una cría")==0){
                view.mostrarModalSac( model.sacrificarCria( cria,UIMenu.getFechaActual() ) );
                view.llenarCriasSa( model.obtenerCriasCuarentena() );
                view.llenarCorralesEn( model.obtenerCorralesEn() );
                return;
            }
            return;
        }
        if(e.getSource()==view.getBtnMoverCriaR()){
            if( model.comprobarTemperatura( cria,view.getDatosSA() )>=40 )
                if( view.mostrarAdvertencia("¿Está seguro que quiere dar de alta la cría? Aún no muestra señales de alivio","Dar de alta")==1)
                    return;
            view.mostrarModalAlt( model.darDeAlta(cria,UIMenu.getFechaActual()) );
            view.llenarCriasG2( model.obtenerCriasG2() );
            view.llenarCriasEn( model.obtenerCriasEn() );
            view.llenarCriasSa( model.obtenerCriasCuarentena() );
            view.llenarCorralesEn( model.obtenerCorralesEn() );
            return;
        }
    }

    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==view.getTbCoEn()){
            view.seleccionarCorral(view.getTbCoEn().rowAtPoint(e.getPoint()));
            return;
        }
        if(e.getSource()==view.getTbCrEn()){
            view.seleccionarCria(view.getTbCrEn().rowAtPoint(e.getPoint()));
            return;
        }
        if(e.getSource()==view.getTbCrSa()){
            view.seleccionarCriaSa(view.getTbCrSa().rowAtPoint(e.getPoint()));
            return;
        }

    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public void keyTyped(KeyEvent e) {
        e.consume();
    }
    public void keyPressed(KeyEvent e) {
        e.consume();
    }
    public void keyReleased(KeyEvent e) {}
}
