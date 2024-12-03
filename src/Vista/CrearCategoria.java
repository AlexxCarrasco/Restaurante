package Vista;

import Controlador.Controlador;
import Excepciones.Restaurante;
import Modelo.Tipo;

import javax.swing.*;
import java.awt.event.*;

public class CrearCategoria extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nombretxt;
    private JComboBox tipoCB;

    public CrearCategoria() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        Tipo [] tipos = Controlador.getInstance().getTipos();
        for(Tipo tipo: tipos){
            tipoCB.addItem(tipo);
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
        String nombre = nombretxt.getText();
        Tipo tipo = (Tipo) tipoCB.getSelectedItem();
        if(!nombre.isEmpty()){
            try{
                Controlador.getInstance().crearCategoria(nombre,tipo);
                JOptionPane.showMessageDialog(this,"Creado","",JOptionPane.PLAIN_MESSAGE);
                dispose();
            }catch (Restaurante e){
                JOptionPane.showMessageDialog(this,e.getMessage(),"",JOptionPane.ERROR_MESSAGE);
            }

        }else{
            JOptionPane.showMessageDialog(this,"Ingrese todos los datos","",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void display() {
        CrearCategoria dialog = new CrearCategoria();
        dialog.pack();
        dialog.setAlwaysOnTop(true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
