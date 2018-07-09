package com.xzy.serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


/**
 * Created by RuzzZZ on 2017/2/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryTransportPriceForm implements Serializable {
    private static final long serialVersionUID = 3072661494960809183L;
    private String userId;
    private Long startId;
    private String name;
    private Integer test;
    private List<CarSnapInfo> carInfoList;
}
