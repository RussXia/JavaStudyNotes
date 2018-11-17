package com.xzy.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class CLHLock implements Lock {

    private AtomicReference<QNode> tail = new AtomicReference<>(new QNode());

    private ThreadLocal<QNode> myPred;
    private ThreadLocal<QNode> myNode;

    public CLHLock() {
        tail = new AtomicReference<>(new QNode());
        myNode = ThreadLocal.withInitial(QNode::new);
        myPred = ThreadLocal.withInitial(() -> null);
    }

    @Override
    public void lock() {
        QNode qNode = myNode.get();
        qNode.lock = true;
        QNode pred = tail.getAndSet(qNode);
        myPred.set(pred);
        while (pred.lock) ;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        QNode qnode = myNode.get();
        qnode.lock = false;
        myNode.set(myPred.get());
    }

    @Override
    public Condition newCondition() {
        return null;
    }


    class QNode {

        boolean lock;
    }
}
