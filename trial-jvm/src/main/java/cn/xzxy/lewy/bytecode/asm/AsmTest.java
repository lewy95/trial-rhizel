package cn.xzxy.lewy.bytecode.asm;

/**
 * ASM：线上工具
 * https://plugins.jetbrains.com/plugin/5918-asm-bytecode-outline
 */
public class AsmTest {

  public static void main(String[] args) {
    Base base = new Base();
    base.process();
  }

}

// 执行后：
// start
// process
// end
// 原文件中只有：
// process
