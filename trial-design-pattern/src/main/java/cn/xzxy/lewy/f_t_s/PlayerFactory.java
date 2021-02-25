package cn.xzxy.lewy.f_t_s;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂设计模式
 */
public class PlayerFactory {
    // 定义一个全局Map，存放<key, 策略类>
    private static Map<String, AbstractPlayerHandler> strategyMap = new HashMap<>();

    public static AbstractPlayerHandler getInvokeStrategy(String name) {
        return strategyMap.get(name);
    }

    public static void register(String name, AbstractPlayerHandler handler) {
        if (StringUtils.isEmpty(name) || null == handler) {
            return;
        }
        strategyMap.put(name, handler);
    }
}
