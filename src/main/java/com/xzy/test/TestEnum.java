package com.xzy.test;

public enum TestEnum {

    NOT_DELETED(new Integer("0"), "未删除"),
    DELETED(new Integer("-1"), "已删除");

    private Integer key;

    private String value;

    TestEnum(Integer key, String value) {
        setKey(key);
        setValue(value);
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        TestEnum.NOT_DELETED.setValue("test1");
        TestEnum.NOT_DELETED.setValue("test2");
        TestEnum.NOT_DELETED.setValue("test3");
        System.out.println(TestEnum.NOT_DELETED.getValue());
    }
}
