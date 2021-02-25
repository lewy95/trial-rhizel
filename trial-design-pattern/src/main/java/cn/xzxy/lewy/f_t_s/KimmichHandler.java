package cn.xzxy.lewy.f_t_s;

import org.springframework.stereotype.Component;

@Component
public class KimmichHandler extends AbstractPlayerHandler {

    @Override
    public void goal(String name) {
        // kimmich 业务
        System.out.println(name + " goals");
    }

    @Override
    public String renew(String name) {
        System.out.println("name" + " 2028");
        return "OK";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 调用工厂方法，将策略类注册到全局Map中
        PlayerFactory.register("kimmich",this);
    }
}
