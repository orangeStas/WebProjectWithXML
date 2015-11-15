package by.epam.task5.bean.type;

import java.util.List;

/**
 * Created by stas- on 10/16/2015.
 */
public class Type {
    private boolean isPeripheral;
    private int power;
    private List<Port> ports;
    private Hardware hardware;

    public boolean isPeripheral() {
        return isPeripheral;
    }

    public void setIsPeripheral(boolean isPeripheral) {
        this.isPeripheral = isPeripheral;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public List<Port> getPorts() {
        return ports;
    }

    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }

    public Hardware getHardware() {
        return hardware;
    }

    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
    }
}
