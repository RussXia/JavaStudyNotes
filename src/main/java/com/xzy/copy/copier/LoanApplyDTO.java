package com.xzy.copy.copier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanApplyDTO {

    private String orderNo;

    private BigDecimal payAmount;

    private String reduceAmount;

    private String remark;

}
