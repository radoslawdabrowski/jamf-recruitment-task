package com.example.warehouse;

import com.example.warehouse.entity.Asset;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Raw Java example
 */
public final class WarehouseApi {

    private HashSet<Asset> assets = new HashSet<>();

    public void addAsset(String serialNumber, String assetType, String warrantyExpirationDate) {
        if(!isAssetInWarehouse(serialNumber))
            assets.add(new Asset(serialNumber, assetType, warrantyExpirationDate));
    }

    public void removeAsset(String serialNumber) {
        assets.removeIf(asset -> asset.getSerialNumber().equals(serialNumber));
    }

    public boolean isAssetInWarehouse(String serialNumber) {
        return assets.stream().anyMatch(asset -> asset.getSerialNumber().equals(serialNumber));
    }

    public int countAllAssets() {
        return assets.size();
    }

    public int countByAssetType(String assetType) {
        return (int) assets.stream().filter(asset -> asset.getAssetType().equals(assetType)).count();
    }

    public List<String> findExpiredWarrantyAssetsSerialNumbers() {
        return assets.stream().filter(asset -> asset.getWarrantyExpirationDate().before(new Date())).map(Asset::getSerialNumber).collect(Collectors.toList());
    }
}
