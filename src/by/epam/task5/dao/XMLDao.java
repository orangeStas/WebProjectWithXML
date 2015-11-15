package by.epam.task5.dao;

import by.epam.task5.bean.Computer;
import by.epam.task5.exception.XMLDaoException;

import java.io.InputStream;
import java.util.List;

public interface XMLDao {
    List<Computer> parse(InputStream inputStream) throws XMLDaoException;
}
