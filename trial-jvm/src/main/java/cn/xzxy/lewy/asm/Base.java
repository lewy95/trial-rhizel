package cn.xzxy.lewy.asm;

/**
 * 定义需要被增强的Base类：其中只包含一个process()方法，方法内输出一行“process”。
 * 增强后，期望实现aop的效果，方法执行前输出“start”，之后输出"end"。
 */
public class Base {
  public void process() {
    System.out.println("process");
  }
}
