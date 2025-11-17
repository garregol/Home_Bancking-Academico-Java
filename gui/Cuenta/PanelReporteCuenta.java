package gui.Cuenta;

import DAO.Excepciones.DAOException;
import Entidades.Cuenta;
import Service.ServiceCuenta;
import gui.PanelManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelReporteCuenta extends JPanel {
    private ServiceCuenta serviceCuenta;
    private PanelManager panel;
    private PanelManager panelManager;
    private JLabel jLabelTitulo;
    private JTable jTable;
    private DefaultTableModel contenido;
    private JScrollPane scrollPane;
    private JButton jButtonVolver;

    public PanelReporteCuenta(PanelManager panel) {
        this.panel = panel;
        armarTablaReporte();
    }

    public void armarTablaReporte(){
        setLayout(new BorderLayout());
        serviceCuenta = new ServiceCuenta();
        jButtonVolver = new JButton("Volver");
        jLabelTitulo = new JLabel("Reporte de Cuentas");
        contenido= new DefaultTableModel();
        jTable=new JTable(contenido);
        scrollPane=new JScrollPane();
        scrollPane.setViewportView(jTable);
        contenido.addColumn("ID Cuenta");
        contenido.addColumn("ID Usuario");
        contenido.addColumn("Tipo de Cuenta");
        contenido.addColumn("Saldo");
        contenido.addColumn("CBU");
        contenido.addColumn("Alias");
        ServiceCuenta service= new ServiceCuenta();
        try{
            ArrayList<Cuenta> cuentas = service.consultarTodos();
            for(Cuenta cuenta:cuentas) {
                Object[] fila = new Object[6];
                fila[0] = cuenta.getId();
                fila[1] = cuenta.getIdUsuario();
                fila[2] = cuenta.getTipo();
                fila[3] = cuenta.getSaldo();
                fila[4] = cuenta.getCBU();
                fila[5] = cuenta.getAlias();
                contenido.addRow(fila);
            }
        }catch (DAOException e){
            JOptionPane.showMessageDialog(null,"error al mostrar");
        }
        add(jButtonVolver, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);
        add(jLabelTitulo, BorderLayout.NORTH);

        jButtonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(4);
            }
        });
    }
}
