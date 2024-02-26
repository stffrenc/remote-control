package edu.iu.stffrenc.remotecontroller.controller;

import edu.iu.stffrenc.remotecontroller.model.DeviceData;
import edu.iu.stffrenc.remotecontroller.repository.IRemoteLoader;
import edu.iu.stffrenc.remotecontroller.repository.RemoteLoader;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/remotes")
public class RemoteController {

    IRemoteLoader remoteLoader = RemoteLoader.getInstance();

    @PostMapping("/{id}")
    public boolean setup(@PathVariable int id, @RequestBody List<DeviceData> devices) {
        remoteLoader.setup(id, devices);
        return  true;
    }

    @GetMapping("/{id}/on/{slot}")
    public String turnOn(@PathVariable int id, @PathVariable int slot) {
        return remoteLoader.onButtonWasPushed(id, slot);
    }

    @GetMapping("/{id}/off/{slot}")
    public String turnOff(@PathVariable int id, @PathVariable int slot) {
        return remoteLoader.offButtonWasPushed(id, slot);
    }


}
