package by.epam.task5.dao.dao_impl;

import by.epam.task5.bean.Computer;
import by.epam.task5.dao.InfoDao;

import java.util.List;

public class DevicesDao implements InfoDao {

    private List<Computer> devices;

    private static final DevicesDao instance = new DevicesDao();

    public static DevicesDao getInstance(){
        return instance;
    }

    private DevicesDao(){}

    @Override
    public void setDevices(List<Computer> devices) {
        this.devices = devices;
    }

    @Override
    public List<Computer> getDevices() {
        return devices;
    }
}
