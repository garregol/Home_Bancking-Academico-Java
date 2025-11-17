package gui.Usuario;

import Service.ServiceUsuario;
import gui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelOpcionesAdmin extends JPanel {
    ServiceUsuario serviceUsuario;
    PanelManager panel;
    JPanel panelOpciones;
    JLabel jlabelTexto;
    JButton jButtonFormulario;
    JButton jButtonReporte;
    JButton jButtonModificar;
    JButton jButtonEliminar;
    JButton jButtonCerrarSesion;
    JButton jButtonCrearCuenta;
    JButton jButtonEliminarCuenta;
    JButton jButtonModificarCuenta;
    JButton jButtonVerCuentas;
    JButton jButtonCrearTarjeta;
    JButton jButtonEliminarTarjeta;
    JButton jButtonReporteTarjeta;
    JButton jbuttonModificarTarjeta;
    JButton jbuttonAuditoria;

    public PanelOpcionesAdmin(PanelManager panelManager) {
        this.panel = panelManager;
        armarPanel();
    }

    public void armarPanel() {
        serviceUsuario=new ServiceUsuario();
        panelOpciones = new JPanel();
        jlabelTexto=new JLabel("seleccione una opcion");
        jButtonFormulario=new JButton("Crear cuenta de usuario");
        jButtonReporte=new JButton("Ver cuentas creadas");
        jButtonModificar=new JButton("Modificar cuenta de usuario");
        jButtonEliminar=new JButton("Eliminar cuenta de usuario");
        jButtonCerrarSesion=new JButton("Cerrar sesion");
        jButtonCrearCuenta = new JButton("Crear Cuenta");
        jButtonEliminarCuenta = new JButton("Eliminar Cuenta");
        jButtonModificarCuenta = new JButton("Modificar Cuenta");
        jButtonVerCuentas = new JButton("Ver Cuentas");
        jButtonCrearTarjeta = new JButton("Crear Tarjeta");
        jButtonEliminarTarjeta = new JButton("Eliminar Tarjeta");
        jButtonReporteTarjeta = new JButton("Ver Reporte de Tarjetas");
        jbuttonModificarTarjeta = new JButton("Modificar Tarjeta");
        jbuttonAuditoria = new JButton("Auditoría");



        panelOpciones.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx=0;
        c.gridy=0;
        panelOpciones.add(jlabelTexto, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx=0;
        c.gridy=1;
        panelOpciones.add(jButtonFormulario, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx=0;
        c.gridy=2;
        panelOpciones.add(jButtonReporte, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx=2;
        c.gridx=0;
        c.gridy=3;
        panelOpciones.add(jButtonModificar, c);

        c.fill= GridBagConstraints.HORIZONTAL;
        c.weightx=2;
        c.gridx=0;
        c.gridy=4;
        panelOpciones.add(jButtonEliminar, c);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx=0;
        c.gridy=5;
        panelOpciones.add(jButtonCrearCuenta, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx=2;
        c.gridx=0;
        c.gridy=6;
        panelOpciones.add(jButtonEliminarCuenta, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx=2;
        c.gridx=0;
        c.gridy=7;
        panelOpciones.add(jButtonModificarCuenta, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx=2;
        c.gridx=0;
        c.gridy=8;
        panelOpciones.add(jButtonVerCuentas, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx=0;
        c.gridy=9;
        panelOpciones.add(jButtonCrearTarjeta, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx=0;
        c.gridy=10;
        panelOpciones.add(jButtonEliminarTarjeta, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx=0;
        c.gridy=11;
        panelOpciones.add(jButtonReporteTarjeta, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx=0;
        c.gridy=12;
        panelOpciones.add(jbuttonModificarTarjeta, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx=0;
        c.gridy=13;
        panelOpciones.add(jbuttonAuditoria, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx=0;
        c.gridy=14;
        panelOpciones.add(jButtonCerrarSesion, c);

        jButtonCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(1);
            }
        });


        setLayout(new BorderLayout());
        add(panelOpciones, BorderLayout.CENTER);


        jButtonFormulario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(3);
            }
        });
        jButtonReporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(2);
            }
        });
        jButtonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(7);
            }
        });
        jButtonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(6);
            }
        });
        jButtonCrearCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { panel.mostrar(8);
            }
        });
        jButtonEliminarCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { panel.mostrar(9);
            }
        });
        jButtonModificarCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { panel.mostrar(10);
            }
        });

        jButtonVerCuentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { panel.mostrar(11);}
        });

        jButtonCrearTarjeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { panel.mostrar(13);}
        });

        jButtonEliminarTarjeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { panel.mostrar(14);}
        });

        jButtonReporteTarjeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { panel.mostrar(16);}
        });

        jbuttonModificarTarjeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { panel.mostrar(18);}
        });

        jbuttonAuditoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { panel.mostrar(19);}
        });


    }

}
