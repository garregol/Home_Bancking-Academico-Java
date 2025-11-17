package gui.Cuenta;

import Entidades.CA;
import Entidades.CA_dls;
import Entidades.Cta_Cte;
import Entidades.Cuenta;
import Service.ServiceCuenta;
import gui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PanelModificarCuenta extends JPanel {
    private ServiceCuenta serviceCuenta;
    private PanelManager panel;
    private JPanel panelModificarCuenta;
    private JLabel jLabelTitulo;
    private JLabel jLabelIDCuenta;
    private JComboBox <Integer> jComboBoxIDCuenta;
    private JLabel jLabelIDUsuario;
    private JLabel jLabelTipoCuenta;
    private JLabel jLabelAlias;
    private JLabel jLabelCBU;
    private JLabel jLabelSaldo;
    private JTextField jTextFieldAlias;
    private JTextField jTextFieldCBU;
    private JLabel jLabelSaldo2;
    private JLabel jLabelIDUsuario2;
    private JLabel jLabelTipoCuenta2;

    public PanelModificarCuenta(PanelManager panel) {
        this.panel = panel;
        armarPanelModificar();
    }

    public void armarPanelModificar(){
        serviceCuenta = new ServiceCuenta();
        panelModificarCuenta = new JPanel();
        jLabelTitulo = new JLabel("Modificar Cuenta");
        jLabelIDCuenta = new JLabel("ID Cuenta");
        jLabelIDUsuario = new JLabel("ID Usuario");
        jLabelTipoCuenta = new JLabel("Tipo de Cuenta");
        jLabelAlias = new JLabel("Alias");
        jLabelCBU = new JLabel("CBU");
        jLabelSaldo = new JLabel("Saldo");

        jComboBoxIDCuenta = new JComboBox<>();
        List<Cuenta> cuentas = serviceCuenta.consultarTodos();
        for (Cuenta cuenta : cuentas) {
            jComboBoxIDCuenta.addItem(cuenta.getId());

        }

        jLabelSaldo2 = new JLabel();
        jLabelIDUsuario2 = new JLabel();
        jLabelTipoCuenta2 = new JLabel();
        jTextFieldAlias = new JTextField(20);
        jTextFieldCBU = new JTextField(20);



        JButton jButtonModificarCuenta = new JButton("Modificar Cuenta");
        JButton jButtonVolver = new JButton("Volver");

        panelModificarCuenta.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 0;
        panelModificarCuenta.add(jLabelTitulo, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 1;
        panelModificarCuenta.add(jLabelIDCuenta, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 1;
        panelModificarCuenta.add(jComboBoxIDCuenta, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 2;
        panelModificarCuenta.add(jLabelIDUsuario, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 2;
        panelModificarCuenta.add(jLabelIDUsuario2, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 3;
        panelModificarCuenta.add(jLabelTipoCuenta, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 3;
        panelModificarCuenta.add(jLabelTipoCuenta2, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 4;
        panelModificarCuenta.add(jLabelAlias, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 4;
        panelModificarCuenta.add(jTextFieldAlias, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 5;
        panelModificarCuenta.add(jLabelCBU, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 5;
        panelModificarCuenta.add(jTextFieldCBU, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 6;
        panelModificarCuenta.add(jLabelSaldo, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 6;
        panelModificarCuenta.add(jLabelSaldo2, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 7;
        panelModificarCuenta.add(jButtonVolver, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 7;
        panelModificarCuenta.add(jButtonModificarCuenta, c);

        jButtonModificarCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cuenta cuenta;
                String tipo = jLabelTipoCuenta2.getText();
                try{
                    if ("caja de ahorro".equals(tipo)) {
                        cuenta = new CA();
                    } else if ("cuenta corriente".equals(tipo)) {
                        cuenta = new Cta_Cte();
                    } else if ("caja de ahorro DLS".equals(tipo)||"caja de ahorro en dolares".equals(tipo) ) {
                        cuenta = new CA_dls();
                    } else {
                        JOptionPane.showMessageDialog(PanelModificarCuenta.this, "Tipo de cuenta desconocido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(PanelModificarCuenta.this, "Error al crear la cuenta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Integer idcuentaSeleccionada = (Integer) jComboBoxIDCuenta.getSelectedItem();
                if (idcuentaSeleccionada == null) {
                    JOptionPane.showMessageDialog(PanelModificarCuenta.this, "Debe seleccionar una cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                cuenta.setId(idcuentaSeleccionada);
                cuenta.setAlias(jTextFieldAlias.getText());
                cuenta.setCBU(Integer.parseInt(jTextFieldCBU.getText()));
                serviceCuenta.modificar(cuenta);
                JOptionPane.showMessageDialog(PanelModificarCuenta.this, "Cuenta modificada exitosamente.");
            }
        });

        jComboBoxIDCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer idSeleccionado = (Integer) jComboBoxIDCuenta.getSelectedItem();
                if (idSeleccionado != null) {
                    Cuenta cuentaSeleccionada = serviceCuenta.consultarID(idSeleccionado);
                    jLabelSaldo2.setText(String.valueOf(cuentaSeleccionada.getSaldo()));
                    jLabelTipoCuenta2.setText(cuentaSeleccionada.getTipo());
                    jLabelIDUsuario2.setText(String.valueOf(cuentaSeleccionada.getIdUsuario()));
                    jTextFieldAlias.setText(cuentaSeleccionada.getAlias());
                    jTextFieldCBU.setText(String.valueOf(cuentaSeleccionada.getCBU()));
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
        add(panelModificarCuenta, BorderLayout.CENTER);



    }

}
