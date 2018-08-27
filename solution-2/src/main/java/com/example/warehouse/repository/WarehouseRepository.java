package com.example.warehouse.repository;

import com.example.warehouse.entity.WarehouseEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WarehouseRepository extends CrudRepository<WarehouseEntity, String> {

    boolean existsBySerialNumber(String serialNumber);

    @Query("SELECT COUNT(u) FROM WarehouseEntity u")
    int countAll();

    int countAllByAssetType(String assetType);

    List<WarehouseEntity> findByWarrantyExpirationDateBefore(Date date);

}
