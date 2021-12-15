package cn.xzxy.lewy.dispatch;

public class StaticDispatchTest2 {

   private static void sayHello(char arg) {
     System.out.println("hello char");
   }

  private static void sayHello(Object arg) {
    System.out.println("hello Object");
  }

  private static void sayHello(int arg) {
    System.out.println("hello int");
  }

  private static void sayHello(long arg) {
    System.out.println("hello long");
  }

  public static void main(String[] args) {
    sayHello('a');
  }

}

// 执行结果：'a' 首先表示的是char，其次表示的是数字97
// 所以：
// sayHello(char arg) 存在，输出 hello char
// sayHello(char arg) 不存在，输出 hello int
// sayHello(int arg) 也不存在，输出 hello long
// sayHello(long arg) 也不存在，输出 hello Object

// 总结：char>int>long>float>double>Character>Serializable>Object>...


