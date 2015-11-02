package main.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by KGelashvili on 11/2/2015.
 */
@Entity
@Table(name = "service_types")
public class ServiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "serviceTypeId")
    private long id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private float pricePlus;
    public ServiceType(){

    }
    public ServiceType(String name,float pricePlus){
        this.name=name;
        this.pricePlus=pricePlus;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPricePlus() {
        return pricePlus;
    }

    public void setPricePlus(float pricePlus) {
        this.pricePlus = pricePlus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
