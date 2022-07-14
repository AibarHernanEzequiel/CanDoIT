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
    public List<Clima> obtenerTodosLosClimas() {
        return null;
    }

    @Override
    public void persistirDatosDeLaAPI(List<Clima> climasDeLaAPI) {

    }

    @Override
    public void actualizarRepositorio(List<Clima> climas) {

    }
}
