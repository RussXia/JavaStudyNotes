package com.xzy.reflect;

import lombok.Data;

import java.io.Serializable;


/**
 * Created by RuzzZZ on 2017/2/17.
 */
@Data
public class CarSnapInfo implements Serializable {
    private static final long serialVersionUID = -6331372047336357656L;
    private Long carId;
    private String modelName;
    private String name;
}
