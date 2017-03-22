package com.xzy.guava;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by RuzzZZ on 2017/3/22.
 */
public class TypeTokenDemo {

    public static void main(String[] args) {
        ArrayList<String> stringList = Lists.newArrayList();
        ArrayList<Integer> intList = Lists.newArrayList();
        System.out.println(intList.getClass());
        System.out.println(stringList.getClass());
        //尽管指定了stringList的泛型是string，intList的泛型是Integer
        //但是两个的实际类型仍然是java.util.ArrayList，输出true
        //这个就是所谓的 "泛型擦除"
        System.out.println(stringList.getClass().isAssignableFrom(intList.getClass()));

        /**
         * Java的泛型在编译时期有效，在运行时期被删除。
         * 也就是说所有泛型的参数类型在编译后都会被清除掉。
         * 这也是Java的泛型设计的一个蛋疼之处。
         */

        /**
         * guava的{@link TypeToken是用来解决运行时java泛型被擦除问题}
         *  方法	                    描述
         *  getType()	            获得包装的java.lang.reflect.Type.
         *  getRawType()	        返回大家熟知的运行时类
         *  getSubtype(Class<?>)	返回那些有特定原始类的子类型。举个例子，如果这有一个Iterable并且参数是List.class，那么返回将是List。
         *  getSupertype(Class<?>)	产生这个类型的超类，这个超类是指定的原始类型。举个例子，如果这是一个Set并且参数是Iterable.class，结果将会是Iterable。
         *  isAssignableFrom(type)	如果这个类型是 assignable from 指定的类型，并且考虑泛型参数，返回true。List<? extends Number>是assignable from List，但List没有.
         *  getTypes()	            返回一个Set，包含了这个所有接口，子类和类是这个类型的类。返回的Set同样提供了classes()和interfaces()方法允许你只浏览超类和接口类。
         *  isArray()	            检查某个类型是不是数组，甚至是<? extends A[]>。
         *  getComponentType()	    返回组件类型数组。
         */
        TypeToken<ArrayList<String>> strListToken = new TypeToken<ArrayList<String>>() {
        };
        TypeToken<?> genericTypeToken = strListToken.resolveType(ArrayList.class.getTypeParameters()[0]);
        System.out.println(genericTypeToken.getType());
        System.out.println("type : " + strListToken.getType());
        System.out.println("raw : " + strListToken.getRawType());

        TypeToken<Integer> intToken = TypeToken.of(Integer.class);
        System.out.println(intToken.getType());
    }
}
