package com.example.warehouse.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class WarehouseEntity {

    @Id
    private String serialNumber;
    private String assetType;
    private Date warrantyExpirationDate;

    public WarehouseEntity() {}

    public WarehouseEntity(String serialNumber, String assetType, Date warrantyExpirationDate) {
        this.serialNumber = serialNumber;
        this.assetType = assetType;
        this.warrantyExpirationDate = warrantyExpirationDate;
    }
}
