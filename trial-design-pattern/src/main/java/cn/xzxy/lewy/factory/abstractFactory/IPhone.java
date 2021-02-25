package cn.xzxy.lewy.factory.abstractFactory;

public class IPhone implements SmartPhone{
    @Override
    public void label() {
        System.out.println("this is iphone");
    }
}
