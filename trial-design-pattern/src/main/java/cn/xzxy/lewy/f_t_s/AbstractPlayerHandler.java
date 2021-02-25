package cn.xzxy.lewy.f_t_s;

import org.springframework.beans.factory.InitializingBean;

/**
 * 模板方法设计模式
 */
public abstract class AbstractPlayerHandler implements InitializingBean {

    public void goal(String name) {
        // 抛出异常的目的：告诉调用者该子类不支持该方法
        throw new UnsupportedOperationException();
    }

    public String renew(String name) {
        throw new UnsupportedOperationException();
    }
}