package gui.Usuario;

import Entidades.Usuario;
import Service.ServiceUsuario;
import gui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelModificar extends JPanel {

    private PanelManager panel;
    private ServiceUsuario serviceUsuario;
    private JLabel jLabelNombre;
    private JLabel jLabelApellido;
    private JLabel jLabelUsername;
    private JLabel jLabelPassword;
    private JTextField jTextFieldNombre;
    private JTextField jTextFieldApellido;
    private JTextField jTextFieldUsername;
    private JTextField jTextFieldPassword;
    private JButton jButtonModificar;
    private JButton jButtonVolver;
    private JComboBox<Integer> jBoxID;
    private JCheckBox jCheckBoxAdmin;
    private ArrayList<Usuario> usuarios;

    public PanelModificar(PanelManager panel) {
        this.panel = panel;
        armarPanelModificar();
    }

    public void armarPanelModificar() {
        serviceUsuario = new ServiceUsuario();
        jLabelNombre = new JLabel("Nombre");
        jLabelApellido = new JLabel("Apellido");
        jLabelUsername = new JLabel("Username");
        jLabelPassword = new JLabel("Contraseña");
        jTextFieldNombre = new JTextField(20);
        jTextFieldApellido = new JTextField(30);
        jTextFieldUsername = new JTextField(20);
        jTextFieldPassword = new JTextField(20);
        jCheckBoxAdmin = new JCheckBox("Es Administrador");

        jBoxID = new JComboBox<>();
        usuarios = serviceUsuario.consultarTodos();
        for (Usuario usuario : usuarios) {
            jBoxID.addItem(usuario.getId());
        }

        jButtonModificar = new JButton("Modificar");
        jButtonVolver = new JButton("Volver");

        JPanel panelModificar = new JPanel();

        panelModificar.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 0;
        panelModificar.add(jButtonVolver, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 1;
        panelModificar.add(jBoxID, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 2;
        panelModificar.add(jLabelNombre, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 2;
        panelModificar.add(jTextFieldNombre, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 3;
        panelModificar.add(jLabelApellido, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 3;
        panelModificar.add(jTextFieldApellido, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 4;
        panelModificar.add(jLabelUsername, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 4;
        panelModificar.add(jTextFieldUsername, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 5;
        panelModificar.add(jLabelPassword, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 5;
        panelModificar.add(jTextFieldPassword, c);

        c.fill= GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 6;
        panelModificar.add(jCheckBoxAdmin, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 7;
        panelModificar.add(jButtonModificar, c);

        jButtonModificar.addActionListener(e -> {
            int id = (int) jBoxID.getSelectedItem();
            String nombre = jTextFieldNombre.getText();
            String apellido = jTextFieldApellido.getText();
            String username = jTextFieldUsername.getText();
            String password = jTextFieldPassword.getText();
            Boolean esAdmin = jCheckBoxAdmin.isSelected();
            if (nombre.isEmpty() || apellido.isEmpty() || username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                return;
            }
            try{
                Usuario usuario = new Usuario(id, nombre, apellido, username, password, esAdmin);
                serviceUsuario.modificar(usuario);
                JOptionPane.showMessageDialog(null, "Usuario modificado correctamente");}
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al modificar el usuario: " + ex.getMessage());
            }
        });

        jBoxID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idSeleccionado = (int) jBoxID.getSelectedItem();
                for (Usuario usuario : usuarios) {
                    if (usuario.getId() == idSeleccionado) {
                        jTextFieldNombre.setText(usuario.getNombre());
                        jTextFieldApellido.setText(usuario.getApellido());
                        jTextFieldUsername.setText(usuario.getUsername());
                        jTextFieldPassword.setText(usuario.getPassword());
                        jCheckBoxAdmin.setSelected(usuario.isEsAdmin());
                        break;
                    }

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
        add(panelModificar, BorderLayout.CENTER);

    }

}

