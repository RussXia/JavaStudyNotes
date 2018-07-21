package com.xzy.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class LoadingCacheTemplate<K, V> implements LocalCachePolicy  {


    private LoadingCache<K, V> loadingCache;

    public LoadingCacheTemplate() {
        this.init();
    }


    public void init() {
        loadingCache = CacheBuilder.newBuilder()
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .maximumSize(maximumSize())
                .expireAfterAccess(expire(), TimeUnit.MILLISECONDS)
                .build(new CacheLoader<K, V>() {
                    @Override
                    public V load(K key) throws Exception {
                        V v = loadKey(key);
                        if (v == null) {
                            loadingCache.invalidate(key);
                        }
                        return v;
                    }
                });
        log.info("LoadingCache init!");
    }


    protected abstract V loadKey(K k);

    public V get(K k) {
        try {
            return loadingCache.get(k);
        } catch (ExecutionException e) {
            log.error("get V from cache failed, K - {} , e- {}", k, e);
            return null;
        }
    }

}
