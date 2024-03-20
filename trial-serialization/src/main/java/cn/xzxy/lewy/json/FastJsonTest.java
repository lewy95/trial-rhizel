package cn.xzxy.lewy.json;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FastJsonTest {
    public static void main(String[] args) {
        Player player = new Player(1, "Lewy", 33);

        // 1. Java对象转Json字符串
        String json = JSONObject.toJSONString(player);
        System.out.println(json);
        System.out.println("Json序列化后的体积：" + json.getBytes().length);

        // 2. Json字符串转Java对象
        Player playerJson = JSONObject.parseObject(json, Player.class);
        System.out.println(playerJson);

        testList();

        testMap();

    }

    /**
     * 序列化 / 反序列化 List
     */
    private static void testList() {
        List<Player> zhuZis = Arrays.asList(
            new Player(5, "Hummers", 36),
            new Player(25, "Muller", 35));
        String json = JSONArray.toJSONString(zhuZis);
        System.out.println(json);
        List<Player> playersJson = JSONArray.parseArray(json, Player.class);
        System.out.println(playersJson);
    }

    /**
     * 序列化 / 反序列化 Map
     */
    private static void testMap() {
        Map<String, Player> playerMap = new HashMap<>();
        playerMap.put("1", new Player(5, "Hummers", 36));
        playerMap.put("2", new Player(25, "Muller", 35));
        String json = JSONObject.toJSONString(playerMap);
        System.out.println(json);
        HashMap<String, Player> playerMapJson = JSONObject.parseObject(
            json,
            new TypeReference<HashMap<String, Player>>() {});
        System.out.println(playerMapJson);
    }


}


