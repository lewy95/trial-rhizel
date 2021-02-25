package cn.xzxy.lewy.template;

public class CheeseCake extends AbstractCake {
    private boolean flag = false;
    public void setFlag(boolean shouldApply){
        flag = shouldApply;
    }
    @Override
    protected boolean shouldApply() {
        return this.flag;
    }
    @Override
    protected void shape() {
        System.out.println("芝士蛋糕造型");
    }
    @Override
    protected void apply() {
        System.out.println("芝士蛋糕涂抹");
    }
    @Override
    protected void brake() {
        System.out.println("芝士蛋糕烘焙");
    }

    public static void main(String[] args) {
        AbstractCake cake = new CheeseCake();
        cake.run();
    }

}