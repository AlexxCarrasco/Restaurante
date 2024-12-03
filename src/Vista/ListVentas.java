package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.*;
import Controlador.*;

public class ListVentas extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable table1;
    private JLabel datos;
    private TableModel tableModel;

    public ListVentas() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        datos.setVisible(false);

        String [] columnas = {"RUT","NOMBRE","FECHA","HORA","NUMERO PRODUCTOS","TOTAL"};
        String [][] datosListado = Controlador.getInstance().listVentas();

        if(datosListado.length>0){
            tableModel = new DefaultTableModel(datosListado, columnas){
                @Override
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            };

            table1.setModel(tableModel);

            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
            for(int i=0; i<table1.getColumnCount(); i++){
                table1.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
            }
        }else{
            datos.setVisible(true);
            datos.setText("No hay datos");
        }


        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }


    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void display() {
        ListVentas dialog = new ListVentas();
        dialog.pack();
        dialog.setAlwaysOnTop(true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);


    }
}
