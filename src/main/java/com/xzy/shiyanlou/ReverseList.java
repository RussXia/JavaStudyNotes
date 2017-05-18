package com.xzy.shiyanlou;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Collections;
import java.util.ListIterator;

/**
 * Created by RuzzZZ on 2017/5/17.
 */
public class ReverseList<T> extends ArrayList<T> {


    public ReverseList(Collection<T> c) {
        super(c);
    }

    public Iterable<T> reversed() {
        return new Iterable<T>() {

            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {

                    private ListIterator<T> listIterator = ReverseList.this.listIterator();
                    {
                        for(;listIterator.hasNext();){
                            listIterator.next();
                        }
                        Collections.reverse(ReverseList.this);
                    }

                    @Override
                    public boolean hasNext() {
                        return listIterator.hasPrevious();
                    }

                    @Override
                    public T next() {
                        return listIterator.previous();
                    }
                };
            }
        };
    }
}
