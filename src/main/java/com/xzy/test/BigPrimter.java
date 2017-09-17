package com.xzy.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by RuzzZZ on 17/08/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BigPrimter {

    private int field;


    public static BigPrimter fieldBigPrimer(int i) {
        return new BigPrimter(i);
    }

}
