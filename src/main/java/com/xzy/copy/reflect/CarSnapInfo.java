package com.xzy.copy.reflect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * Created by RuzzZZ on 2017/2/17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarSnapInfo implements Serializable {
    private static final long serialVersionUID = -6331372047336357656L;
    private Long carId;
    private String modelName;
    private String name;
}
