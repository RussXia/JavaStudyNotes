package com.xzy.learning.serializable;

import java.io.Serializable;


/**
 * Created by RuzzZZ on 2017/2/17.
 */
public class CarSnapInfo implements Serializable {
    private static final long serialVersionUID = -7597039319847777313L;
    private Long carId;
    private Integer specId;
    private String specName;
    private Long brandId;
    private String brandName;
    private Long seriesId;
    private String seriesName;
    private Long modelId;
    private String modelName;
    private Long innerId;
    private String innerName;
    private Long outerId;
    private String outerName;
    private Integer number;
    private String guidePrice;
    private String marketPrice;
    private String emission;
    private Integer attach;

    public Long getCarId() {
        return this.carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getMarketPrice() {
        return this.marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getEmission() {
        return this.emission;
    }

    public void setEmission(String emission) {
        this.emission = emission;
    }

    public Integer getAttach() {
        return this.attach;
    }

    public void setAttach(Integer attach) {
        this.attach = attach;
    }

    public Integer getSpecId() {
        return this.specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public String getSpecName() {
        return this.specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Long getSeriesId() {
        return this.seriesId;
    }

    public void setSeriesId(Long seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesName() {
        return this.seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public Long getBrandId() {
        return this.brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Long getModelId() {
        return this.modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return this.modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getGuidePrice() {
        return this.guidePrice;
    }

    public void setGuidePrice(String guidePrice) {
        this.guidePrice = guidePrice;
    }

    public Long getInnerId() {
        return this.innerId;
    }

    public void setInnerId(Long innerId) {
        this.innerId = innerId;
    }

    public String getInnerName() {
        return this.innerName;
    }

    public void setInnerName(String innerName) {
        this.innerName = innerName;
    }

    public Long getOuterId() {
        return this.outerId;
    }

    public void setOuterId(Long outerId) {
        this.outerId = outerId;
    }

    public String getOuterName() {
        return this.outerName;
    }

    public void setOuterName(String outerName) {
        this.outerName = outerName;
    }

    public CarSnapInfo() {
    }

    public CarSnapInfo(Integer specId, String specName, Long brandId, String brandName, Long seriesId, String seriesName, Long modelId, String modelName, Long innerId, String innerName, Long outerId, String outerName, Integer number, String guidePrice, String marketPrice, String emission, Integer attach) {
        this.specId = specId;
        this.specName = specName;
        this.brandId = brandId;
        this.brandName = brandName;
        this.seriesId = seriesId;
        this.seriesName = seriesName;
        this.modelId = modelId;
        this.modelName = modelName;
        this.innerId = innerId;
        this.innerName = innerName;
        this.outerId = outerId;
        this.outerName = outerName;
        this.number = number;
        this.guidePrice = guidePrice;
        this.marketPrice = marketPrice;
        this.emission = emission;
        this.attach = attach;
    }

    @Override
    public String toString() {
        return "CarSnapInfo{" +
                "carId=" + carId +
                ", specId=" + specId +
                ", specName='" + specName + '\'' +
                ", brandId=" + brandId +
                ", brandName='" + brandName + '\'' +
                ", seriesId=" + seriesId +
                ", seriesName='" + seriesName + '\'' +
                ", modelId=" + modelId +
                ", modelName='" + modelName + '\'' +
                ", innerId=" + innerId +
                ", innerName='" + innerName + '\'' +
                ", outerId=" + outerId +
                ", outerName='" + outerName + '\'' +
                ", number=" + number +
                ", guidePrice='" + guidePrice + '\'' +
                ", marketPrice='" + marketPrice + '\'' +
                ", emission='" + emission + '\'' +
                ", attach=" + attach +
                '}';
    }
}
