package gui.Cuenta;

import Entidades.Cuenta;
import Entidades.Usuario;
import Service.ServiceCuenta;
import Service.ServiceUsuario;
import gui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PanelEliminarCuenta extends JPanel {
    private ServiceCuenta serviceCuenta;
    private ServiceUsuario serviceUsuario;
    private PanelManager panel;
    private JPanel panelEliminarCuenta;
    private JLabel jlabelTitulo;
    private JLabel jLabelCuenta;
    private Map<String, Integer> usuarioNombreIdMap = new HashMap<>();
    private JComboBox<String> jComboBoxCuenta;
    private JButton jButtonEliminarCuenta;
    private JButton jButtonVolver;

    public PanelEliminarCuenta(PanelManager panel) {
        this.panel = panel;
        armarPanelEliminar();

    }

    public void armarPanelEliminar() {
        serviceCuenta = new ServiceCuenta();
        serviceUsuario = new ServiceUsuario();
        panelEliminarCuenta = new JPanel();
        jlabelTitulo = new JLabel("Eliminar Cuenta");
        jLabelCuenta = new JLabel("Seleccione la cuenta a eliminar:");
        jComboBoxCuenta = new JComboBox<>();
        List<Cuenta> cuentas = serviceCuenta.consultarTodos();
        for (Cuenta cuenta : cuentas) {
            jComboBoxCuenta.addItem(cuenta.getAlias());
            usuarioNombreIdMap.put(cuenta.getAlias(), cuenta.getId());
        }
        jButtonEliminarCuenta = new JButton("Eliminar Cuenta");
        jButtonVolver = new JButton("Volver");

        panelEliminarCuenta.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 0;
        panelEliminarCuenta.add(jlabelTitulo, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 1;
        panelEliminarCuenta.add(jLabelCuenta, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 1;
        panelEliminarCuenta.add(jComboBoxCuenta, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 3;
        panelEliminarCuenta.add(jButtonEliminarCuenta, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 3;
        panelEliminarCuenta.add(jButtonVolver, c);

        jButtonEliminarCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Cuenta cuentaSeleccionada = serviceCuenta.consultarAlias((String) jComboBoxCuenta.getSelectedItem());
                    int confirmation = JOptionPane.showConfirmDialog(null, "¿SEGURO DE ELIMINAR LA CUENTA DE" + "\n" + " >ALIAS: " + cuentaSeleccionada.getAlias() + "\n" + " >ID: " + cuentaSeleccionada.getId(),
                            "CONFIRMACION", JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.INFORMATION_MESSAGE);
                    if (confirmation == JOptionPane.OK_OPTION) {
                        serviceCuenta.eliminar(cuentaSeleccionada.getId());
                        JOptionPane.showMessageDialog(panelEliminarCuenta, "Cuenta eliminada exitosamente.");
                        jComboBoxCuenta.removeItem(cuentaSeleccionada.getAlias());
                    }
                } catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
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
        add(panelEliminarCuenta, BorderLayout.CENTER);

    }

}
