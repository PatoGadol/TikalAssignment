package com.tikal.cache;

import com.fasterxml.jackson.databind.util.LRUMap;

/**
 * Created by pavel_sopher on 27/03/2017.
 */
public class AccountsCacheImpl<K,T> implements InMemoryCache<K,T> {

    private long timeToLive;
    private LRUMap cacheMap;

    protected class AccountCacheObject {
        public long lastAccessed = System.currentTimeMillis();
        public T value;

        protected AccountCacheObject(T value) {
            this.value = value;
        }
    }

    public AccountsCacheImpl(long accountTimeToLive, final long accountTimerInterval, int maxItems) {
        this.timeToLive = accountTimeToLive * 1000;

        cacheMap = new LRUMap(0, maxItems);

        if (timeToLive > 0 && accountTimerInterval > 0) {

            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(accountTimerInterval * 1000);
                        } catch (InterruptedException ex) {
                        }
                        cleanup();
                    }
                }
            });

            t.setDaemon(true);
            t.start();
        }
    }


    @Override
    public void put(K key, T value) {

    }

    @Override
    public T get(K key) {
        return null;
    }

    @Override
    public void remove(K key) {

    }

    @Override
    public long size() {
        return 0;
    }

    @Override
    public void cleanup() {

    }
}
