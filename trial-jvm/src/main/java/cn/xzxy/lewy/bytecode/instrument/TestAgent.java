package cn.xzxy.lewy.bytecode.instrument;

import java.lang.instrument.Instrumentation;

public class TestAgent {
  public static void agentmain(String args, Instrumentation inst) {
    // 指定自定义的 Transformer，在其中利用 Javassist 做字节码替换
    inst.addTransformer(new TestTransformer(), true);
    try {
      // 重定义类并载入新的字节码
      inst.retransformClasses(Base.class);
      System.out.println("Agent Load Done.");
    } catch (Exception e) {
      System.out.println("agent load failed!");
    }
  }
}
