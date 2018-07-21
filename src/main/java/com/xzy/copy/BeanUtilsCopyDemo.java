package com.xzy.copy;

import com.alibaba.fastjson.JSONObject;
import com.esotericsoftware.reflectasm.ConstructorAccess;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class BeanUtilsCopyDemo {

    public static final Integer LOOP_TIME = 500000;

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        {
            CopyBeanDO copyBeanDO = new CopyBeanDO(100, true, 3.21f, 3.21, 100L, 'c', (byte) 3, (short) 3, Integer.MIN_VALUE, Boolean.TRUE,
                    Float.MAX_VALUE, Double.MAX_VALUE, Long.MAX_VALUE, Short.MAX_VALUE, Byte.MAX_VALUE, BigInteger.ONE, BigDecimal.ONE, "test");
            CopyBeanDTO copyBeanDTO = new CopyBeanDTO();
            Long startTime = System.currentTimeMillis();
            for (int i = 0; i < LOOP_TIME; i++) {
                BeanUtils.copyProperties(copyBeanDO, copyBeanDTO);
            }
            Long end = System.currentTimeMillis();
            System.out.println("Spring.BeanUtils spend time : " + (end - startTime));
            System.out.println("CarSnapInfoDTO : " + JSONObject.toJSONString(copyBeanDTO));
        }
        {
            CopyBeanDO copyBeanDO = new CopyBeanDO(100, true, 3.21f, 3.21, 100L, 'c', (byte) 3, (short) 3, Integer.MIN_VALUE, Boolean.TRUE,
                    Float.MAX_VALUE, Double.MAX_VALUE, Long.MAX_VALUE, Short.MAX_VALUE, Byte.MAX_VALUE, BigInteger.ONE, BigDecimal.ONE, "test");
            CopyBeanDTO copyBeanDTO = new CopyBeanDTO();
            Long startTime = System.currentTimeMillis();
            for (int i = 0; i < LOOP_TIME; i++) {
                org.apache.commons.beanutils.BeanUtils.copyProperties(copyBeanDO, copyBeanDTO);
            }
            Long end = System.currentTimeMillis();
            System.out.println("Apache.BeanUtils spend time : " + (end - startTime));
            System.out.println("CarSnapInfoDTO : " + JSONObject.toJSONString(copyBeanDTO));
        }

        {
            CopyBeanDO copyBeanDO = new CopyBeanDO(100, true, 3.21f, 3.21, 100L, 'c', (byte) 3, (short) 3, Integer.MIN_VALUE, Boolean.TRUE,
                    Float.MAX_VALUE, Double.MAX_VALUE, Long.MAX_VALUE, Short.MAX_VALUE, Byte.MAX_VALUE, BigInteger.ONE, BigDecimal.ONE, "test");
            CopyBeanDTO copyBeanDTO = new CopyBeanDTO();
            Long startTime = System.currentTimeMillis();
            for (int i = 0; i < LOOP_TIME; i++) {
                PropertyUtils.copyProperties(copyBeanDO, copyBeanDTO);
            }
            Long end = System.currentTimeMillis();
            System.out.println("Apache.PropertyUtils spend time : " + (end - startTime));
            System.out.println("CarSnapInfoDTO : " + JSONObject.toJSONString(copyBeanDTO));
        }


        {
            CopyBeanDO copyBeanDO = new CopyBeanDO(100, true, 3.21f, 3.21, 100L, 'c', (byte) 3, (short) 3, Integer.MIN_VALUE, Boolean.TRUE,
                    Float.MAX_VALUE, Double.MAX_VALUE, Long.MAX_VALUE, Short.MAX_VALUE, Byte.MAX_VALUE, BigInteger.ONE, BigDecimal.ONE, "test");
            CopyBeanDTO copyBeanDTO = new CopyBeanDTO();
            Long startTime = System.currentTimeMillis();
            for (int i = 0; i < LOOP_TIME; i++) {
                copyBean(copyBeanDO, copyBeanDTO);
            }
            Long end = System.currentTimeMillis();
            System.out.println("direct setter spend time : " + (end - startTime));
            System.out.println("CarSnapInfoDTO : " + JSONObject.toJSONString(copyBeanDTO));
        }


        {
            CopyBeanDO copyBeanDO = new CopyBeanDO(100, true, 3.21f, 3.21, 100L, 'c', (byte) 3, (short) 3, Integer.MIN_VALUE, Boolean.TRUE,
                    Float.MAX_VALUE, Double.MAX_VALUE, Long.MAX_VALUE, Short.MAX_VALUE, Byte.MAX_VALUE, BigInteger.ONE, BigDecimal.ONE, "test");
            ConstructorAccess<CopyBeanDTO> constructorAccess = ConstructorAccess.get(CopyBeanDTO.class);
            CopyBeanDTO copyBeanDTO = constructorAccess.newInstance();
            BeanCopier copier = BeanCopier.create(CopyBeanDO.class, CopyBeanDTO.class, false);
            Long startTime = System.currentTimeMillis();
            for (int i = 0; i < LOOP_TIME; i++) {
                copier.copy(copyBeanDO, copyBeanDTO, null);
            }
            Long end = System.currentTimeMillis();
            System.out.println("BeanCopier : " + (end - startTime));
            System.out.println("CarSnapInfoDTO : " + JSONObject.toJSONString(copyBeanDTO));
        }

    }

    private static void copyBean(CopyBeanDO copyBeanDO, CopyBeanDTO copyBeanDTO) {
        copyBeanDTO.setIntValue(copyBeanDO.getIntValue());
        copyBeanDTO.setBoolValue(copyBeanDO.getBoolObjValue());
        copyBeanDTO.setFloatValue(copyBeanDO.getFloatValue());
        copyBeanDTO.setDoubleValue(copyBeanDO.getDoubleValue());
        copyBeanDTO.setLongValue(copyBeanDO.getLongValue());
        copyBeanDTO.setCharValue(copyBeanDO.getCharValue());
        copyBeanDTO.setByteValue(copyBeanDO.getByteValue());
        copyBeanDTO.setShortValue(copyBeanDO.getShortValue());
        copyBeanDTO.setIntegerValue(copyBeanDO.getIntegerValue());
        copyBeanDTO.setBoolObjValue(copyBeanDO.getBoolObjValue());
        copyBeanDTO.setFloatObjValue(copyBeanDO.getFloatObjValue());
        copyBeanDTO.setDoubleObjValue(copyBeanDO.getDoubleObjValue());
        copyBeanDTO.setLongObjValue(copyBeanDO.getLongObjValue());
        copyBeanDTO.setShortObjValue(copyBeanDO.getShortObjValue());
        copyBeanDTO.setByteObjValue(copyBeanDO.getByteObjValue());
        copyBeanDTO.setBigIntegerValue(copyBeanDO.getBigIntegerValue());
        copyBeanDTO.setBigDecimalValue(copyBeanDO.getBigDecimalValue());
        copyBeanDTO.setStringValue(copyBeanDO.getStringValue());
    }
}
