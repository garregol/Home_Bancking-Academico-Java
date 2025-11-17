package gui.Tarjeta;

import DAO.Excepciones.DAOException;
import Entidades.Tarjeta;
import Entidades.Usuario;
import Service.ServiceCuenta;
import Service.ServiceTarjeta;
import gui.PanelManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelReporteTarjetaUsuario extends JPanel {
    private ServiceTarjeta serviceTarjeta;
    private ServiceCuenta serviceCuenta;
    private PanelManager panel;
    private JTable jTable;
    private JLabel jLabelTitulo;
    private DefaultTableModel contenido;
    private JScrollPane scrollPane;
    private JButton jButtonVolver;

    public PanelReporteTarjetaUsuario(PanelManager panel) {
        this.panel = panel;
        armarTablaReporte();
    }

    public void armarTablaReporte() {
        setLayout(new BorderLayout());
        serviceTarjeta = new ServiceTarjeta();
        serviceCuenta = new ServiceCuenta();
        jButtonVolver = new JButton("Volver");
        jLabelTitulo = new JLabel("Reporte de Tarjetas del Usuario");
        contenido = new DefaultTableModel();
        jTable = new JTable(contenido);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(jTable);
        contenido.addColumn("Numero Tarjeta");
        contenido.addColumn("Tipo de Tarjeta");
        contenido.addColumn("Saldo");
        contenido.addColumn("ID Usuario");

        Usuario usuarioActual = panel.getUsuarioAcutual();
        ServiceTarjeta service = new ServiceTarjeta();
        try {
            ArrayList<Tarjeta> tarjetas = service.consultarTodasPorIDusuario(usuarioActual.getId());
            for (Tarjeta tarjeta : tarjetas) {
                    Object[] fila = new Object[4];
                    fila[0] = tarjeta.getNumeroTarjeta();
                    fila[1] = tarjeta.getTipoTarjeta();
                    fila[2] = tarjeta.getSaldo();
                    fila[3] = tarjeta.getIdUsuario();
                    contenido.addRow(fila);
                };
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar las tarjetas: " + e.getMessage());
        }


        jButtonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(5);
            }
        });


        add(jButtonVolver, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);
        add(jLabelTitulo, BorderLayout.NORTH);
    }
}
