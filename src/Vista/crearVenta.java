package Vista;

import Controlador.Controlador;
import Excepciones.Restaurante;
import Modelo.Cliente;
import Modelo.Producto;
import Modelo.Venta;

import javax.swing.*;
import java.awt.event.*;

public class crearVenta extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox precioCB;
    private JTextField ruttxt;
    private JLabel infotxt;

    public crearVenta() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        Producto [] productos = Controlador.getInstance().getProductos();
        for(Producto producto: productos){
            precioCB.addItem(producto);
        }

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
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

    private void onOK() {
        String rut = ruttxt.getText();
        Producto producto = (Producto) precioCB.getSelectedItem();
        if(!rut.isEmpty()){

            try{
                Venta venta = Controlador.getInstance().crearVenta(rut);
                JOptionPane.showMessageDialog(this,"venta exitosa","",JOptionPane.PLAIN_MESSAGE);
                venta.addProducto(producto);
                MasProductos.display(venta);


            }catch(Restaurante e){
                JOptionPane.showMessageDialog(this,e.getMessage(),"",JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this,"Ingrese todos los datos","",JOptionPane.ERROR_MESSAGE);
        }
    }



    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void display() {
        crearVenta dialog = new crearVenta();
        dialog.pack();
        dialog.setAlwaysOnTop(true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);


    }
}
