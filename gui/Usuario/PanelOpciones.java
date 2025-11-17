package gui.Usuario;

import Service.ServiceUsuario;
import gui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelOpciones extends JPanel {
    private ServiceUsuario serviceUsuario;
    private PanelManager panel;
    private JPanel panelOpciones;
    private JLabel jlabelTexto;
    private JButton jButtonVerCuentas;
    private JButton jButtonMovimientos;
    private JButton jButtonTransferencias;
    private JButton jButtonVerTarjetas;
    private JButton jButtonCerrarSesion;

    public PanelOpciones(PanelManager panel) {
        this.panel = panel;
        armarPanelUsuario();
    }

    public void armarPanelUsuario() {
        serviceUsuario = new ServiceUsuario();
        panelOpciones = new JPanel();
        jlabelTexto = new JLabel("Seleccione una opción");
        jButtonVerCuentas = new JButton("Ver cuentas");
        jButtonMovimientos = new JButton("Ver movimientos");
        jButtonTransferencias = new JButton("Realizar transferencias");
        jButtonVerTarjetas = new JButton("Ver tarjetas");
        jButtonCerrarSesion = new JButton("Cerrar sesión");
        panelOpciones.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 0;
        panelOpciones.add(jlabelTexto, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 1;
        panelOpciones.add(jButtonVerCuentas, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 2;
        panelOpciones.add(jButtonMovimientos, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 3;
        panelOpciones.add(jButtonTransferencias, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 4;
        panelOpciones.add(jButtonVerTarjetas, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 5;
        panelOpciones.add(jButtonCerrarSesion, c);

        jButtonCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(1);
            }
        });

        jButtonVerCuentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(12);}
        });

        jButtonTransferencias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(15);}
        });

        jButtonVerTarjetas.addActionListener(new ActionListener() {;
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(17);}
        });

        jButtonMovimientos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(20);}
        });

        setLayout(new BorderLayout());
        add(panelOpciones, BorderLayout.CENTER);






    }
}
