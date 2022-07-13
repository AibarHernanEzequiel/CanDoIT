package api.clima.modelo;

import javax.persistence.*;

@Entity
public class Clima {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identity;
    @Column
    private String province;
    @Column
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    private Weather weather;

    public Clima() {
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public void setIdentity(Long id) {
        this.identity = id;
    }

    public Long getIdentity() {
        return identity;
    }


    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
