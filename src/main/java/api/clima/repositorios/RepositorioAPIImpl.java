package api.clima.repositorios;

import api.clima.modelo.Clima;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioAPIImpl implements RepositorioAPI {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioAPIImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardarAPI(List<Clima> climas) {
        for (Clima clima : climas) {
            sessionFactory.getCurrentSession().save(clima);
        }
    }

    @Override
    public List<Clima> obtenerTodosLosClimas() {
        return sessionFactory.getCurrentSession().createQuery("select c from Clima c", Clima.class).list();
    }

    @Override
    public void actualizarRepositorio(List<Clima> climasDeLaAPI) {
        List<Clima> climasDelRepositorio = this.obtenerTodosLosClimas();
        if (climasDelRepositorio.isEmpty()) {
            this.guardarAPI(climasDeLaAPI);
        } else {
            for (Clima clima : climasDelRepositorio) {
                sessionFactory.getCurrentSession().delete(clima);
            }
            this.guardarAPI(climasDeLaAPI);
        }
    }
}
