package cn.xzxy.lewy.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * RMI 远程服务端
 */
public class RemoteRMIServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException, InterruptedException {

        System.out.println("the RemoteRMIServer is Starting ...");

        // 本地主机上的远程对象注册表Registry的实例,默认端口11093
        LocateRegistry.createRegistry(11093);
        // 创建一个远程对象
        RemoteRmiImpl remoteRmi = new RemoteRmiImpl();
        System.out.println("Binding server implementation to registry");
        // 把远程对象注册到RMI注册服务器上，并命名为remote_rmi
        Naming.bind("rmi://127.0.0.1:11093/remote_rmi", remoteRmi);

        System.out.println("the RemoteRMIServer is Started");

        Thread.sleep(10000000);
    }
}