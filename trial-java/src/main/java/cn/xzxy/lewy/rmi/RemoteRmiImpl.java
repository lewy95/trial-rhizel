package cn.xzxy.lewy.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteRmiImpl extends UnicastRemoteObject implements RemoteRmi {

    private static final long serialVersionUID = 1L;

    public RemoteRmiImpl() throws RemoteException {
    }

    @Override
    public void sendNoReturn(String message) throws RemoteException, InterruptedException {
        Thread.sleep(2000);
        //throw new RemoteException();
    }

    @Override
    public String sendHasReturn(JoinRmiEvt joinRmiEvt) throws RemoteException {
        if (joinRmiEvt.getId() >= 0)
            return "the " + joinRmiEvt.getName() + " has join";
        else return null;
    }
}
