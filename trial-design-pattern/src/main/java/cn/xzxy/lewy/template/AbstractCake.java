package cn.xzxy.lewy.template;

public abstract class AbstractCake {
    protected boolean shouldApply() {
        return true;
    }

    protected abstract void shape();

    protected abstract void apply();

    protected abstract void brake();

    /**
     * 钩子程序，通过flag去判断是否要具体执行某个方法
     */
    public final void run() {
        this.shape();
        if (this.shouldApply()) {
            this.apply();
        }
        this.brake();
    }
}
