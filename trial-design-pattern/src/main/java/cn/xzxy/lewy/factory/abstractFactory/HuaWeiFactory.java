package cn.xzxy.lewy.factory.abstractFactory;

public class HuaWeiFactory implements PhoneFactory {

    @Override
    public SmartPhone createPhone() {
        return new HuaWeiPhone();
    }
}
