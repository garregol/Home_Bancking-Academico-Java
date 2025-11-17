package gui.Movimientos;

import Entidades.Cuenta;
import Entidades.Movimientos;
import Entidades.Usuario;
import Service.ServiceCuenta;
import Service.ServiceMovimientos;
import gui.PanelManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PanelReporteMovimientoUsuario extends JPanel {
    private ServiceMovimientos serviceMovimientos;
    private ServiceCuenta serviceCuenta;
    private PanelManager panel;
    private JLabel jLabelTitulo;
    private JButton Volver;
    private JTable jTable;
    private DefaultTableModel contenido;
    private JScrollPane scrollPane;

    public PanelReporteMovimientoUsuario(PanelManager panel) {
        this.panel = panel;
        armarTablaReporte();
    }

    public void armarTablaReporte() {
        setLayout(new BorderLayout());
        serviceMovimientos = new ServiceMovimientos();
        serviceCuenta = new ServiceCuenta();
        jLabelTitulo = new JLabel("Reporte de Movimientos del Usuario");

        Volver = new JButton("Volver");
        contenido = new DefaultTableModel();
        jTable = new JTable(contenido);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(jTable);

        contenido.addColumn("ID Movimiento");
        contenido.addColumn("ID Cuenta");
        contenido.addColumn("Fecha");
        contenido.addColumn("Descripción");
        contenido.addColumn("Tipo de Movimiento");
        contenido.addColumn("Monto");


        try {
            Usuario usuario = panel.getUsuarioAcutual();
            int idusuario = usuario.getId();

            // Buscar todas las cuentas del usuario
            List<Cuenta> cuentas = serviceCuenta.consultarTodosID(idusuario);

            for (Cuenta cuenta : cuentas) {
                // Buscar movimientos de cada cuenta
                List<Movimientos> movimientos = serviceMovimientos.consultarPorIdCuenta(cuenta.getId());
                for (Movimientos movimiento : movimientos) {
                    Object[] fila = new Object[6];
                    fila[0] = movimiento.getId();
                    fila[1] = movimiento.getIdCuenta();
                    fila[2] = movimiento.getFecha();
                    fila[3] = movimiento.getDescripcion();
                    fila[4] = movimiento.getTipoMovimiento();
                    fila[5] = movimiento.getMonto();
                    contenido.addRow(fila);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los movimientos: " + e.getMessage());
        }


        add(jLabelTitulo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(Volver, BorderLayout.SOUTH);

        Volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(5);
            }
        });
    }
}
