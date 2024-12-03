package Vista;

import Controlador.Controlador;
import Excepciones.Restaurante;

import javax.swing.*;
import java.awt.event.*;

public class CrearCliente extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nombretxt;
    private JTextField ruttxt;

    public CrearCliente() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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
        String nombre = nombretxt.getText();
        String rut = ruttxt.getText();
        if(!nombre.isEmpty() && !rut.isEmpty()){
            try{
                Controlador.getInstance().crearCliente(nombre,rut);
                JOptionPane.showMessageDialog(this,"Cliente creado exitosamente","Exito",JOptionPane.PLAIN_MESSAGE);
                dispose();
            }catch (Restaurante e){
                JOptionPane.showMessageDialog(this, e.getMessage(),"Mensaje Error",JOptionPane.ERROR_MESSAGE);
            }


        }else{
            JOptionPane.showMessageDialog(this, "Ingrese todos los datos");
        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void display() {
        CrearCliente dialog = new CrearCliente();
        dialog.pack();
        dialog.setAlwaysOnTop(true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);


    }

}
