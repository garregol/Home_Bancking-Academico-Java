package gui.Tarjeta;

import DAO.Excepciones.DAOException;
import Entidades.Tarjeta;
import Service.ServiceTarjeta;
import gui.PanelManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelReporteTarjeta extends JPanel {
    private PanelManager panelManager;
    private JTable jTable;
    private JLabel jLabelTitulo;
    private DefaultTableModel contenido;
    private JScrollPane scrollPane;
    private JButton jButtonVolver;

    public PanelReporteTarjeta(PanelManager panelManager) {
        this.panelManager = panelManager;
        armarTablaReporte();
    }

    public void armarTablaReporte() {
        setLayout(new BorderLayout());
        contenido = new DefaultTableModel();
        jTable = new JTable(contenido);
        jLabelTitulo = new JLabel("Reporte de Tarjetas del Usuario");
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(jTable);
        contenido.addColumn("Numero Tarjeta");
        contenido.addColumn("Tipo de Tarjeta");
        contenido.addColumn("Saldo");
        contenido.addColumn("ID_USUARIO");

        ServiceTarjeta service = new ServiceTarjeta();
        try {
            ArrayList<Tarjeta> tarjetas = service.consultarTodas();
            for (Tarjeta tarjeta : tarjetas) {
                Object[] fila = new Object[5];
                fila[0] = tarjeta.getNumeroTarjeta();
                fila[1] = tarjeta.getTipoTarjeta();
                fila[2] = tarjeta.getSaldo();
                fila[3] = tarjeta.getIdUsuario();
                contenido.addRow(fila);
            }
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar las tarjetas");
        }

        jButtonVolver = new JButton("Volver");
        add(jButtonVolver, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);
        add(jLabelTitulo, BorderLayout.NORTH);

        jButtonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrar(4);
            }
        });

    }

}
