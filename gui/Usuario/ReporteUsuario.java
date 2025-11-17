package gui.Usuario;

import DAO.Excepciones.DAOException;
import Entidades.Usuario;
import Service.ServiceUsuario;
import gui.PanelManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReporteUsuario extends JPanel {
    private PanelManager panelManager;
    private JTable jTable;
    private DefaultTableModel contenido;
    private JScrollPane scrollPane;
    private JButton jButtonVolver;

    public ReporteUsuario(PanelManager panelManager) {
        this.panelManager = panelManager;
        armarTablaReporte();
    }

    public void armarTablaReporte(){
        setLayout(new BorderLayout());
        contenido= new DefaultTableModel();
        jTable=new JTable(contenido);
        scrollPane=new JScrollPane();
        scrollPane.setViewportView(jTable);
        contenido.addColumn("id");
        contenido.addColumn("Nombre");
        contenido.addColumn("Apellido");
        contenido.addColumn("Username");
        contenido.addColumn("Contraseña");
        contenido.addColumn("Es Administrador");
        JButton jButtonVolver = new JButton("Volver");
        ServiceUsuario service=new ServiceUsuario();
        try{
            ArrayList<Usuario> usuarios = service.consultarTodos();
            for(Usuario usuario:usuarios) {
                Object[] fila = new Object[6];
                fila[0] = usuario.getId();
                fila[1] = usuario.getNombre();
                fila[2] = usuario.getApellido();
                fila[3] = usuario.getUsername();
                fila[4] = usuario.getPassword();
                fila[5] = usuario.isEsAdmin() ? "Si" : "No";
                contenido.addRow(fila);
            }
        }catch (DAOException e){
            JOptionPane.showMessageDialog(null,"error al mostrar");
        }
        add(jButtonVolver, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        jButtonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrar(4);
            }
        });
    }

}
