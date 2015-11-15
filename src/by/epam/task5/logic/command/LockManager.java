package by.epam.task5.logic.command;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockManager {
    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static final Lock lock = readWriteLock.readLock();

    public static Lock getLock() {
        return lock;
    }
}
