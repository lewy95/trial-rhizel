package cn.xzxy.lewy.cache;

/**
 * 测试类
 */
public class LFUCacheTest {

    public static void main(String[] args) {
        LFUCache<String, Integer> cache = new LFUCache<String, Integer>();
        cache.put("k1", 1);
        cache.put("k2", 2);
        cache.put("k3", 3);

        cache.get("k1");
        cache.get("k2");

        cache.put("k4", 4);
        cache.get("k4");
        cache.get("k3");
        cache.put("k5", 5);

        for (String key : cache.keySet()) {
            System.out.println(key);
        }
    }
}

