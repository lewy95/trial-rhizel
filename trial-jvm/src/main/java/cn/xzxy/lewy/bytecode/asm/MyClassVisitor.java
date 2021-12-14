package cn.xzxy.lewy.bytecode.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * MyClassVisitor类，用于对字节码的visit以及修改
 *
 * 字节码的修改实现步骤：
 * 1. 首先通过MyClassVisitor类中的visitMethod方法，判断当前字节码读到哪一个方法了。跳过构造方法""后，将需要被增强的方法交给内部类MyMethodVisitor来进行处理。
 * 2. 接下来，进入内部类MyMethodVisitor中的visitCode方法，它会在ASM开始访问某一个方法的Code区时被调用，重写visitCode方法，将AOP中的前置逻辑就放在这里。
 * 3. MyMethodVisitor继续读取字节码指令，每当ASM访问到无参数指令时，都会调用MyMethodVisitor中的visitInsn方法。
 *    我们判断了当前指令是否为无参数的“return”指令，如果是就在它的前面添加一些指令，也就是将AOP的后置逻辑放在该方法中。
 * 4. 综上，重写MyMethodVisitor中的两个方法，就可以实现AOP了，而重写方法时就需要用ASM的写法，手动写入或者修改字节码。】
 *    通过调用methodVisitor的visitXXXXInsn()方法就可以实现字节码的插入，XXXX对应相应的操作码助记符类型，
 *    比如mv.visitLdcInsn("end")对应的操作码就是ldc "end"，即将字符串“end”压入栈。
 */
public class MyClassVisitor extends ClassVisitor implements Opcodes {

  public MyClassVisitor(ClassVisitor cv) {
    super(ASM5, cv);
  }

  @Override
  public void visit(int version, int access, String name, String signature,
                    String superName, String[] interfaces) {
    cv.visit(version, access, name, signature, superName, interfaces);
  }

  @Override
  public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
    MethodVisitor mv = cv.visitMethod(access, name, desc, signature,
        exceptions);
    // Base类中有两个方法：无参构造以及process方法，这里不增强构造方法
    if (!name.equals("<init>") && mv != null) {
      mv = new MyMethodVisitor(mv);
    }
    return mv;
  }

  /**
   * 内部类 MyMethodVisitor，继承自MethodVisitor用于对类内方法的观察
   */
  static class MyMethodVisitor extends MethodVisitor implements Opcodes {
    public MyMethodVisitor(MethodVisitor mv) {
      super(Opcodes.ASM5, mv);
    }

    @Override
    public void visitCode() {
      super.visitCode();
      mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
      mv.visitLdcInsn("start");
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }

    @Override
    public void visitInsn(int opcode) {
      if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN)
          || opcode == Opcodes.ATHROW) {
        // 方法在返回之前，打印"end"
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("end");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
      }
      mv.visitInsn(opcode);
    }
  }
}
