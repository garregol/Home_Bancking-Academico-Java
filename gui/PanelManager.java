package gui;

import Entidades.Usuario;
import gui.Cuenta.*;
import gui.Movimientos.PanelAuditoria;
import gui.Movimientos.PanelReporteMovimientoUsuario;
import gui.Movimientos.PanelTransferir;
import gui.Tarjeta.*;
import gui.Usuario.*;

import javax.swing.*;

public class PanelManager {
    private FormularioUsuario formularioUsuario;
    private ReporteUsuario reporteUsuario;
    private PanelOpcionesAdmin panelOpcionesAdmin;
    private Login login;
    private Usuario usuarioAcutual;
    private PanelOpciones panelOpciones;
    private PanelEliminar panelEliminar;
    private PanelModificar panelModificar;
    private PanelCrearCuenta panelCrearCuenta;
    private PanelEliminarCuenta panelEliminarCuenta;
    private PanelModificarCuenta panelModificarCuenta;
    private PanelReporteCuenta panelReporteCuenta;
    private PanelReporteCuentaUsuario panelReporteCuentaUsuario;
    private PanelCrearTarjeta panelCrearTarjeta;
    private PanelTransferir panelTransferir;
    private PanelEliminarTarjeta panelEliminarTarjeta;
    private PanelReporteTarjeta panelReporteTarjeta;
    private PanelReporteTarjetaUsuario panelReporteTarjetaUsuario;
    private PanelModificarTarjeta panelModificarTarjeta;
    private PanelAuditoria panelAuditoria;
    private PanelReporteMovimientoUsuario panelReporteMovimientoUsuario;

    JFrame jFrame;

    public PanelManager(int tipo) {
        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (tipo == 1){
            login = new Login(this);
            mostrar(login);
        }
    }

    public void setUsuarioAcutual(Usuario usuarioAcutual) {
        this.usuarioAcutual = usuarioAcutual;
    }
    public Usuario getUsuarioAcutual() {
        return usuarioAcutual;
    }

    public void mostrar(JPanel panel) {
        jFrame.getContentPane().removeAll();
        jFrame.getContentPane().add(panel);
        jFrame.getContentPane().validate();
        jFrame.getContentPane().repaint();
        jFrame.show();
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
    }

    public void mostrar(int codigoPantalla) {
        switch (codigoPantalla) {
            case 1:
                login = new Login(this);
                mostrar(login);
                break;
            case 2:
                reporteUsuario = new ReporteUsuario(this);
                mostrar(reporteUsuario);
                break;
            case 3:
                formularioUsuario = new FormularioUsuario(this);
                mostrar(formularioUsuario);
                break;
            case 4:
                panelOpcionesAdmin = new PanelOpcionesAdmin(this);
                mostrar(panelOpcionesAdmin);
                break;
            case 5:
                panelOpciones = new PanelOpciones(this);
                mostrar(panelOpciones);
                break;
            case 6:
                panelEliminar = new PanelEliminar(this);
                mostrar(panelEliminar);
                break;
            case 7:
                panelModificar = new PanelModificar(this);
                mostrar(panelModificar);
                break;

            case 8:
                panelCrearCuenta = new PanelCrearCuenta(this);
                mostrar(panelCrearCuenta);
                break;

            case 9:
                panelEliminarCuenta = new PanelEliminarCuenta(this);
                mostrar(panelEliminarCuenta);
                break;

            case 10:
                panelModificarCuenta = new PanelModificarCuenta(this);
                mostrar(panelModificarCuenta);
                break;

            case 11:
                panelReporteCuenta = new PanelReporteCuenta(this);
                mostrar(panelReporteCuenta);
                break;

            case 12:
                panelReporteCuentaUsuario = new PanelReporteCuentaUsuario(this);
                mostrar(panelReporteCuentaUsuario);
                break;

            case 13:
                panelCrearTarjeta = new PanelCrearTarjeta(this);
                mostrar(panelCrearTarjeta);
                break;

            case 14:
                panelEliminarTarjeta = new PanelEliminarTarjeta(this);
                mostrar(panelEliminarTarjeta);
                break;

            case 15:
                panelTransferir = new PanelTransferir(this);
                mostrar(panelTransferir);
                break;

            case 16:
                panelReporteTarjeta = new PanelReporteTarjeta(this);
                mostrar(panelReporteTarjeta);
                break;

            case 17:
                panelReporteTarjetaUsuario = new PanelReporteTarjetaUsuario(this);
                mostrar(panelReporteTarjetaUsuario);
                break;

            case 18:
                panelModificarTarjeta = new PanelModificarTarjeta(this);
                mostrar(panelModificarTarjeta);
                break;

            case 19:
                panelAuditoria = new PanelAuditoria(this);
                mostrar(panelAuditoria);
                break;

            case 20:
                panelReporteMovimientoUsuario = new PanelReporteMovimientoUsuario(this);
                mostrar(panelReporteMovimientoUsuario);
                break;



        }
    }
}
