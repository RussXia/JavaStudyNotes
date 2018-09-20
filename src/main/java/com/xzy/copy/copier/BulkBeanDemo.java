package com.xzy.copy.copier;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cglib.beans.BulkBean;

import java.math.BigDecimal;

public class BulkBeanDemo {

    public static void main(String[] args) {

        LoanApplyEntity entity = new LoanApplyEntity("ttt32321", new BigDecimal("323.33"), new BigDecimal("323.31111"), "testst");

        final String[] getters = new String[]{"getOrderNo", "getPayAmount", "getReduceAmount"};
        final String[] setters = new String[]{"setOrderNo", "setPayAmount", "setReduceAmount"};
        final Class[] classes = new Class[]{String.class, BigDecimal.class, BigDecimal.class};

        BulkBean bulkBean = BulkBean.create(LoanApplyEntity.class, getters, setters, classes);

        Object[] result = bulkBean.getPropertyValues(entity);
        for (Object object : result) {
            System.out.println(object);
        }

        final String[] getters2 = new String[]{"getOrderNo", "getPayAmount", "getReduceAmount"};
        final String[] setters2 = new String[]{"setOrderNo", "setPayAmount", "setReduceAmount"};
        final Class[] classes2 = new Class[]{String.class, BigDecimal.class, String.class};
        BulkBean bulkBean2 = BulkBean.create(LoanApplyDTO.class, getters2, setters2, classes2);
        LoanApplyDTO dto = new LoanApplyDTO();
        bulkBean2.setPropertyValues(dto, new Object[]{"ddd", new BigDecimal("323.33323"), "323.3311", "43fdsf3ds"});
        System.out.println(JSONObject.toJSONString(dto));
    }
}
