package main.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vakhtanggelashvili on 10/23/15.
 */
@Entity
@Table(name = "parcels")
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parcelId")
    private long id;

    @Column
    @NotNull
    private long organisationId;

    @Column
    @NotNull
    private long userId;
    @Column
    @NotNull
    private String barcode;
    @Column
    @NotNull
    private String reciever;

    @Column
    @NotNull
    private String address;

    @Column
    @NotNull
    private String sentFrom;

    @Column
    @NotNull
    private Date expectedDeliveryDate;

    @Column
    private Date deliveryDate;

    @Column
    @NotNull
    private int status;

    @ManyToOne
    @JoinColumn(name = "formatId")
    private Format format;

    @Column
    @NotNull
    private long serviceTypeId;

    @Column
    private String comment;

    @Column
    private String recievedBy;

    @ManyToOne
    @JoinColumn(name = "regionId")
    private Region region;

    @Column
    private byte[] signature;

    @OneToMany(mappedBy = "parcel",cascade = CascadeType.ALL)
    private List<Movement> movements;

    public Parcel(){

    }

    public Parcel(long organisationId, long userId, String reciever, String address, String sentFrom, Date expectedDeliveryDate, int status, long formatId, long serviceTypeId, String barCode, long zoneId, Region region) {
        this.region = region;
        //this.regionId = regionId;
        this.setOrganisationId(organisationId);
        this.setUserId(userId);
        this.setReciever(reciever);
        this.setAddress(address);
        this.setSentFrom(sentFrom);
        this.setExpectedDeliveryDate(expectedDeliveryDate);
        this.setStatus(status);
        this.setFormatId(formatId);
        this.setServiceTypeId(serviceTypeId);
        this.setBarcode(barCode);
        //this.setZoneId(zoneId);
        this.movements=new ArrayList<Movement>();
    }
    public void addMovement(Movement movement){
        this.movements.add(movement);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(long organisationId) {
        this.organisationId = organisationId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSentFrom() {
        return sentFrom;
    }

    public void setSentFrom(String sentFrom) {
        this.sentFrom = sentFrom;
    }

    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getFormatId() {
        return formatId;
    }

    public void setFormatId(long formatId) {
        this.formatId = formatId;
    }

    public long getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(long serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRecievedBy() {
        return recievedBy;
    }

    public void setRecievedBy(String recievedBy) {
        this.recievedBy = recievedBy;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<Movement> getMovements() {
        return movements;
    }

    public void setMovements(List<Movement> movements) {
        this.movements = movements;
    }

/*    public long getRegionId() {
        return regionId;
    }

    public void setRegionId(long regionId) {
        this.regionId = regionId;
    }

    public long getZoneId() {
        return zoneId;
    }

    public void setZoneId(long zoneId) {
        this.zoneId = zoneId;
    }*/

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }
}
