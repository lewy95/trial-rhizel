package cn.xzxy.lewy.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RemoteRmiClient {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException, InterruptedException {

        System.out.println("the client has started");

        String url = "rmi://127.0.0.1:11093/remote_rmi";
        RemoteRmi remoteRmi = (RemoteRmi) Naming.lookup(url);

        // 获取无返回值对象
        remoteRmi.sendNoReturn("send no return");
        // 获取有返回值对象
        System.out.println(remoteRmi.sendHasReturn(new JoinRmiEvt(1L, "lewy")));

        System.out.println("the client has end");
    }
}
