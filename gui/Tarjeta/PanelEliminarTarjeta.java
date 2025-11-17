package gui.Tarjeta;

import Entidades.Tarjeta;
import Service.ServiceTarjeta;
import gui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PanelEliminarTarjeta extends JPanel {
    private ServiceTarjeta serviceTarjeta;
    private PanelManager panel;
    private JLabel jLabelTitulo;
    private JLabel jLabelNumeroTarjeta;
    private JComboBox<Integer> jComboBoxNumeroTarjeta;
    private JButton jButtonEliminar;
    private JButton jButtonVolver;
    private JPanel panelEliminarTarjeta;

   public PanelEliminarTarjeta(PanelManager panel){
       this.panel = panel;
       armarPanelEliminarTarjeta();
   }

   public void armarPanelEliminarTarjeta() {
         this.serviceTarjeta = new ServiceTarjeta();


       jLabelTitulo = new JLabel("Eliminar Tarjeta");
       jLabelNumeroTarjeta = new JLabel("Número de Tarjeta:");
       jComboBoxNumeroTarjeta = new JComboBox<>();
       List<Tarjeta> tarjetas = serviceTarjeta.consultarTodas();
       for(Tarjeta tarjeta:tarjetas){
           jComboBoxNumeroTarjeta.addItem(tarjeta.getNumeroTarjeta());
       }
       jButtonEliminar = new JButton("Eliminar");
       jButtonVolver = new JButton("Volver");

       // Layout and add components to the panel
       panelEliminarTarjeta = new JPanel();

       panelEliminarTarjeta.setLayout(new GridBagLayout());
         GridBagConstraints c = new GridBagConstraints();

         c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 2;
            c.gridx = 0;
            c.gridy = 0;
            panelEliminarTarjeta.add(jLabelTitulo, c);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 2;
            c.gridx = 0;
            c.gridy = 1;
            panelEliminarTarjeta.add(jLabelNumeroTarjeta, c);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 2;
            c.gridx = 1;
            c.gridy = 1;
            panelEliminarTarjeta.add(jComboBoxNumeroTarjeta, c);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 2;
            c.gridx = 0;
            c.gridy = 2;
            panelEliminarTarjeta.add(jButtonEliminar, c);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 2;
            c.gridx = 1;
            c.gridy = 2;
            panelEliminarTarjeta.add(jButtonVolver, c);


         jButtonEliminar.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Integer numeroTarjetaSeleccionado = (Integer) jComboBoxNumeroTarjeta.getSelectedItem();
                 if (numeroTarjetaSeleccionado == null) {
                     JOptionPane.showMessageDialog(PanelEliminarTarjeta.this, "Por favor, seleccione una tarjeta.", "Error", JOptionPane.ERROR_MESSAGE);
                     return;
                 }
                 Tarjeta tarjetaSeleccionada = serviceTarjeta.consultarPorNumero(numeroTarjetaSeleccionado);
                 try {

                     if (tarjetaSeleccionada == null) {
                         JOptionPane.showMessageDialog(PanelEliminarTarjeta.this, "Tarjeta no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
                         return;
                     }
                     if (tarjetaSeleccionada.getSaldo() > 0) {
                         JOptionPane.showMessageDialog(PanelEliminarTarjeta.this, "No se puede eliminar una tarjeta con saldo positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                         return;
                     }
                     if (tarjetaSeleccionada.getSaldo() < 0) {
                         JOptionPane.showMessageDialog(PanelEliminarTarjeta.this, "No se puede eliminar una tarjeta con saldo Negativo.", "Error", JOptionPane.ERROR_MESSAGE);
                         return;
                     }

                     int numeroTarjeta = (Integer) jComboBoxNumeroTarjeta.getSelectedItem();
                     serviceTarjeta.eliminar(numeroTarjeta);
                     JOptionPane.showMessageDialog(PanelEliminarTarjeta.this, "Tarjeta eliminada exitosamente.");
                     panel.mostrar(4);
                 } catch (Exception ex) {
                     JOptionPane.showMessageDialog(PanelEliminarTarjeta.this, "Error al eliminar la tarjeta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                 }
             }
         });

            jButtonVolver.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel.mostrar(4);
                }
            });

       this.setLayout(new BorderLayout());
       this.add(panelEliminarTarjeta, BorderLayout.CENTER);




   }
}
