package com.example.vmt.company.dto;

public class Company {

    private String logoPath;
    private String name;
    private String address;
    private String phone;
    private String site;

    public Company(String logoPath, String name, String address, String phone, String site) {
        this.logoPath = logoPath;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.site = site;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getSite() {
        return site;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSite(String site) {
        this.site = site;
    }

}
