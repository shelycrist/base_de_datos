package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAO.general.IDao;
import config.Connection.Conne;
import java.time.Instant;
import modelos.Tema;
import modelos.Ticket;

public class TicketDao implements IDao<Ticket> {

    private Conne con;

    @Override
    public Ticket setEntity(ResultSet rs) {
        try {
            Ticket ticket = new Ticket();

            ticket.setId(rs.getInt("id"));
            ticket.setNombre_contacto(rs.getString("nombre_contacto"));
            ticket.setEmail(rs.getString("email"));
            ticket.setTelefono(rs.getString("telefono"));
            ticket.setTema_id(rs.getInt("tema_id"));
            ticket.setFactura_id(rs.getInt("factura_id"));
            ticket.setDetalle_falle(rs.getString("detalle_falla"));
            ticket.setStatus(rs.getString("status"));
            ticket.setFecha_creacion(rs.getDate("fecha_creacion"));
            ticket.setFecha_actualizacion(rs.getDate("fecha_actualizacion"));

            Tema tema = new Tema();
            tema.setDescripcion(rs.getString("descripcion"));
            tema.setNombre_tema(rs.getString("nombre_tema"));

            ticket.setTema(tema);
            return ticket;
        } catch (SQLException e) {
            String msg = "Error asignando los datos obtenidos\n" + e.getMessage();
            System.out.println(msg);
            return null;
        }
    }

    public TicketDao() {
    }

    @Override
    public List<Ticket> getAll() {
        try {
            List<Ticket> list = new ArrayList<Ticket>();
            con = new Conne();
            con.open();
            String sql = "SELECT *"
                    + " FROM ticket as ti JOIN tema as te ON ti.tema_id = te.id";
            ResultSet rs = con.execQuery(sql);
            if (con.isResultSetEmpty(rs)) {
                return list;
            }
            do {
                Ticket ticket = setEntity(rs);
                list.add(ticket);
            } while (rs.next());
            return list;
        } catch (SQLException e) {
            String msg = "Error obteniendo los datos de la bd\n" + e.getMessage();
            System.out.println(msg);
            e.printStackTrace();
            return null;
        } finally {
            con.close();
        }
    }

    @Override
    public void save(Ticket ticket) {
        try {

            Timestamp now = new Timestamp(new Date().getTime());

            con = new Conne();
            con.open();
            String sql = "INSERT INTO ticket (nombre_contacto, email, telefono, tema_id, factura_id, detalle_falla, status,fecha_creacion,fecha_actualizacion) "
                    + "VALUES (?,?,?,?,?,?,?,?,?);";
            String[] params = {
                ticket.getNombre_contacto(),
                ticket.getEmail(),
                ticket.getTelefono(),
                String.valueOf(ticket.getTema_id()),
                String.valueOf(ticket.getFactura_id()),
                ticket.getDetalle_falle(),
                "Activo",
                now.toString(),
                now.toString()
            };
            con.execMutation(sql, params);

        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            con.close();
        }
    }

    @Override
    public void update(Ticket t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Ticket t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Ticket get(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
