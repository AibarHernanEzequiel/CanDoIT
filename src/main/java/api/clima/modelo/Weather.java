package api.clima.modelo;

import javax.persistence.*;

@Entity
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identity;
    @Column
    private int id;
    @Column
    private float temp;
    @Column
    private String tempDesc;

    public Weather() {
    }

    public void setIdentity(Long id) {
        this.identity = id;
    }

    public Long getIdentity() {
        return identity;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }


    public String getTempDesc() {
        return tempDesc;
    }

    public void setTempDesc(String tempDesc) {
        this.tempDesc = tempDesc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
