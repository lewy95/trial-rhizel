package cn.xzxy.lewy.security;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestSecurityManager {

  static class SecurityManagerImpl extends SecurityManager {

    public void checkRead(String file) {
      throw new SecurityException();  // 这里进行了阻断，表示安全管理器不允许这样操作
    }
  }

  public static void main(String[] args) {
    // CurrentSecurityManager is null
    System.out.println("CurrentSecurityManager is " + System.getSecurityManager());
    System.setSecurityManager(new SecurityManagerImpl());
    // CurrentSecurityManager is cn.xzxy.lewy.security.TestSecurityManager$SecurityManagerImpl@2503dbd3
    System.out.println("CurrentSecurityManager is " + System.getSecurityManager());
    String s;
    try {
      FileReader fr = new FileReader("D:\\etc\\cas\\logs\\aaa.txt");
      BufferedReader br = new BufferedReader(fr);
      while ((s = br.readLine()) != null) {
        System.out.println(s);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
