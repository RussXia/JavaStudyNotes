package com.xzy.shiyanlou;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author RuzzZZ
 * @since 13/11/2017 10:55 AM
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BObject extends AObject {

    private Integer bInt;

    private String bStr;

}