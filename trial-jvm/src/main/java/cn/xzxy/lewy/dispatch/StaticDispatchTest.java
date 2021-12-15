package cn.xzxy.lewy.dispatch;

public class StaticDispatchTest {

  // 类定义
  static abstract class Human {
  }

  // 继承自抽象类Human
  static class Man extends Human {
  }

  static class Woman extends Human {
  }

  // 可供重载的方法
  public void sayHello(Human guy) {
    System.out.println("hello guy!");
  }

  public void sayHello(Man guy) {
    System.out.println("hello gentleman!");
  }

  public void sayHello(Woman guy) {
    System.out.println("hello lady!");
  }

  public static void main(String[] args) {
    // 首先明确的两点：
    // 变量man的静态类型 = 引用类型 = Human：不会被改变、在编译器可知
    // 变量man的动态类型 = 实例对象类型 = Man：会变化、在运行期才可知
    Human man = new Man();
    Human woman = new Woman();
    StaticDispatchTest test = new StaticDispatchTest();

    test.sayHello(man);
    test.sayHello(woman);

    // 注意点：当变量的静态类型发生变化时
    test.sayHello((Man)man); // 强转类型，man的静态类型从Human变为了Man，此时调用sayHello(Man guy)
  }
}

// 运行结果
// hello guy!
// hello guy!
// hello gentleman!
