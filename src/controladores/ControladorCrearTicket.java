package controladores;

import DAO.TemaDao;
import DAO.general.DaoFactory;
import DAO.general.IDao;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import vistas.general.ComboboxItem;
import vistas.swing.VentanaCrearTicket;
import javax.swing.JLabel;
import modelos.Tema;
import modelos.Ticket;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanperez
 */
public class ControladorCrearTicket extends ControladorGeneral implements ListSelectionListener {

    VentanaCrearTicket window;
    DaoFactory daoFactory;

    public ControladorCrearTicket() {
        super();
        daoFactory = new DaoFactory();
        window = new VentanaCrearTicket(this, this, this);
        window.setVisible(true);
        initCrear();
    }

    public void initCrear() {
        fillTema();
    }

    public void fillTema() {
        DefaultComboBoxModel modelTema = new DefaultComboBoxModel();
        TemaDao temaDao = new TemaDao();
        List<Tema> temaList = temaDao.getAll();
        for (Tema tem : temaList) {
            modelTema.addElement(
                    new ComboboxItem(String.valueOf(tem.getId()), tem.getNombre_tema()));
        }
        window.setModelTema(modelTema);
    }

    public Ticket getTicket() {
        Ticket ticket = new Ticket();
        ticket.setNombre_contacto(window.getNombre_contacto().getText());
        ticket.setEmail(window.getNombre_contacto().getText());
        ticket.setTelefono(window.getTelefono().getText());
        ticket.setTema_id(Integer.valueOf(((ComboboxItem) window.getTema().getSelectedItem()).getId()));
        ticket.setFactura_id(Integer.valueOf(window.getNumero_factura().getText()));
        ticket.setDetalle_falle(window.getDetalle().getText());
        return ticket;
    }

    public void save() {
        try {

            Ticket newTicket = getTicket();
            String entity = "ticket";
            System.out.println("save" + '-' + entity);

            System.out.println(newTicket.toString());
            IDao entityDao = daoFactory.getDao(entity);
            //!validacion costo
            entityDao.save(newTicket);
            window.mostrarMensaje("Se agrego el registro con exito ");
            initCrear();

            //((VentanaCrearSolicitud) window).clear();
        } catch (Exception e) {
            System.out.println("controladores.ControladorAddSolicitud.save()" + e);
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent l) {

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        var source = arg0.getSource();

        if (source == window.getEnviarTicket()) {
            save();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String source = e.getSource().getClass().getName();
        if (source.equals("javax.swing.JLabel")) {
            JLabel lbl = (JLabel) e.getSource();
            if (lbl.getName() == "goHome") {
                window.dispose();
                new ControladorBackOffice();
            }
        }
    }

}
