package com.xzy.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TestDate {

    private Date timeNow;

    public Date getTimeNow() {
        if (timeNow != null) {
            return new Date(timeNow.getTime());
        }
        return null;
    }

    public void setDate(Date timeNow) {
        this.timeNow = timeNow;
    }

    public static void main(String[] args) {
        TestDate testDate = new TestDate(new Date());
        Date date = testDate.getTimeNow();
        System.out.println(date);
        date.setYear(117);
        System.out.println(date);
        System.out.println(testDate.getTimeNow());
        String str1 = "Test12333";
        String str2 = str1;
        str1 = "dsads";
        System.out.println(str1);
        System.out.println(str2);
    }
}
