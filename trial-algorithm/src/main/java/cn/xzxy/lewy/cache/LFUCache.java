package cn.xzxy.lewy.cache;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * LFU的java实现
 */
public class LFUCache<K, V> extends HashMap<K, V> {

    private static final int DEFAULT_MAX_SIZE = 3;
    private int maxSize = DEFAULT_MAX_SIZE;
    private Map<K, HitRate> km = new HashMap<K, HitRate>();

    public LFUCache() {
        this(DEFAULT_MAX_SIZE);
    }

    private LFUCache(int maxSize) {
        super(maxSize);
        this.maxSize = maxSize;
    }

    @Override
    public V get(Object key) {
        V v = super.get(key);
        if (v != null) {
            HitRate hitRate = km.get(key);
            hitRate.hitCount += 1;
            hitRate.atime = System.nanoTime();
        }
        return v;
    }

    @Override
    public V put(K key, V value) {
        while (km.size() >= maxSize) {
            K k = getLFUAging();
            km.remove(k);
            this.remove(k);
        }
        V v = super.put(key, value);
        km.put(key, new HitRate(key, 1, System.nanoTime()));
        return v;
    }

    private K getLFUAging() {
        HitRate min = Collections.min(km.values());
        return min.key;
    }

    class HitRate implements Comparable<HitRate> {
        K key;
        Integer hitCount; // 命中次数
        Long atime; // 上次命中时间

        public HitRate(K key, Integer hitCount, Long atime) {
            this.key = key;
            this.hitCount = hitCount;
            this.atime = atime;
        }

        public int compareTo(HitRate o) {
            int hr = hitCount.compareTo(o.hitCount);
            return hr != 0 ? hr : atime.compareTo(o.atime);
        }
    }

}
