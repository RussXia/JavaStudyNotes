package com.xzy.concurrent2;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicIntegerArray;

import static java.lang.System.out;

/**
 * Created by RuzzZZ on 18/07/2017.
 */
public class AtomicIntegerArrayTest {

    @Test
    public void testAtomicIntegerArray() {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(20);
        atomicIntegerArray.set(0,10);
        Assert.assertEquals(atomicIntegerArray.get(0),10);
    }
}
