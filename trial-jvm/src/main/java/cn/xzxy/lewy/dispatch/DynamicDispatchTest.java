package cn.xzxy.lewy.dispatch;

public class DynamicDispatchTest {

  static class Human {
    public void sayHello() {
      System.out.println("Human say hello");

    }
  }

  static class Man extends Human {
    @Override
    public void sayHello() {
      System.out.println("man say hello");
    }
  }

  static class Woman extends Human {
    @Override
    public void sayHello() {
      System.out.println("woman say hello");
    }
  }

  public static void main(String[] args) {

    Human man = new Man();
    man.sayHello();

    man = new Woman();
    man.sayHello();
  }

}

// 运行结果：
// man say hello
// woman say hello

// 原因解析：
// 1. 方法重写（Override） = 动态分派 = 根据 变量的动态类型 确定执行（重写）哪个方法
// 2. 对于情况1：根据变量（Man）的动态类型（man）确定调用man中的重写方法 sayHello()
// 3. 对于情况2：根据变量（Man）的动态类型（woman）确定调用woman中的重写方法 sayHello()






