package gui.Movimientos;

import Entidades.Cuenta;
import Entidades.Usuario;
import Service.ServiceCuenta;
import Service.ServiceMovimientos;
import gui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PanelTransferir extends JPanel {
    private ServiceMovimientos serviceMovimientos;
    private ServiceCuenta serviceCuenta;
    private PanelManager panel;
    private JLabel jLabelTitulo;
    private JLabel jLabelIDCuentaOrigen;
    private JComboBox<String> jComboBoxAliasCuentaOrigen;
    private JLabel jLabelIDCuentaDestino;
    private JComboBox<String> jComboBoxAliasCuentaDestino;
    private JLabel jLabelMonto;
    private JTextField jTextFieldMonto;
    private JButton jButtonTransferir;
    private JButton jButtonVolver;
    private JPanel panelTransferir;

    public PanelTransferir(PanelManager panel) {
        this.panel = panel;
        armarPanelTransferir();
    }

    public void armarPanelTransferir() {

        Usuario usuarioActual = panel.getUsuarioAcutual();
        serviceCuenta = new ServiceCuenta();
        serviceMovimientos = new ServiceMovimientos();
        panelTransferir = new JPanel();
        jLabelTitulo = new JLabel("Transferir Dinero entre Cuentas");
        jLabelIDCuentaOrigen = new JLabel("Cuenta Origen:");
        jComboBoxAliasCuentaOrigen = new JComboBox<>();
        ArrayList<Cuenta> cuentas = serviceCuenta.consultarTodosID(usuarioActual.getId());
        for(Cuenta cuenta:cuentas) {
                    jComboBoxAliasCuentaOrigen.addItem(cuenta.getAlias());
        };
        jLabelIDCuentaDestino = new JLabel("Cuenta Destino:");
        jComboBoxAliasCuentaDestino = new JComboBox<>();
        ArrayList<Cuenta> cuentas2 = serviceCuenta.consultarTodos();
        for(Cuenta cuenta:cuentas2) {
            if (cuenta.getIdUsuario() != usuarioActual.getId()) {
                jComboBoxAliasCuentaDestino.addItem(cuenta.getAlias());
            }
        };
        jLabelMonto = new JLabel("Monto a Transferir:");
        jTextFieldMonto = new JTextField(20);
        jButtonTransferir = new JButton("Transferir");
        jButtonVolver = new JButton("Volver");

        panelTransferir.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 0;
        panelTransferir.add(jLabelTitulo, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 1;
        panelTransferir.add(jLabelIDCuentaOrigen, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 1;
        panelTransferir.add(jComboBoxAliasCuentaOrigen, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 2;
        panelTransferir.add(jLabelIDCuentaDestino, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 2;
        panelTransferir.add(jComboBoxAliasCuentaDestino, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 3;
        panelTransferir.add(jLabelMonto, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 3;
        panelTransferir.add(jTextFieldMonto, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 4;
        panelTransferir.add(jButtonTransferir, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 4;
        panelTransferir.add(jButtonVolver, c);

        jButtonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(5);
            }
        });


        jButtonTransferir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String aliasCuentaOrigen = (String) jComboBoxAliasCuentaOrigen.getSelectedItem();
                    String aliasCuentaDestino = (String) jComboBoxAliasCuentaDestino.getSelectedItem();
                    String montoTexto = jTextFieldMonto.getText().trim();

                    if (montoTexto.isEmpty()) {
                        JOptionPane.showMessageDialog(panelTransferir, "El campo monto no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    double monto = Double.parseDouble(jTextFieldMonto.getText());
                    Cuenta cuentaOrigen = serviceCuenta.consultarAlias(aliasCuentaOrigen);
                    Cuenta cuentaDestino = serviceCuenta.consultarAlias(aliasCuentaDestino);

                    String tipoOrigen = cuentaOrigen.getTipo();
                    String tipoDestino = cuentaDestino.getTipo();
                    int idCuentaOrigen = cuentaOrigen.getId();
                    int idCuentaDestino = cuentaDestino.getId();
                    double saldoOrigen = cuentaOrigen.getSaldo();

                    if (serviceMovimientos.Transferencia(idCuentaOrigen, idCuentaDestino, monto, tipoOrigen, tipoDestino, saldoOrigen)) {
                        JOptionPane.showMessageDialog(panelTransferir, "Transferencia exitosa");
                    } else {
                        JOptionPane.showMessageDialog(panelTransferir, "Error en la transferencia");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        this.setLayout(new BorderLayout());
        this.add(panelTransferir, BorderLayout.CENTER);


    }
}




