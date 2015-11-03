package main.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KGelashvili on 11/3/2015.
 */
@Entity
@Table(name = "zones")
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "zoneId")
    private long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "regionId")
    private Region region;

    @JsonIgnore
    @OneToMany(mappedBy = "zone",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<User> users;

    public Zone(){

    }
    public Zone(String name,Region region){
        this.name=name;
        this.region=region;
        this.users=new ArrayList<User>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
