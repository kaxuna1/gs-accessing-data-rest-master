package main.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by KGelashvili on 11/2/2015.
 */
@Entity
@Table(name = "formats")
public class Format {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "formatId")
    private long id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private float price;

    public Format() {
    }
    public Format(String name,float price) {
        this.name=name;
        this.price=price;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
