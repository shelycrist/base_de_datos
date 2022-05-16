package controladores;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import vistas.swing.VentanaReportes;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;

import DAO.TicketDao;
import modelos.Ticket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanperez
 */
public class ControladorReportes extends ControladorGeneral {

    VentanaReportes window;
    TicketDao ticketDao;


    public ControladorReportes(                  ) {
        super();
        window = new VentanaReportes(this, this);
        window.setVisible(true);
        ticketDao = new TicketDao();
        fillTickets();
    }

    public void fillTickets() {
        DefaultTableModel modelTickets = new DefaultTableModel();
        List<Ticket> tickets = ticketDao.getAll();  
        modelTickets.setColumnCount(5);
        modelTickets.setColumnIdentifiers(new String[]{"Ticket #", "Creado", "Actualizado", "Estado", "Tema"});
        for (Ticket t : tickets) {
          
            modelTickets.addRow(new Object[]{t.getId(), 
                t.getFecha_creacion(), 
                t.getFecha_actualizacion(),
               t.getStatus(),
            t.getTema().getNombre_tema()});
        }
        window.setModelTickets(modelTickets);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String source = e.getSource().getClass().getName();
        if(source.equals("javax.swing.JButton")){
            JButton btn = (JButton) e.getSource();
            String name = btn.getName();
            if(name.equals("verSolicitud")){
            
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String source = e.getSource().getClass().getName();
        if(source.equals("javax.swing.JLabel")){
            JLabel lbl = (JLabel)e.getSource();
            if(lbl.getName() == "goHome"){
                window.dispose();
                new ControladorBackOffice();
            }
        }
    }
}
