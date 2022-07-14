package api.clima.repositorios;

import api.clima.modelo.Clima;
import org.hibernate.Session;
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
        return getSession().createQuery("select c from Clima c", Clima.class).list();
    }

    @Override
    public void persistirDatosDeLaAPI(List<Clima> climasDeLaAPI) {
        for (Clima clima : climasDeLaAPI) {
            getSession().save(clima);
        }
    }

    @Override
    public void actualizarRepositorio(List<Clima> climasActualizados) {
        List<Clima> todosLosClimas = this.obtenerTodosLosClimas();
        for (Clima clima : todosLosClimas) {
            for (Clima climaActualizado : climasActualizados) {
                if (esElMismoIdDelWeather(clima, climaActualizado)) {
                    setearDatosAActualizar(clima, climaActualizado);
                    getSession().update(clima);
                }
            }
        }
    }

    private boolean esElMismoIdDelWeather(Clima clima, Clima climaActualizado) {
        return clima.getWeather().getId() == climaActualizado.getWeather().getId();
    }

    private void setearDatosAActualizar(Clima clima, Clima climaActualizado) {
        clima.getWeather().setTemp(climaActualizado.getWeather().getTemp());
        clima.getWeather().setTempDesc(climaActualizado.getWeather().getTempDesc());
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
