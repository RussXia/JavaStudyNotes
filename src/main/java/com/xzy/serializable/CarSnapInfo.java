package com.xzy.serializable;

import lombok.Data;

import java.io.Serializable;


/**
 * Created by RuzzZZ on 2017/2/17.
 */
@Data
public class CarSnapInfo implements Serializable {
    private static final long serialVersionUID = -4341372047336357656L;
    private Long carId;
    private String modelName;
    private String name;
    private java.util.Date date1;
    private java.util.Date date2;
    private java.sql.Date date3;
}
