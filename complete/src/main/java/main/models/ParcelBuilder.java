package main.models;

import java.util.Date;

public class ParcelBuilder {
    private long organisationId;
    private long userId;
    private String reciever;
    private String address;
    private String sentFrom;
    private Date expectedDeliveryDate;
    private int status;
    private long formatId;
    private long serviceTypeId;
    private String barCode;
    private long regionId;

    public ParcelBuilder setOrganisationId(long organisationId) {
        this.organisationId = organisationId;
        return this;
    }

    public ParcelBuilder setUserId(long userId) {
        this.userId = userId;
        return this;
    }
    public ParcelBuilder setRegionId(long regionId) {
        this.regionId = regionId;
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

    public ParcelBuilder setFormatId(long formatId) {
        this.formatId = formatId;
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

    public Parcel createParcel() {
        return new Parcel(organisationId, userId, reciever, address, sentFrom, expectedDeliveryDate, status, formatId, serviceTypeId,barCode, regionId);
    }
}