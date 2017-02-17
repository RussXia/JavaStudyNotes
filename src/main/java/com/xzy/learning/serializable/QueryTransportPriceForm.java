package com.xzy.learning.serializable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by RuzzZZ on 2017/2/17.
 */
public class QueryTransportPriceForm implements Serializable {
    private static final long serialVersionUID = -4642440788683825919L;
    private Integer pageSize = Integer.valueOf(20);
    private Integer pageNo = Integer.valueOf(1);
    private String userId;
    private Long startLocationId;
    private String startLocationName;
    private Long arriveLocationId;
    private String arriveLocationName;
    private Integer transportType;
    private Integer queryOuterCarrier;
    private List<CarSnapInfo> carInfoList = new ArrayList();

    public QueryTransportPriceForm(Integer pageSize, Integer pageNo, String userId, Long startLocationId, String startLocationName, Long arriveLocationId, String arriveLocationName, Integer transportType, Integer queryOuterCarrier, List<CarSnapInfo> carInfoList) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.userId = userId;
        this.startLocationId = startLocationId;
        this.startLocationName = startLocationName;
        this.arriveLocationId = arriveLocationId;
        this.arriveLocationName = arriveLocationName;
        this.transportType = transportType;
        this.queryOuterCarrier = queryOuterCarrier;
        this.carInfoList = carInfoList;
    }

    public QueryTransportPriceForm() {
    }

    public Integer getQueryOuterCarrier() {
        return this.queryOuterCarrier;
    }

    public void setQueryOuterCarrier(Integer queryOuterCarrier) {
        this.queryOuterCarrier = queryOuterCarrier;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getStartLocationId() {
        return this.startLocationId;
    }

    public void setStartLocationId(Long startLocationId) {
        this.startLocationId = startLocationId;
    }

    public String getStartLocationName() {
        return this.startLocationName;
    }

    public void setStartLocationName(String startLocationName) {
        this.startLocationName = startLocationName;
    }

    public Long getArriveLocationId() {
        return this.arriveLocationId;
    }

    public void setArriveLocationId(Long arriveLocationId) {
        this.arriveLocationId = arriveLocationId;
    }

    public String getArriveLocationName() {
        return this.arriveLocationName;
    }

    public void setArriveLocationName(String arriveLocationName) {
        this.arriveLocationName = arriveLocationName;
    }

    public Integer getTransportType() {
        return this.transportType;
    }

    public void setTransportType(Integer transportType) {
        this.transportType = transportType;
    }

    public List<CarSnapInfo> getCarInfoList() {
        return this.carInfoList;
    }

    public void setCarInfoList(List<CarSnapInfo> carInfoList) {
        this.carInfoList = carInfoList;
    }

    @Override
    public String toString() {
        return "QueryTransportPriceForm{" +
                "pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", userId='" + userId + '\'' +
                ", startLocationId=" + startLocationId +
                ", startLocationName='" + startLocationName + '\'' +
                ", arriveLocationId=" + arriveLocationId +
                ", arriveLocationName='" + arriveLocationName + '\'' +
                ", transportType=" + transportType +
                ", queryOuterCarrier=" + queryOuterCarrier +
                ", carInfoList=" + carInfoList +
                '}';
    }
}
