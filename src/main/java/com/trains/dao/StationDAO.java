package com.trains.dao;


import com.trains.model.entity.Station;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class StationDAO extends CrudDAO {

    public List<Station> getAllStations() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Station ").list();
    }

    public List<Station> getStationsForPagination(int page) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Station").setFirstResult(10*(page-1)).setMaxResults(10).list();
    }

    public int getCountStationsForPagination() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count (*) from Station ",Number.class).getSingleResult().intValue();
    }


    public Station getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Station.class,id);
    }

    public Station getByName (String name) {
        Session session = sessionFactory.getCurrentSession();
        Station station = new Station();
        Query query = session.createQuery("from Station where nameStation like :stationName");
        query.setParameter("stationName",name);
        List<Station> stations = query.list();
        if (!stations.isEmpty())  station = stations.get(0);
        return station;
    }

    public boolean isExistStationInDB(String nameStation){
        Session session = sessionFactory.getCurrentSession();
        boolean isExist=true;
        Query query = session.createQuery("from Station  where nameStation like :name");
        query.setParameter("name",nameStation);
        List<Station> stations = query.list();
        if(stations.isEmpty()){
            isExist = false;
        }
        return isExist;
    }

    public void delByID (int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Station.class,id));
    }

    public List<Station> getSortedListByNameStation(int page) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Station order by nameStation");
        return query.setFirstResult(10*(page-1)).setMaxResults(10).list();
    }

}
