package cn.xzxy.lewy.cor.basic;

public class TestCor {

    public static void main(String[] args) {

        // 组装责任链
        CorHandler corHandler1 = new ConcreteCorHandler();
        CorHandler corHandler2 = new ConcreteCorHandler();
        // corHandler2 是 corHandler1 的后继对象
        corHandler1.setSuccessor(corHandler2);

        // 提交请求
        corHandler1.handleRequest();
    }

}
