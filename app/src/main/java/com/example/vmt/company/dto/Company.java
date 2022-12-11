package com.example.vmt.company.dto;

import org.json.JSONException;
import org.json.JSONObject;

public class Company {

    private String logoPath;
    private String name;
    private String address;
    private String phone;
    private String site;

    public Company(String logoPath, String name, String address, String phone, String site) {
        // Used for adding new companies
        this.logoPath = logoPath;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.site = site;
    }

    public Company(JSONObject companyJsonObject) throws JSONException {
        // Used for loading existing companies
        this.logoPath = companyJsonObject.getString("logoPath");
        this.name = companyJsonObject.getString("name");
        this.address = companyJsonObject.getString("address");
        this.phone = companyJsonObject.getString("phone");
        this.site = companyJsonObject.getString("site");
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

    public JSONObject toJsonObject() throws JSONException {
        JSONObject companyJsonObject = new JSONObject();
        companyJsonObject.put("logoPath", logoPath);
        companyJsonObject.put("name", name);
        companyJsonObject.put("address", address);
        companyJsonObject.put("phone", phone);
        companyJsonObject.put("site", site);
        return companyJsonObject;
    }
}
