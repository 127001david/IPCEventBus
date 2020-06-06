package org.geenrobot.eventbus;

import android.os.Bundle;
import org.geenrobot.eventbus.IIPCEventPostListener;

interface IPCBusInterface {
    void registerListener(IIPCEventPostListener listener);
    void unRegisterListener(IIPCEventPostListener listener);
    void postData(in Bundle event);
}
