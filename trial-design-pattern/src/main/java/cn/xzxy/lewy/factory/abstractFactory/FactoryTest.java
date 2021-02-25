package cn.xzxy.lewy.factory.abstractFactory;

public class FactoryTest {

    public static void main(String[] args) {
        PhoneFactory factory = new HuaWeiFactory();
        SmartPhone phone = factory.createPhone();
        phone.label();
    }
}
