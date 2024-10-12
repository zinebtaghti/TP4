package stor.ensa.ma.stor.service;

import java.util.ArrayList;
import java.util.List;

import stor.ensa.ma.stor.beans.Show;
import stor.ensa.ma.stor.dao.IDao;

public class ShowService implements IDao<Show> {
    private List<Show> shows;
    private static ShowService instance;
    private ShowService() {
        this.shows = new ArrayList<>();
    }
    public static ShowService getInstance() {
        if(instance == null)
            instance = new ShowService();
        return instance;
    }
    @Override
    public boolean create(Show o) {
        return shows.add(o);
    }
    @Override
    public boolean update(Show o) {
        for(Show s : shows){
            if(s.getStar() == o.getId()){
                s.setImg(o.getImg());
                s.setName(o.getName());
                s.setStar(o.getStar());
            }
        }
        return true;
    }
    @Override
    public boolean delete(Show o) {
        return shows.remove(o);
    }
    @Override
    public Show findById(int id) {
        for(Show s : shows){
            if(s.getId() == id)
                return s;
        }
        return null;
    }
    @Override
    public List<Show> findAll() {
        return shows;
    }
}