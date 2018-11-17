package com.xzy.jvmlearning.clazz.loading.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * 3中类加载器:
 * 1.Bootstrap ClassLoader(启动类加载器)
 * 2.Extension ClassLoader(扩展类加载器)
 * 3.Application ClassLoader(应用程序类加载器)
 * @author RuzzZZ
 * @since 15/01/2018 2:04 PM
 */
public class ClassLoaderTest {

    public static final int FLAG = 1024;

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader myLoad = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }

            }
        };
        //这个obj是通过自定义的类加载器加载并创建的实例
        Object obj = myLoad.loadClass("com.xzy.jvmlearning.clazz.loading.classloader.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        //ClassLoaderTest是"系统应用程序类加载器"加载，所以返回false
        System.out.println(obj instanceof ClassLoaderTest);
        //虽然加载的是同一个class文件，但是由于是两个类加载器，所以依然是两个独立的类
        System.out.println(obj.getClass().getClassLoader());
        System.out.println(ClassLoaderTest.class.getClassLoader());
        //由于"系统应用程序类加载器(Application ClassLoader)"是CloassLoader的系统默认的加载器，所以"系统应用程序类加载器"又被叫做"系统加载器"
        System.out.println(ClassLoader.getSystemClassLoader());

        System.out.println("******************分割线1**********************");

        //双亲委派模型:除顶层的启动类加载器外，其余的类加载器都应该有自己的父类加载器
        System.out.println(obj.getClass().getClassLoader().getParent());
        System.out.println(ClassLoaderTest.class.getClassLoader().getParent());

        System.out.println("******************分割线2**********************");

        System.out.println(obj.getClass().getClassLoader().getParent().getParent());
        System.out.println(ClassLoaderTest.class.getClassLoader().getParent().getParent());


        ClassLoader classLoader = new ClassLoader() {
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                return super.findClass(name);
            }
        };
        classLoader.loadClass("com.xzy.jvmlearning.clazz.loading.classloader.ClassLoaderTest").newInstance();
    }
}