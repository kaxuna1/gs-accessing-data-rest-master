package main.models;

public class OrganisationBuilder {
    private String name;
    private String address;
    private String mobileNumber;
    private String email;
    private int regionId;

    public OrganisationBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public OrganisationBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public OrganisationBuilder setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public OrganisationBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public OrganisationBuilder setRegionId(int regionId) {
        this.regionId = regionId;
        return this;
    }

    public Organisation createOrganisation() {
        return new Organisation(name, address, mobileNumber, email, regionId);
    }
}