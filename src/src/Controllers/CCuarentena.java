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
        view.llenarCorralesEn( model.obtenerCorralesEn() );
        view.llenarCBDietas( model.obtenerDietas() );
        view.llenarCriasSa( model.obtenerCriasCuarentena() );
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getBtnBuscar()){
            view.llenarCriasG2( model.obtenerDatosCria( view.getTxtCriaEv() ) );
            return;
        }
        if(e.getSource()==view.getBtnMoverCriaA()){
            if(view.getCria1().equals("") || view.getCorral1().equals("")){
                view.mostrarModal("Faltan campos por llenar");
                return;
            }
            view.mostrarModal( model.moverACuarentena(view.getCria1(),view.getDieta(),view.getCorral1(), UIMenu.getFechaActual()) );
            view.llenarCorralesEn( model.obtenerCorralesEn() );
            view.llenarCriasSa( model.obtenerCriasCuarentena() );
            return;
        }
        if(e.getSource()==view.getBtnMarcarC40()){
            view.marcarCriasAliviadas( model.marcarCrias40D( view.getDatosSA() ) );
            return;
        }

        String cria=view.getCria2();
        if(cria.equals("")){
            view.mostrarModal("Seleccione una cría");
            return;
        }
        if (e.getSource()==view.getBtnSacrificar()){
            if( model.comprobarDias( cria,view.getDatosSA() )<40 ){
                view.mostrarModal2("La cría necesita estar 40+ días en cuarentena");
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
            if( view.mostrarAdvertencia("¿Está seguro que quiere dar de alta la cría?","Dar de alta")==1)
                return;
            view.mostrarModalAlt( model.darDeAlta(cria,UIMenu.getFechaActual()) );
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
