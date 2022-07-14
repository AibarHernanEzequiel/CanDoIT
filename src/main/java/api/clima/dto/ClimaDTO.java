package api.clima.dto;

import api.clima.modelo.Weather;

import java.io.Serializable;

public class ClimaDTO implements Serializable {

    private Long identity;
    private String id;
    private String province;
    private String name;
    private Weather weather;

    public ClimaDTO(Long identity, String id, String province, String name, Weather weather) {
        this.identity = identity;
        this.id = id;
        this.province = province;
        this.name = name;
        this.weather = weather;
    }

    public ClimaDTO() {
    }

    public Long getIdentity() {
        return identity;
    }

    public void setIdentity(Long identity) {
        this.identity = identity;
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

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
