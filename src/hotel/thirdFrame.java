package hotel;

import PaqC07.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class thirdFrame extends JFrame{
    private JPanel thirdPanel;
    private JTextArea taMapaHotel;
    private JTextField tfNum;
    private JTextField tfDNI;
    private JLabel lbDNI;
    private JLabel lbNum;
    private JButton btAnular;
    private JButton btCancelar;
    private JLabel lbTipo;
    private JTextField tfTipo;
    protected Registro H;

    /////////////////////*CONSTRUCTOR*////////////////////////////////////////////////////
    thirdFrame(){
        H = secondFrame.H;
        setContentPane(thirdPanel);
        setTitle("Getsión de reservas");
        setSize(650,300);
        this.mostrarMapa();
        setVisible(true);

        /////////////////////*GESTIONAR EL BOTÓN "CANCELAR"*////////////////////////////////////////////////////
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondFrame A = new secondFrame();
                dispose();
            }
        });

        /////////////////////*GESTIONAR EL BOTÓN "ANULAR"*////////////////////////////////////////////////////
        btAnular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tipo = Integer.parseInt(tfTipo.getText());
                int DNI = Integer.parseInt(tfDNI.getText());
                int num = Integer.parseInt(tfNum.getText());
                H.anulaReserva(DNI,tipo,num);
                try {
                    secondFrame.Serializar(H);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                mostrarMapa();
                JOptionPane.showMessageDialog(null,"El proceso de anulación ha finalizado.");
                tfNum.setText("");
                tfTipo.setText("");
            }
        });

    }

    public static void main(String[] args) {thirdFrame A = new thirdFrame();}

    /////////////////////*MUESTRA EL MAPA DEL HOTEL EN EL TEXT_AREA MAPAHOTEL*//////////////////////////////////////////
    public void mostrarMapa(){
        String mostrar = new String();
        for (int i = 0; i < H.numPisos; ++i) {
            for (int j = 0; j < H.numHab; ++j) {
                if (H.habitaciones[i][j] == null){
                    mostrar = mostrar + " L ";
                } else {
                    mostrar = mostrar + " R ";
                }
            }

            mostrar = mostrar + "\n";
        }
        taMapaHotel.setText(mostrar);
    }


}
