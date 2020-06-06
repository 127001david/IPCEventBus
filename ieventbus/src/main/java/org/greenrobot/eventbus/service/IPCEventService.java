package org.greenrobot.eventbus.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import org.geenrobot.eventbus.IIPCEventPostListener;
import org.geenrobot.eventbus.IPCBusInterface;

public class IPCEventService extends Service {

    private RemoteCallbackList<IIPCEventPostListener> mListeners = new RemoteCallbackList<>();

    public IPCEventService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return getBinder();
    }

    private IBinder getBinder() {
        return new IPCBusInterface.Stub() {
            @Override
            public void registerListener(IIPCEventPostListener listener) throws RemoteException {
                mListeners.register(listener);
            }

            @Override
            public void unRegisterListener(IIPCEventPostListener listener) throws RemoteException {
                mListeners.unregister(listener);
            }

            @Override
            public void postData(Bundle event) throws RemoteException {
                // TODO: 2020/6/5 需要处理优先级 priority
                int N = mListeners.beginBroadcast();
                for (int i = 0; i < N; i++) {
                    IIPCEventPostListener listener = mListeners.getBroadcastItem(i);

                    if (null != listener) {
                        listener.onReceiveEvent(event);
                    }
                }
                mListeners.finishBroadcast();
            }
        };
    }
}
