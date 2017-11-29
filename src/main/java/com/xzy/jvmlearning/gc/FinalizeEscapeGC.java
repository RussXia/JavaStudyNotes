package com.xzy.jvmlearning.gc;

/**
 * -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails
 *
 * @author RuzzZZ
 * @since 28/11/2017 4:37 PM
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }


    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();

        //在gc时，因为finalize方法，对象第一次挽救自己
        SAVE_HOOK = null;
        System.gc();
        //因为finalize的线程优先级比较低，所以阻塞主线程0.5s
        Thread.sleep(500L);

        if (SAVE_HOOK != null) {
            System.out.println("FinalizeEscapeGC SAVE_HOOK is alive");
        } else {
            System.out.println("FinalizeEscapeGC SAVE_HOOK is dead");
        }

        SAVE_HOOK = null;
        //第二次gc的时候，因为已经执行过finalize方法，所以不会再执行finalize方法
        System.gc();

        Thread.sleep(500L);
        if (SAVE_HOOK != null) {
            System.out.println("FinalizeEscapeGC SAVE_HOOK is alive");
        } else {
            System.out.println("FinalizeEscapeGC SAVE_HOOK is dead");
        }
    }
}