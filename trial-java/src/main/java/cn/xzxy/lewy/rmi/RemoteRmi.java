package cn.xzxy.lewy.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 自定义接口，要求实现 Remote
 */
public interface RemoteRmi extends Remote {

    void sendNoReturn(String message) throws RemoteException, InterruptedException;

    String sendHasReturn(JoinRmiEvt joinRmiEvt) throws RemoteException;
}
