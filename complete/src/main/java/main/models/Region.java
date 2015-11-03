package main.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vakhtanggelashvili on 10/23/15.
 */
@Entity
@Table(name = "regions")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "regionId")
    private long id;

    @Column
    @NotNull
    private String Name;

    @JsonIgnore
    @OneToMany(mappedBy = "region",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Parcel> parcels;

    @JsonIgnore
    @OneToMany(mappedBy = "region",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Zone> zones;

    @JsonIgnore
    @OneToMany(mappedBy = "region",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<User> users;

    public Region(){

    }

    public Region(String name) {
        Name = name;
        this.zones=new ArrayList<Zone>();
        this.parcels=new ArrayList<Parcel>();
        this.users=new ArrayList<User>();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Zone> getZones() {
        return zones;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
