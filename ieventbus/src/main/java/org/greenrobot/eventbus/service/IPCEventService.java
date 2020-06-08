package org.greenrobot.eventbus.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import org.geenrobot.eventbus.IIPCEventPostListener;
import org.geenrobot.eventbus.IPCBusInterface;

import java.util.concurrent.ConcurrentHashMap;

public class IPCEventService extends Service {

    private RemoteCallbackList<IIPCEventPostListener> mListeners = new RemoteCallbackList<>();

    /**
     * 处理粘性广播
     */
    private ConcurrentHashMap<String, Bundle> mPostStickyEvent2Remote =
            new ConcurrentHashMap<>();

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

                for (Bundle value : mPostStickyEvent2Remote.values()) {
                    listener.onReceiveStickyEvent(value);
                }
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

            @Override
            public void postStickyData(Bundle event) throws RemoteException {
                // TODO: 2020/6/5 需要处理优先级 priority

                event.setClassLoader(getClass().getClassLoader());
                Parcelable p = event.getParcelable("event");
                if (null != p) {
                    mPostStickyEvent2Remote.put(p.getClass().getName(), event);
                }

                int N = mListeners.beginBroadcast();
                for (int i = 0; i < N; i++) {
                    IIPCEventPostListener listener = mListeners.getBroadcastItem(i);

                    if (null != listener) {
                        listener.onReceiveStickyEvent(event);
                    }
                }

                mListeners.finishBroadcast();
            }
        };
    }
}
