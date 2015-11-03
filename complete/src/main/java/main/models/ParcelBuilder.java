package main.models;

import java.util.Date;

public class ParcelBuilder {
    private Organisation organisation;
    private long userId;
    private String reciever;
    private String address;
    private String sentFrom;
    private Date expectedDeliveryDate;
    private int status;
    private Format format;
    private long serviceTypeId;
    private String barCode;
    private Region region;
    private Zone zone;

    public ParcelBuilder setOrganisation(Organisation organisation) {
        this.organisation = organisation;
        return this;
    }

    public ParcelBuilder setUserId(long userId) {
        this.userId = userId;
        return this;
    }
    public ParcelBuilder setRegion(Region region) {
        this.region = region;
        return this;
    }

    public ParcelBuilder setReciever(String reciever) {
        this.reciever = reciever;
        return this;
    }

    public ParcelBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public ParcelBuilder setSentFrom(String sentFrom) {
        this.sentFrom = sentFrom;
        return this;
    }

    public ParcelBuilder setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
        return this;
    }

    public ParcelBuilder setStatus(int status) {
        this.status = status;
        return this;
    }

    public ParcelBuilder setFormat(Format format) {
        this.format = format;
        return this;
    }

    public ParcelBuilder setServiceTypeId(long serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
        return this;
    }
    public ParcelBuilder setBarCode(String barCode){
        this.barCode=barCode;
        return this;
    }
    public ParcelBuilder setZone(Zone zone){
        this.zone=zone;
        return this;
    }

    public Parcel createParcel() {
        return new Parcel(organisation, userId, reciever, address, sentFrom, expectedDeliveryDate, status, format, serviceTypeId,barCode, region, zone);
    }
}