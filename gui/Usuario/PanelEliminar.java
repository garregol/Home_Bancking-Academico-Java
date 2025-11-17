package gui.Usuario;

import Entidades.Usuario;
import Service.ServiceUsuario;
import gui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelEliminar extends JPanel {

    private PanelManager panel;
    private ServiceUsuario serviceUsuario;
    private JLabel jLabelTexto;
    private JButton jButtonEliminarCuenta;
    private JButton jButtonVolver;
    private JComboBox<Integer> jBoxID;


    public PanelEliminar(PanelManager panel) {
        this.panel = panel;
        armarPanel();
    }

    public void armarPanel() {
        serviceUsuario = new ServiceUsuario();
        jLabelTexto = new JLabel("Seleccione el ID de la cuenta a eliminar:");
        jBoxID = new JComboBox<>();
        jButtonEliminarCuenta = new JButton("Eliminar Cuenta");
        jButtonVolver = new JButton("Volver");
        ArrayList<Usuario> usuarios = serviceUsuario.consultarTodos();
        for (Usuario usuario : usuarios) {
            jBoxID.addItem(usuario.getId());
        }


        JPanel panelEliminar = new JPanel();

        panelEliminar.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill= GridBagConstraints.HORIZONTAL;
        c.weightx=2;
        c.gridx=0;
        c.gridy=0;
        panelEliminar.add(jButtonVolver, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 1;
        panelEliminar.add(jLabelTexto, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 2;
        panelEliminar.add(jBoxID, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2;
        c.gridx = 1;
        c.gridy = 3;
        panelEliminar.add(jButtonEliminarCuenta, c);

        jButtonEliminarCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Usuario usuario = serviceUsuario.consultarID((Integer) jBoxID.getSelectedItem());
                    int Confirmation = JOptionPane.showConfirmDialog(null, "¿SEGURO DE ELIMINAR LA CUENTA DE"+"\n"+" >NOMBRE: "+usuario.getNombre()+"\n"+" >APELLIDO: "+usuario.getApellido()+"\n"+" >USERNAME: "+usuario.getUsername()+"\n"+" >ID: "+usuario.getId(),
                            "CONFIRMACION", JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.INFORMATION_MESSAGE);
                    if (Confirmation != JOptionPane.OK_OPTION) {
                        return; // Si el usuario cancela, no se realiza la eliminación
                    }
                    else {
                        serviceUsuario.eliminar((Integer) jBoxID.getSelectedItem());
                        JOptionPane.showMessageDialog(panelEliminar, "Cuenta eliminada exitosamente.");
                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(panelEliminar, "Error al eliminar la cuenta: " + ex.getMessage());
                    return;
                }
                // Si la eliminación es exitosa, mostrar mensaje de éxito
                finally {
                    panel.mostrar(4);
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
        add(panelEliminar, BorderLayout.CENTER);

    }


}
