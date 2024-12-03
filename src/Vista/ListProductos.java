package Vista;

import Controlador.Controlador;
import Modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.*;

public class ListProductos extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton bebestibleRadioButton;
    private JRadioButton comestibleRadioButton;
    private JTable table1;
    private JLabel texto;
    private TableModel tableModel;

    public ListProductos() {
        setContentPane(contentPane);
        setModal(true);


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
        bebestibleRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                texto.setText("Lista de bebestibles");
                listaBebestible();
            }
        });
        comestibleRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                texto.setText("Lista de comida");
                listaComida();
            }
        });
        comestibleRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void listaBebestible() {
        String [][] bebestibles = Controlador.getInstance().getBebestible();
        String [] columnas = {"NOMBRE","PRECIO"};
        tableModel = new DefaultTableModel(bebestibles, columnas){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        table1.setModel(tableModel);
        renderizar();
    }

    private void listaComida() {
        String [][] comida = Controlador.getInstance().getComida();
        String [] columnas = {"NOMBRE","PRECIO"};
        tableModel = new DefaultTableModel(comida, columnas){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        table1.setModel(tableModel);
        renderizar();
    }

    public void renderizar(){
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        for(int i=0; i<table1.getColumnCount(); i++){
            table1.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void display() {
        ListProductos dialog = new ListProductos();
        dialog.pack();
        dialog.setAlwaysOnTop(true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
