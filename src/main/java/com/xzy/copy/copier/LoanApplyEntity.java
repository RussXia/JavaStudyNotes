package com.xzy.copy.copier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanApplyEntity {

    private String orderNo;

    private BigDecimal payAmount;

    private BigDecimal reduceAmount;

    private String remark;
}
