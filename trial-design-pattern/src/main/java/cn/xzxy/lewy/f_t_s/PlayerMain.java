package cn.xzxy.lewy.f_t_s;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PlayerMain {

    @Test
    public void testDesign() {
        String name1 = "lewy";

        AbstractPlayerHandler handler = PlayerFactory.getInvokeStrategy(name1);
        handler.goal(name1);

        String name2 = "muller";
        AbstractPlayerHandler handler2 = PlayerFactory.getInvokeStrategy(name2);
        handler.goal(name2);
        handler2.renew(name2);
    }

}
