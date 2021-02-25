package cn.xzxy.lewy.factory.abstractFactory;

public class IphoneFactory implements PhoneFactory {

    @Override
    public SmartPhone createPhone() {
        return new IPhone();
    }
}
