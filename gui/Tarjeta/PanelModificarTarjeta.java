package gui.Tarjeta;

import Entidades.Cuenta;
import Entidades.Tarjeta;
import Service.ServiceTarjeta;
import Service.ServiceUsuario;
import gui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PanelModificarTarjeta extends JPanel {
    private ServiceTarjeta serviceTarjeta;
    private PanelManager panel;
    private ServiceUsuario serviceUsuario;
    private JPanel panelModificarTarjeta;
    private JLabel jLabelTitulo;
    private JLabel jLabelNumeroTarjeta;
    private JLabel jLabelTipoTarjeta;
    private JLabel jLabelSaldo;
    private JLabel jLabelUsuario;
    private JComboBox <Integer> jComboBoxNumeroTarjeta;
    private JLabel jLabelTipoTarjeta2;
    private JLabel jLabelUsuario2;
    private JTextField jTextFieldSaldo;
    private JButton jButtonModificarTarjeta;
    private JButton jButtonVolver;


    public PanelModificarTarjeta(PanelManager panel) {
        this.panel = panel;
        armarPanelModificarTarjeta();
    }

    public void armarPanelModificarTarjeta() {
        this.serviceTarjeta = new ServiceTarjeta();
        this.serviceUsuario = new ServiceUsuario();
        panelModificarTarjeta = new JPanel();

        jLabelTitulo = new JLabel("Modificar Tarjeta");
        jLabelNumeroTarjeta = new JLabel("Número de Tarjeta:");
        jComboBoxNumeroTarjeta = new JComboBox<>();
        List<Tarjeta> tarjetas = serviceTarjeta.consultarTodas();
        for (Tarjeta tarjeta : tarjetas) {
            jComboBoxNumeroTarjeta.addItem(tarjeta.getNumeroTarjeta());
        }
        jLabelTipoTarjeta = new JLabel("Tipo de Tarjeta:");
        jLabelTipoTarjeta2 = new JLabel();
        jLabelSaldo = new JLabel("Saldo:");
        jTextFieldSaldo = new JTextField(20);
        jLabelUsuario = new JLabel("Usuario:");
        jLabelUsuario2 = new JLabel();

        jButtonModificarTarjeta = new JButton("Modificar Tarjeta");
        jButtonVolver = new JButton("Volver");

        panelModificarTarjeta.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 0;
        panelModificarTarjeta.add(jLabelTitulo, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 1;
        panelModificarTarjeta.add(jLabelNumeroTarjeta, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 1;
        panelModificarTarjeta.add(jComboBoxNumeroTarjeta, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 2;
        panelModificarTarjeta.add(jLabelTipoTarjeta, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 2;
        panelModificarTarjeta.add(jLabelTipoTarjeta2, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 3;
        panelModificarTarjeta.add(jLabelSaldo, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 3;
        panelModificarTarjeta.add(jTextFieldSaldo, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 4;
        panelModificarTarjeta.add(jLabelUsuario, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 4;
        panelModificarTarjeta.add(jLabelUsuario2, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 5;
        panelModificarTarjeta.add(jButtonVolver, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 5;
        panelModificarTarjeta.add(jButtonModificarTarjeta, c);

        this.setLayout(new BorderLayout());
        this.add(panelModificarTarjeta, BorderLayout.CENTER);

        jButtonModificarTarjeta.addActionListener(e -> {
            Integer numeroTarjeta = (Integer) jComboBoxNumeroTarjeta.getSelectedItem();
            try{
                if (numeroTarjeta != null) {
                    Tarjeta tarjeta = serviceTarjeta.consultarPorNumero(numeroTarjeta);
                        tarjeta.setSaldo(Double.parseDouble(jTextFieldSaldo.getText()));
                        serviceTarjeta.modificar(tarjeta);
                        JOptionPane.showMessageDialog(this, "Tarjeta modificada correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Tarjeta no encontrada.");
                    }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Saldo inválido. Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al modificar la tarjeta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        jButtonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(4);
            }
        });

        jComboBoxNumeroTarjeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer idSeleccionado = (Integer) jComboBoxNumeroTarjeta.getSelectedItem();
                if (idSeleccionado != null) {
                    Tarjeta tarjetaSeleccionada = serviceTarjeta.consultarPorNumero(idSeleccionado);
                    if (tarjetaSeleccionada != null) {
                        jLabelTipoTarjeta2.setText(tarjetaSeleccionada.getTipoTarjeta());
                        jTextFieldSaldo.setText(String.valueOf(tarjetaSeleccionada.getSaldo()));
                        jLabelUsuario2.setText(String.valueOf(tarjetaSeleccionada.getIdUsuario()));
                    }
                }
            }
        });


    }
}