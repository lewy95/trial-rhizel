package cn.xzxy.lewy.cor.basic;

/**
 * 责任处理对象
 */
public abstract class CorHandler {

    /**
     * 持有后继的责任对象
     */
    protected CorHandler successor;

    /**
     * 模拟处理请求的方法
     */
    public abstract void handleRequest();

    /**
     * 获取后继的责任对象
     */
    public CorHandler getSuccessor() {
        return successor;
    }

    /**
     * 设置后继的责任对象
     */
    public void setSuccessor(CorHandler successor) {
        this.successor = successor;
    }

}
