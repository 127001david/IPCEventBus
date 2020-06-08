package org.geenrobot.eventbus;

import android.os.Bundle;

interface IIPCEventPostListener {
    void onReceiveEvent(inout Bundle event);
    void onReceiveStickyEvent(inout Bundle event);
}
