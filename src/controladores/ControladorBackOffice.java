/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import DAO.general.DaoFactory;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import vistas.swing.VentanaBackOffice;

/**
 *
 * @author sarah
 */
public class ControladorBackOffice extends ControladorGeneral {

    VentanaBackOffice ventana;
    DaoFactory daoFactory;

    public ControladorBackOffice() {
        super();
        //Inicializo las vars de arriba
        daoFactory = new DaoFactory();
        ventana = new VentanaBackOffice(this);
        ventana.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == ventana.getNew_ticket()) {
            new ControladorCrearTicket();
            ventana.dispose();
        } else if (e.getSource() == ventana.getReportes()) {
            new ControladorReportes();
            ventana.dispose();
        }

    }

}
