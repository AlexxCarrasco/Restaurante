package Vista;

import Controlador.Controlador;
import Excepciones.Restaurante;
import Modelo.Categoria;

import javax.swing.*;
import java.awt.event.*;

public class CrearProducto extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JLabel nombretxt;
    private JLabel preciotxt;
    private JLabel categoriaCB;

    public CrearProducto() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        Categoria [] categorias = Controlador.getInstance().getCategorias();
        for(Categoria categoria: categorias){
            comboBox1.addItem(categoria);
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
        String nombre = textField1.getText();
        String precio = textField2.getText();
        Categoria categoria = (Categoria) comboBox1.getSelectedItem();

        if(!nombre.isEmpty() && !precio.isEmpty()){
            try{
                int precioInt = Integer.parseInt(precio);
                Controlador.getInstance().crearProducto(nombre,precioInt,categoria);
                JOptionPane.showMessageDialog(this,"Producto creado","Exito",JOptionPane.PLAIN_MESSAGE);
                dispose();


            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(this,"Ingrese numero valido","Error",JOptionPane.ERROR_MESSAGE);
            }catch(Restaurante e){
                JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }

        }else{
            JOptionPane.showMessageDialog(this,"ingrese todos los datos","",JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void display(){
        CrearProducto dialog = new CrearProducto();
        dialog.pack();
        dialog.setAlwaysOnTop(true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

    }
}
