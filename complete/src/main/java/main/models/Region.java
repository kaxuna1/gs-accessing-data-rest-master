package main.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    public Region(){

    }

    public Region(String name) {
        Name = name;
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
}
