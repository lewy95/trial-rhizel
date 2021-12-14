package cn.xzxy.lewy.bytecode.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Generator类，在这个类中定义ClassReader和ClassWriter，
 * 其中的逻辑是，classReader读取字节码，然后交给MyClassVisitor类处理，处理完成后由ClassWriter写字节码并将旧的字节码替换掉
 */
public class Generator {
  public static void main(String[] args) throws Exception {
    // 读取
    ClassReader classReader = new ClassReader("cn/xzxy/lewy/bytecode/asm/Base");
    ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
    // 处理
    ClassVisitor classVisitor = new MyClassVisitor(classWriter);
    classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
    byte[] data = classWriter.toByteArray();
    // 输出
    File f = new File("trial-jvm/target/classes/cn/xzxy/lewy/asm/Base.class");
    FileOutputStream out = new FileOutputStream(f);
    out.write(data);
    out.close();
    System.out.println("now generator cc success!!!!!");
  }
}

// 通过 MyClassVisitor 增强后的 Base 如下：
//public class Base {
//  public Base() {
//  }
//
//  public void process() {
//    System.out.println("start");
//    System.out.println("process");
//    System.out.println("end");
//  }
//}
