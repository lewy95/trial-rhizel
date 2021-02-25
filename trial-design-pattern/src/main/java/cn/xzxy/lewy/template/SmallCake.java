package cn.xzxy.lewy.template;

public class SmallCake extends AbstractCake {
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
        System.out.println("小蛋糕造型");
    }
    @Override
    protected void apply() {
        System.out.println("小蛋糕涂抹");
    }
    @Override
    protected void brake() {
        System.out.println("小蛋糕烘焙");
    }

    public static void main(String[] args) {
        AbstractCake cake = new SmallCake();
        cake.run();
    }

}