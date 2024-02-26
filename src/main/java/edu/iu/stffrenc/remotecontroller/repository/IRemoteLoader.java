package edu.iu.stffrenc.remotecontroller.repository;

import edu.iu.stffrenc.remotecontroller.model.DeviceData;

import java.util.List;

public interface IRemoteLoader {
    void setup(int id, List<DeviceData> devices);

    String onButtonWasPushed(int id, int slot);
    String offButtonWasPushed(int id, int slot);

}
