package edu.iu.stffrenc.remotecontroller.repository;

import edu.iu.stffrenc.remotecontroller.model.DeviceData;
import edu.iu.stffrenc.remotecontroller.model.Light;
import edu.iu.stffrenc.remotecontroller.model.RemoteControl;

import java.util.HashMap;
import java.util.List;

public class RemoteLoader implements IRemoteLoader {
    private static RemoteLoader instance;
    private HashMap<Integer, RemoteControl> remoteControls = new HashMap<>();

    // Private constructor to prevent instantiation
    private RemoteLoader() {}

    // Static method to get the single instance of the class
    public static synchronized RemoteLoader getInstance() {
        if (instance == null) {
            instance = new RemoteLoader();
        }
        return instance;
    }

    @Override
    public void setup(int id, List<DeviceData> devices) {
        RemoteControl remoteControl = new RemoteControl(devices.size());
        for (DeviceData device : devices) {
            switch (device.type()) {
                case "light":
                    Light light = new Light(device.location());
                    remoteControl.setCommand(device.slot(), light::on, light::off);
                    break;
            }
        }
        remoteControls.put(id, remoteControl);
        System.out.println(remoteControl.toString());
    }

    @Override
    public String onButtonWasPushed(int id, int slot) {
        return remoteControls.get(id).onButtonWasPushed(slot);
    }

    @Override
    public String offButtonWasPushed(int id, int slot) {
        return remoteControls.get(id).offButtonWasPushed(slot);
    }
}
