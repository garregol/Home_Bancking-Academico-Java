package gui.Movimientos;

import Entidades.Movimientos;
import Service.ServiceMovimientos;
import gui.PanelManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PanelAuditoria extends JPanel {
    private ServiceMovimientos serviceMovimientos;
    private PanelManager panel;
    private JLabel jLabelTitulo;
    private JButton Volver;
    private JTable jTable;
    private DefaultTableModel contenido;
    private JScrollPane scrollPane;

    public PanelAuditoria(PanelManager panel) {
        this.panel = panel;
        armarTablaReporte();
    }

    public void armarTablaReporte() {
        setLayout(new BorderLayout());
        serviceMovimientos = new ServiceMovimientos();
        jLabelTitulo = new JLabel("Auditoría de Movimientos");

        Volver = new JButton("Volver");
        contenido = new DefaultTableModel();
        jTable = new JTable(contenido);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(jTable);

        contenido.addColumn("ID Movimiento");
        contenido.addColumn("ID Cuenta");
        contenido.addColumn("FECHA");
        contenido.addColumn("DESCRIPCIÓN");
        contenido.addColumn("TIPO");
        contenido.addColumn("MONTO");

        try {
            List<Movimientos> movimientos = serviceMovimientos.consultarTodos();
            for (Movimientos movimiento : movimientos) {
                Object[] fila = new Object[6];
                fila[0] = movimiento.getId();
                fila[1] = movimiento.getIdCuenta();
                fila[2] = movimiento.getFecha();
                fila[3] = movimiento.getDescripcion();
                fila[4] = movimiento.getTipoMovimiento();
                fila[5] = movimiento.getMonto();
                contenido.addRow(fila);
            };
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los movimientos: " + e.getMessage());
        }

        add(jLabelTitulo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(Volver, BorderLayout.SOUTH);

        Volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(4);
            }
        });
    }
}
