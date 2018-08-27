package com.example.warehouse.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Could use lombok project
 */
public class Asset {

    private String serialNumber;
    private String assetType;
    private Date warrantyExpirationDate;

    public Asset(String serialNumber, String assetType, String warrantyExpirationDate)
    {
        this.serialNumber = serialNumber;
        this.assetType = assetType;
        try{
            this.warrantyExpirationDate = (new SimpleDateFormat("dd/MM/yyyy")).parse(warrantyExpirationDate);
        }
        catch (ParseException ignore){}
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getAssetType() {
        return assetType;
    }

    public Date getWarrantyExpirationDate() {
        return warrantyExpirationDate;
    }
}
