package cn.xzxy.lewy.factory.staticFactory;

public class StaticFactory {

    public static Product createProduct(String type) {
        if ("A".equals(type)) {
            return new ProductA();
        } else {
            return new ProductB();
        }
    }

    public static void main(String[] args) {
        Product product = StaticFactory.createProduct("B");
        product.label();
    }
}
