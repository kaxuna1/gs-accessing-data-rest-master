package main.models;

public class UserBuilder {
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String address;
    private long organisationId;
    private String mobile;
    private String personalNumber;
    private int type;
    private int regionId;
    private int zoneId;

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public UserBuilder setOrganisationId(long organisationId) {
        this.organisationId = organisationId;
        return this;
    }

    public UserBuilder setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public UserBuilder setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
        return this;
    }

    public UserBuilder setType(int type) {
        this.type = type;
        return this;
    }

    public UserBuilder setRegionId(int regionId) {
        this.regionId = regionId;
        return this;
    }

    public UserBuilder setZoneId(int zoneId) {
        this.zoneId = zoneId;
        return this;
    }

    public User createUser() {
        return new User(username, password, email, name, surname, address, organisationId, mobile, personalNumber, type, regionId, zoneId);
    }
}