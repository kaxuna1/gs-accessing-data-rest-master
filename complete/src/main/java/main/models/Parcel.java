package main.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by vakhtanggelashvili on 10/23/15.
 */
@Entity
@Table(name = "parcels")
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @NotNull
    private int status;

    @Column
    @NotNull
    private long formatId;

    @Column
    @NotNull
    private long serviceTypeId;

    @Column
    @NotNull
    private String comment;

    @Column
    @NotNull
    private String recievedBy;

    @Column
    @NotNull
    private byte[] signature;


    public Parcel(long organisationId, long userId, String reciever, String address, String sentFrom, Date expectedDeliveryDate, int status, long formatId, long serviceTypeId) {
        this.setOrganisationId(organisationId);
        this.setUserId(userId);
        this.setReciever(reciever);
        this.setAddress(address);
        this.setSentFrom(sentFrom);
        this.setExpectedDeliveryDate(expectedDeliveryDate);
        this.setStatus(status);
        this.setFormatId(formatId);
        this.setServiceTypeId(serviceTypeId);
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
}
