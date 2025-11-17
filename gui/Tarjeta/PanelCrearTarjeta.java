package gui.Tarjeta;

import Entidades.Tarjeta;
import Entidades.Usuario;
import Service.ServiceTarjeta;
import Service.ServiceUsuario;
import gui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PanelCrearTarjeta extends JPanel {
    private ServiceTarjeta serviceCuenta;
    private ServiceUsuario serviceUsuario;
    private PanelManager panel;
    private Map<String, Integer> usuarioNombreIdMap = new HashMap<>();
    private JComboBox<String> jComboBoxNombreUsuario;
    private JLabel jLabelTitulo;
    private JLabel jLabelNumeroTarjeta;
    private JLabel jLabelTipoTarjeta;
    private JLabel jLabelSaldo;
    private JLabel jLabelUsuario;
    private JComboBox<String> jComboBoxTipoTarjeta;
    private JTextField jTextFieldNumeroTarjeta;
    private JTextField jTextFieldSaldo;
    private JButton jButtonCrearTarjeta;
    private JButton jButtonVolver;


    public PanelCrearTarjeta(PanelManager panel) {
        this.panel = panel;
        armarPanelCrearTarjeta();
    }

    public void armarPanelCrearTarjeta(){
        this.serviceCuenta = new ServiceTarjeta();
        this.serviceUsuario = new ServiceUsuario();
        JPanel panelCrearTarjeta = new JPanel();

        jLabelTitulo = new JLabel("Crear Tarjeta");
        jLabelNumeroTarjeta = new JLabel("Número de Tarjeta:");
        jLabelTipoTarjeta = new JLabel("Tipo de Tarjeta:");
        jLabelSaldo = new JLabel("Saldo:");
        jLabelUsuario = new JLabel("Usuario:");

        jComboBoxTipoTarjeta = new JComboBox<>(new String[]{"credito", "debito"});
        jTextFieldNumeroTarjeta = new JTextField(20);
        jTextFieldSaldo = new JTextField(10);

        jComboBoxNombreUsuario = new JComboBox<>();

        List<Usuario> usuarios = serviceUsuario.consultarTodos();
        for (Usuario usuario : usuarios) {
            jComboBoxNombreUsuario.addItem(usuario.getUsername());
            usuarioNombreIdMap.put(usuario.getUsername(), usuario.getId());
        }

        jButtonCrearTarjeta = new JButton("Crear Tarjeta");
        jButtonVolver = new JButton("volver");


        panelCrearTarjeta.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 0;
        panelCrearTarjeta.add(jLabelTitulo, c);

        c.fill= GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 1;
        panelCrearTarjeta.add(jLabelNumeroTarjeta, c);

        c.fill= GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 1;
        panelCrearTarjeta.add(jTextFieldNumeroTarjeta, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        panelCrearTarjeta.add(jLabelUsuario, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        panelCrearTarjeta.add(jComboBoxNombreUsuario, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        panelCrearTarjeta.add(jLabelTipoTarjeta, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        panelCrearTarjeta.add(jComboBoxTipoTarjeta, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        panelCrearTarjeta.add(jLabelSaldo, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        panelCrearTarjeta.add(jTextFieldSaldo, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        panelCrearTarjeta.add(jButtonVolver, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 5;
        panelCrearTarjeta.add(jButtonCrearTarjeta, c);
        c.fill = GridBagConstraints.HORIZONTAL;






        jButtonCrearTarjeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String numeroTarjeta = jTextFieldNumeroTarjeta.getText();
                    String tipoTarjeta = (String) jComboBoxTipoTarjeta.getSelectedItem();
                    String saldo = jTextFieldSaldo.getText();
                    String nombreUsuario = (String) jComboBoxNombreUsuario.getSelectedItem();
                    int idUsuario = usuarioNombreIdMap.get(nombreUsuario);
                    Tarjeta TarjetaNueva = new Tarjeta(Integer.parseInt(numeroTarjeta), tipoTarjeta, Double.parseDouble(saldo),idUsuario);

                    if (numeroTarjeta.isEmpty() || tipoTarjeta == null || saldo.isEmpty() || nombreUsuario == null) {
                        JOptionPane.showMessageDialog(PanelCrearTarjeta.this, "Por favor, complete todos los campos.");
                        return;
                    }

                    serviceCuenta.insertar(TarjetaNueva);
                    JOptionPane.showMessageDialog(PanelCrearTarjeta.this, "Tarjeta creada exitosamente.");
                    panel.mostrar(4);
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(PanelCrearTarjeta.this, "Error al crear la tarjeta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jButtonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(4);
            }
        });

        setLayout(new BorderLayout());
        add(panelCrearTarjeta,BorderLayout.CENTER);
    }

}
