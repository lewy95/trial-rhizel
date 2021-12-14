package cn.xzxy.lewy.bytecode.javassist;

import javassist.*;

import java.io.IOException;

public class JavassistTest {
  public static void main(String[] args)
      throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException, IOException {
    // Base b = new Base(); // 不能加这一行，JVM不允许在运行时动态重载一个类的
    ClassPool cp = ClassPool.getDefault();
    CtClass cc = cp.get("cn.xzxy.lewy.bytecode.javassist.Base");
    CtMethod m = cc.getDeclaredMethod("process");
    m.insertBefore("{ System.out.println(\"start\"); }");
    m.insertAfter("{ System.out.println(\"end\"); }");
    Base h = (Base) cc.toClass().newInstance();
    h.process();
  }
}

// 执行后：
// start
// process
// end
// 原文件中只有：
// process

// 如果在main方法上加一行 Base b = new Base(); 报错如下：
// Exception in thread "main" javassist.CannotCompileException: by java.lang.ClassFormatError: loader (instance of  sun/misc/Launcher$AppClassLoader): attempted  duplicate class definition for name: "cn/xzxy/lewy/bytecode/javassist/Base"
//	at javassist.util.proxy.DefineClassHelper.toClass(DefineClassHelper.java:271)
//	at javassist.ClassPool.toClass(ClassPool.java:1240)
//	at javassist.ClassPool.toClass(ClassPool.java:1098)
//	at javassist.ClassPool.toClass(ClassPool.java:1056)
//	at javassist.CtClass.toClass(CtClass.java:1298)
//	at cn.xzxy.lewy.bytecode.javassist.JavassistTest2.main(JavassistTest2.java:16)
//Caused by: java.lang.ClassFormatError: loader (instance of  sun/misc/Launcher$AppClassLoader): attempted  duplicate class definition for name: "cn/xzxy/lewy/bytecode/javassist/Base"
//	at javassist.util.proxy.DefineClassHelper$Java7.defineClass(DefineClassHelper.java:182)
//	at javassist.util.proxy.DefineClassHelper.toClass(DefineClassHelper.java:260)
// 原因：JVM不允许在运行时动态重载一个类的
