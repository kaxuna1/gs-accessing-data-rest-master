package main.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by vakhtanggelashvili on 10/22/15.
 */
@Entity
@Table(name = "Organisations")
public class Organisation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String address;

    @NotNull
    @Column
    private String mobileNumber;

    @NotNull
    @Column
    private String email;


    @NotNull
    @Column
    private int regionId;

    public Organisation(String name, String address, String mobileNumber, String email, int regionId) {
        this.name = name;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.regionId = regionId;
    }
    public Organisation(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public long getId() {
        return id;
    }
}
