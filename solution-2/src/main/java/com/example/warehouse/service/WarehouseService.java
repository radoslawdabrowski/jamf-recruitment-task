package com.example.warehouse.service;

import com.example.warehouse.entity.WarehouseEntity;
import com.example.warehouse.repository.WarehouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WarehouseService {

    private final WarehouseRepository repository;

    @Autowired
    public WarehouseService(WarehouseRepository repository) {
        this.repository = repository;
    }

    public void addAsset(String serialNumber, String assetType, String warrantyExpirationDate) {
        repository.save(new WarehouseEntity(serialNumber, assetType, parseDate(warrantyExpirationDate)));
    }

    public void removeAsset(String serialNumber){
        try {
            repository.deleteById(serialNumber);
        } catch(EmptyResultDataAccessException ex) {
            log.error(String.format("Object with id: %s not exists", serialNumber));
        }
    }

    public boolean isAssetInWarehouse(String serialNumber) {
        return repository.existsBySerialNumber(serialNumber);
    }

    public int countAllAssets() {
        return repository.countAll();
    }

    public int countByAssetType(String assetType) {
        return repository.countAllByAssetType(assetType);
    }

    public List<String> findExpiredWarrantyAssetsSerialNumbers() {
        return repository.findByWarrantyExpirationDateBefore(new Date()).stream().map(WarehouseEntity::getSerialNumber).collect(Collectors.toList());
    }

    private Date parseDate(String date) {
        try {
            return (new SimpleDateFormat("dd/MM/yyyy")).parse(date);
        } catch (ParseException ex) {
            return new Date();
        }
    }

}
