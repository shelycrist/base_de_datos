package DAO.general;

import DAO.TicketDao;
import DAO.TemaDao;

public class DaoFactory {
    public IDao getDao(String entity){
        if(entity.equals("ticket")) return new TicketDao();
        if(entity.equals("tema")) return new TemaDao();
        return null;
    }
}
