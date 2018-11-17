package com.xzy.copy.copier;

import com.alibaba.fastjson.JSONObject;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

import java.math.BigDecimal;

public class BeanCopierDemo {

    public static void main(String[] args) {
        LoanApplyEntity entity = new LoanApplyEntity("32321", new BigDecimal("323.33"), new BigDecimal("323.31111"), "testst");
        LoanApplyDTO dto1 = new LoanApplyDTO();
        final BeanCopier copier1 = BeanCopier.create(LoanApplyEntity.class, LoanApplyDTO.class, true);
        copier1.copy(entity, dto1, new Converter() {
            @Override
            public Object convert(Object value, Class target, Object context) {
                if (value instanceof BigDecimal && target.isAssignableFrom(String.class)) {
                    return value.toString();
                } else {
                    return value;
                }
            }
        });
        System.out.println(JSONObject.toJSONString(dto1));

        LoanApplyDTO dto2 = new LoanApplyDTO();
        final BeanCopier copier2 = BeanCopier.create(LoanApplyEntity.class, LoanApplyDTO.class, false);
        copier2.copy(entity, dto2, null);
        System.out.println(JSONObject.toJSONString(dto2));
    }
}
