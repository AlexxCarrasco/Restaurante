package Vista;

import Controlador.Controlador;
import Modelo.Producto;
import Modelo.Venta;

import javax.swing.*;
import java.awt.event.*;

public class MasProductos extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;

    public MasProductos(Venta venta) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        Producto [] productos = Controlador.getInstance().getProductos();
        for(Producto producto: productos){
            comboBox1.addItem(producto);
        }

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK(venta);
            }
        });

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

    private void onOK(Venta venta) {
        Producto producto = (Producto) comboBox1.getSelectedItem();
        Controlador.getInstance().addVenta(producto,venta);
        JOptionPane.showMessageDialog(this,"Producto agregado","",JOptionPane.PLAIN_MESSAGE);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void display(Venta venta) {
        MasProductos dialog = new MasProductos(venta);
        dialog.pack();
        dialog.setAlwaysOnTop(true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
