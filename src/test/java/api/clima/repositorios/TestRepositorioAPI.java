package api.clima.repositorios;

import api.clima.SpringTest;
import api.clima.modelo.Clima;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TestRepositorioAPI extends SpringTest {


    @Test
    @Transactional
    @Rollback
    public void verificarSession() {
        assertThat(session().isConnected()).isTrue();
    }


    @Autowired
    private RepositorioAPI repositorioAPI;
    private final RestOperations restTemplate = new RestTemplate();

}
