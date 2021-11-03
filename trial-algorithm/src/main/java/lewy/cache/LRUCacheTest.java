package lewy.cache;

import java.util.Map;

/**
 * 测试类
 */
public class LRUCacheTest {

    //private static final Logger log = LoggerFactory.getLogger(LRUCacheTest.class);
    private static LRUCache<String, Integer> cache = new LRUCache<>(10);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            cache.put("k" + i, i);
        }
        System.out.println("all cache: ");
        printLHM(cache);

        System.out.println("get k3 :");
        cache.get("k3");
        printLHM(cache);

        cache.get("k4");
        System.out.println("get k4 :");
        printLHM(cache);

        cache.get("k4");
        System.out.println("get k4 :");
        printLHM(cache);

        cache.put("k" + 10, 10);
        System.out.println("After running the LRU algorithm cache : ");
        printLHM(cache);

//        log.info("all cache :'{}'",cache);
//        cache.get("k3");
//        log.info("get k3 :'{}'", cache);
//        cache.get("k4");
//        log.info("get k4 :'{}'", cache);
//        cache.get("k4");
//        log.info("get k4 :'{}'", cache);
//        cache.put("k" + 10, 10);
//        log.info("After running the LRU algorithm cache :'{}'", cache);
    }

    /**
     * 遍历
     */
    private static void printLHM(LRUCache<String, Integer> cache) {
        for(Map.Entry<String, Integer> entry : cache.entrySet()) {
            //System.out.println("key: " + entry.getKey() + "   value: " + entry.getValue());
            System.out.print(entry.getKey() + " ");
        }
        System.out.println();
    }
}
