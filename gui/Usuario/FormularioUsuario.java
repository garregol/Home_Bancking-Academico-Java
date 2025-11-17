package gui.Usuario;

import Entidades.Usuario;
import Service.ServiceException;
import Service.ServiceUsuario;
import gui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioUsuario extends JPanel {

    ServiceUsuario serviceUsuario;
    PanelManager panel;
    JPanel formularioUsuario;
    JLabel jLabelNombre;
    JLabel jLabelApellido;
    JLabel jLabelUsername;
    JLabel jLabelPassword;
    JCheckBox jCheckBoxAdmin;
    //JLabel jLabelID;
    JTextField jTextFieldNombre;
    JTextField jTextFieldApellido;
    //JTextField jTextFieldID;
    JTextField jTextFieldUsername;
    JTextField jTextFieldPassword;
    JButton jButtonGrabar;
    JButton jButtonVolver;


    public FormularioUsuario(PanelManager panel) {
        this.panel = panel;
        armarFormulario();
    }

    public void armarFormulario(){
        serviceUsuario= new ServiceUsuario();
        formularioUsuario=new JPanel();
        jLabelNombre=new JLabel("Nombre");
        jLabelApellido=new JLabel("Apellido");
        //jLabelID=new JLabel("ID");
        jLabelUsername=new JLabel("Username");
        jLabelPassword=new JLabel("Contraseña");
        jCheckBoxAdmin=new JCheckBox("Es Administrador");
        jTextFieldNombre=new JTextField(20);
        jTextFieldApellido=new JTextField(30);
        //jTextFieldID=new JTextField(10);
        jTextFieldUsername=new JTextField(20);
        jTextFieldPassword=new JTextField(20);

        jButtonGrabar=new JButton("Guardar");
        jButtonVolver=new JButton("Volver");


        formularioUsuario.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill= GridBagConstraints.HORIZONTAL;
        c.weightx=3;
        c.gridx=0;
        c.gridy=0;
        formularioUsuario.add(jButtonVolver,c);

       /* c.fill= GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        formularioUsuario.add(jLabelID,c);

        c.fill= GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=1;
        c.anchor=GridBagConstraints.WEST;
        formularioUsuario.add(jTextFieldID,c); */

        c.fill= GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        formularioUsuario.add(jLabelNombre,c);

        c.fill= GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=1;
        formularioUsuario.add(jTextFieldNombre,c);

        c.fill= GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=2;
        formularioUsuario.add(jLabelApellido,c);

        c.fill= GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=2;
        formularioUsuario.add(jTextFieldApellido,c);

        c.fill= GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;
        formularioUsuario.add(jLabelUsername,c);

        c.fill= GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=3;
        formularioUsuario.add(jTextFieldUsername,c);

        c.fill= GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=4;
        formularioUsuario.add(jLabelPassword,c);

        c.fill= GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=4;
        formularioUsuario.add(jTextFieldPassword,c);

        c.fill= GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=5;
        formularioUsuario.add(jCheckBoxAdmin,c);


        c.fill= GridBagConstraints.HORIZONTAL;
        c.weightx=2;
        c.gridx=0;
        c.gridy=6;
        formularioUsuario.add(jButtonGrabar,c);



        jButtonGrabar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario usuario=new Usuario();
                //usuario.setId(Integer.parseInt(jTextFieldID.getText()));
                usuario.setNombre(jTextFieldNombre.getText());
                usuario.setApellido(jTextFieldApellido.getText());
                usuario.setUsername(jTextFieldUsername.getText());
                usuario.setPassword(jTextFieldPassword.getText());
                usuario.setEsAdmin(jCheckBoxAdmin.isSelected());
                try {
                    serviceUsuario.insertar(usuario);
                    JOptionPane.showMessageDialog(null,"Usuario guardado correctamente");
                }
                catch (ServiceException s)
                {
                    JOptionPane.showMessageDialog(null,"No se pudo guardar "+ s.getMessage());
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
        add(formularioUsuario,BorderLayout.CENTER);
    }
}
