package com.tikal.auth.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pavel_sopher on 27/03/2017.
 */

@Service
public class CacheImpl<K, T> implements InMemoryCache<K, T> {

    @Value("${cacheImple.accountTimeToLive}")
    Long accountTimeToLive;
    @Value("${cacheImple.accountTimerInterval}")
    Long accountTimerInterval;
    @Value("${cacheImple.maxItems}")
    int maxItems;


    private Long timeToLive;

    private ConcurrentHashMap<K, T> cacheMap = new ConcurrentHashMap<K,T>();

    @Qualifier("threadPoolTaskExecutorTest")
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutorTest;

    protected class CacheObject {
        public Long lastAccessed = System.currentTimeMillis();
        public T value;

        protected CacheObject(T value) {
            this.value = value;
        }
    }

    @PostConstruct
    public void initCacheImpl() {

        final int second = 1000;
        this.timeToLive = accountTimeToLive * second;

        if (timeToLive > 0 && accountTimerInterval > 0) {

            threadPoolTaskExecutorTest.execute(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(accountTimerInterval * second);
                        } catch (InterruptedException ex) {
                        }
                        cleanup();
                    }
                }
            });
        }
    }

    @Override
    public void put(K key, T value) {
        cacheMap.put(key, value);
    }

    @Override
    public T get(K key) {
        return (T) cacheMap.get(key);
    }

    @Override
    public void remove(K key) {
        cacheMap.put(key, null);
    }

    @Override
    public long size() {
        return cacheMap.size();
    }

    @Override
    public void cleanup() {
        cacheMap.clear();
    }

    @Override
    public boolean contains(K key) {
        return cacheMap.get(key) != null;
    }
}
