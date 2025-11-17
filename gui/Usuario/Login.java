package gui.Usuario;

import Entidades.Usuario;
import Service.ServiceUsuario;
import gui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JPanel {
    private ServiceUsuario serviceUsuario;
    private PanelManager panel;
    private JLabel jLabelUsername;
    private JLabel jLabelPassword;
    private JTextField jTextFieldUsername;
    private JTextField jTextFieldPassword;
    private JButton jButtonLogin;
    private JLabel jlabelTexto;


    public Login(PanelManager panel) {
        this.panel = panel;
        armarLogin();
    }

    public void armarLogin() {
        serviceUsuario = new ServiceUsuario();
        jlabelTexto = new JLabel("Ingrese su Usuario y Contraseña");
        jLabelUsername = new JLabel("Username");
        jLabelPassword = new JLabel("Contraseña");
        jTextFieldUsername = new JTextField(20);
        jTextFieldPassword = new JTextField(20);
        jButtonLogin = new JButton("Iniciar Sesion");
        JPanel loginPanel = new JPanel();

        loginPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 0;
        c.gridy = 0;
        loginPanel.add(jlabelTexto, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        loginPanel.add(jLabelUsername, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        loginPanel.add(jTextFieldUsername, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        loginPanel.add(jLabelPassword, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        loginPanel.add(jTextFieldPassword, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 3;
        loginPanel.add(jButtonLogin, c);


        setLayout(new BorderLayout());
        add(loginPanel, BorderLayout.CENTER);

        jButtonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = jTextFieldUsername.getText();
                    String password = jTextFieldPassword.getText();
                    Usuario usuario = serviceUsuario.consultar(username, password);//hacerlo dentro del try

                    if (usuario != null) {
                        panel.setUsuarioAcutual(usuario);
                        JOptionPane.showMessageDialog(null, "Bienvenido " + usuario.getNombre() + " " + usuario.getApellido());
                        if (usuario.isEsAdmin()) {
                            panel.mostrar(4);
                        } else {
                            panel.mostrar(5);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Credenciales incorrectas");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al iniciar sesión: " + ex.getMessage());
                }
            }
        });
    }
}
