package com.xzy.serializable;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * Created by RuzzZZ on 2017/2/17.
 */
@Getter
@Setter
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
    public String modelName;
    private Long innerId;
    private String innerName;
    private Long outerId;
    private String outerName;
    private Integer number;
    private String guidePrice;
    private String marketPrice;
    private String emission;
    private Integer attach;

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
