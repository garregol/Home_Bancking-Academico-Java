package gui.Cuenta;

import Entidades.*;
import Service.ServiceCuenta;
import Service.ServiceUsuario;
import gui.PanelManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import Entidades.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PanelCrearCuenta extends JPanel {
    private ServiceCuenta serviceCuenta;
    private ServiceUsuario serviceUsuario;
    private PanelManager panel;
    private JLabel jLabelTitulo;
    private JLabel JLabelNombreUsuario;
    private Map<String, Integer> usuarioNombreIdMap = new HashMap<>();
    private JComboBox<String> jComboBoxNombreUsuario;
    private JLabel jLabelTipoCuenta;
    private JComboBox<String> tipoCuentaComboBox;
    private JLabel jLabelSaldoInicial;
    private JTextField jTextFieldSaldoInicial;
    private JLabel jlabelCBU;
    private JTextField jTextFieldCBU;
    private JLabel jlabelAlias;
    private JTextField jTextFieldAlias;
    private JButton jButtonCrearCuenta;
    private JButton jButtonVolver;
    private JPanel panelCrearCuenta;

    public PanelCrearCuenta(PanelManager panel) {
        this.panel = panel;
        armarPanelCrearCuenta();
    }

    public void armarPanelCrearCuenta() {
        serviceCuenta = new ServiceCuenta();
        serviceUsuario = new ServiceUsuario();
        panelCrearCuenta = new JPanel();
        jLabelTitulo = new JLabel("Crear Cuenta");
        JLabelNombreUsuario = new JLabel("Nombre de Usuario");
        jComboBoxNombreUsuario = new JComboBox<>(); // Inicializa antes de usar

        List<Usuario> usuarios = serviceUsuario.consultarTodos(); // Debe retornar java.util.List<Usuario>
        for (Usuario usuario : usuarios) {
            jComboBoxNombreUsuario.addItem(usuario.getUsername());
            usuarioNombreIdMap.put(usuario.getUsername(), usuario.getId());
        }
        jLabelTipoCuenta = new JLabel("Tipo de Cuenta");
        tipoCuentaComboBox = new JComboBox<>(new String[]{"Ahorro", "Corriente","Ahorro DLS"});
        jLabelSaldoInicial = new JLabel("Saldo Inicial");
        jTextFieldSaldoInicial = new JTextField(20);
        jlabelCBU = new JLabel("CBU");
        jTextFieldCBU = new JTextField(20);
        jlabelAlias = new JLabel("Alias");
        jTextFieldAlias = new JTextField(20);
        jButtonCrearCuenta = new JButton("Crear Cuenta");
        jButtonVolver = new JButton("Volver");

        panelCrearCuenta.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 0;
        panelCrearCuenta.add(jLabelTitulo, c);

        c.fill= GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 1;
        panelCrearCuenta.add(JLabelNombreUsuario, c);

        c.fill= GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 1;
        panelCrearCuenta.add(jComboBoxNombreUsuario, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        panelCrearCuenta.add(jLabelTipoCuenta, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        panelCrearCuenta.add(tipoCuentaComboBox, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        panelCrearCuenta.add(jLabelSaldoInicial, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        panelCrearCuenta.add(jTextFieldSaldoInicial, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        panelCrearCuenta.add(jlabelCBU, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        panelCrearCuenta.add(jTextFieldCBU, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        panelCrearCuenta.add(jlabelAlias, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 5;
        panelCrearCuenta.add(jTextFieldAlias, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 6;
        panelCrearCuenta.add(jButtonCrearCuenta, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 7;
        panelCrearCuenta.add(jButtonVolver, c);

        // Add action listeners for buttons
        jButtonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(4);
            }
        });

        jButtonCrearCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombreSeleccionado = (String) jComboBoxNombreUsuario.getSelectedItem();
                    String tipoCuenta = (String) tipoCuentaComboBox.getSelectedItem();
                    if(tipoCuenta==null || nombreSeleccionado==null){
                        JOptionPane.showMessageDialog(null,"Debe seleccionar un usuario y un tipo de cuenta", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    switch (tipoCuenta) {
                        case "Ahorro":
                            CA cuentaCA=new CA();
                            cuentaCA.setIdUsuario(usuarioNombreIdMap.get(nombreSeleccionado));
                            cuentaCA.setTipo("caja de ahorro");
                            cuentaCA.setSaldo(Double.parseDouble(jTextFieldSaldoInicial.getText()));
                            cuentaCA.setCBU(Integer.parseInt(jTextFieldCBU.getText()));
                            cuentaCA.setAlias(jTextFieldAlias.getText());
                            serviceCuenta.insertar(cuentaCA);
                            break;
                        case "Corriente":
                            Cta_Cte cuentaCta_Cte=new Cta_Cte();
                            cuentaCta_Cte.setIdUsuario(usuarioNombreIdMap.get(nombreSeleccionado));
                            cuentaCta_Cte.setTipo("cuenta corriente");
                            cuentaCta_Cte.setSaldo(Double.parseDouble(jTextFieldSaldoInicial.getText()));
                            cuentaCta_Cte.setCBU(Integer.parseInt(jTextFieldCBU.getText()));
                            cuentaCta_Cte.setAlias(jTextFieldAlias.getText());
                            serviceCuenta.insertar(cuentaCta_Cte);
                            break;
                        case "Ahorro DLS":
                            CA_dls cuentaCA_Dls=new CA_dls();
                            cuentaCA_Dls.setIdUsuario(usuarioNombreIdMap.get(nombreSeleccionado));
                            cuentaCA_Dls.setTipo("caja de ahorro DLS");
                            cuentaCA_Dls.setSaldo(Double.parseDouble(jTextFieldSaldoInicial.getText()));
                            cuentaCA_Dls.setCBU(Integer.parseInt(jTextFieldCBU.getText()));
                            cuentaCA_Dls.setAlias(jTextFieldAlias.getText());
                            serviceCuenta.insertar(cuentaCA_Dls);
                            break;
                        default:
                            throw new IllegalArgumentException("Tipo de cuenta no válido");
                    }

                    JOptionPane.showMessageDialog(panelCrearCuenta, "Cuenta creada exitosamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panelCrearCuenta, "Error al crear la cuenta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setLayout(new BorderLayout());
        add(panelCrearCuenta, BorderLayout.CENTER);
    }
}
