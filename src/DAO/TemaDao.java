package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.general.IDao;
import config.Connection.Conne;
import modelos.Tema;

public class TemaDao implements IDao<Tema> {

    private Conne con;

    @Override
    public Tema setEntity(ResultSet rs) {
        try {
            Tema tema = new Tema();
            tema.setId(rs.getInt("id"));
            tema.setDescripcion(rs.getString("descripcion"));
            tema.setNombre_tema(rs.getString("nombre_tema"));
            tema.setFecha_creacion(rs.getDate("fecha_creacion"));
            tema.setFecha_actualizacion(rs.getDate("fecha_actualizacion"));
            return tema;
        } catch (SQLException e) {
            String msg = "Error asignando los datos obtenidos\n" + e.getMessage();
            System.out.println(msg);
            return null;
        }
    }

    public TemaDao() {
    }

    @Override
    public List<Tema> getAll() {
        try {
            List<Tema> list = new ArrayList<Tema>();
            con = new Conne();
            con.open();
            String sql = "SELECT *" + " FROM tema";
            ResultSet rs = con.execQuery(sql);
            if (con.isResultSetEmpty(rs)) {
                return list;
            }
            do {
                Tema tema = setEntity(rs);
                list.add(tema);
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
    public void save(Tema tema) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Tema t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Tema t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Tema get(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
