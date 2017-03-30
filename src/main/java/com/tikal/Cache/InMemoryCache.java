package com.tikal.cache;

/**
 * Created by pavel_sopher on 27/03/2017.
 */
public interface InMemoryCache<K,T> {

    public void put (K key, T value);
    public T get(K key);
    public void remove(K key);
    public long size();
    public void cleanup();
}
