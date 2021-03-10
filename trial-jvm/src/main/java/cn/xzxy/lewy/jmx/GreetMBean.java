package cn.xzxy.lewy.jmx;

/**
 * 接口名字必须以 MBean 结尾
 */
public interface GreetMBean {
    String getName();

    void setName(String name);

    void printHello();
}
