package cn.xzxy.lewy.f_t_s;

import org.springframework.stereotype.Component;

@Component
public class LewyHandler extends AbstractPlayerHandler {

    @Override
    public void goal(String name) {
        // lewy 业务
        System.out.println(name + " goals");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 调用工厂方法，将策略类注册到全局Map中
        PlayerFactory.register("lewy",this);
    }
}
