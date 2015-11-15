package by.epam.task5.dao.dao_impl;

import by.epam.task5.bean.Computer;
import by.epam.task5.dao.XMLDao;
import by.epam.task5.exception.XMLDaoException;
import by.epam.task5.logic.parser.DeviceDomParser;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DomXmlDao implements XMLDao {
    private static final Logger LOGGER = Logger.getLogger(DomXmlDao.class.getPackage().getName());

    private static final DomXmlDao instance = new DomXmlDao();

    public static XMLDao getInstance() {
        return instance;
    }

    private DomXmlDao() {}

    @Override
    public List<Computer> parse(InputStream inputStream) throws XMLDaoException {
        try {
            return DeviceDomParser.parse(inputStream);
        } catch (IOException | SAXException e) {
            LOGGER.debug("Can't get list with objects");
            throw new XMLDaoException("Parse Exception", e);
        }
    }
}
