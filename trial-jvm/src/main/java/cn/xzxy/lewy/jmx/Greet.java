package cn.xzxy.lewy.jmx;

/**
 * 类名必须和接口 MBean 前的内容相同
 */
public class Greet implements GreetMBean {

    String name;

    @Override
    public void printHello() {
        System.out.println("hello，" + name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
