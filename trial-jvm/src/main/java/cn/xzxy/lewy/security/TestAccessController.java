package cn.xzxy.lewy.security;

import java.net.SocketPermission;
import java.security.AccessControlException;
import java.security.AccessController;

public class TestAccessController {

  public static void main(String[] args) {
    System.setSecurityManager(new SecurityManager());
    SocketPermission sp = new SocketPermission("127.0.0.1:9999", "connect");
    try {
      AccessController.checkPermission(sp);
      System.out.println("Ok to open socket");
    } catch (AccessControlException ace) {
      System.out.println(ace.getMessage());
    }
  }

}

// 打印结果：access denied ("java.net.SocketPermission" "127.0.0.1:9999" "connect,resolve")
// 需要在默认的安全策略文件上配置此端口的连接权限，后打印结果：Ok to open socket
