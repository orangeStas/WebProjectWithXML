package by.epam.task5.dao;

import by.epam.task5.bean.Computer;

import java.util.List;

public interface InfoDao {
    void setDevices(List<Computer> devices);
    List<Computer> getDevices();
}
