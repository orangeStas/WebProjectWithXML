package by.epam.task5.dao.dao_impl;

import by.epam.task5.bean.Computer;
import by.epam.task5.dao.XMLDao;
import by.epam.task5.exception.XMLDaoException;
import by.epam.task5.logic.parser.DevicesSaxHandler;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SaxXmlDao implements XMLDao {

    private static final Logger LOGGER = Logger.getLogger(SaxXmlDao.class.getPackage().getName());

    private final static SaxXmlDao instance = new SaxXmlDao();

    public static XMLDao getInstance() {
        return instance;
    }

    @Override
    public List<Computer> parse(InputStream inputStream) throws XMLDaoException {
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            DevicesSaxHandler devicesSaxHandler = new DevicesSaxHandler();
            reader.setContentHandler(devicesSaxHandler);

            reader.parse(new InputSource(inputStream));
            return devicesSaxHandler.getDevices();
        } catch (SAXException | IOException e) {
            LOGGER.debug("Can't get list with objects");
            throw new XMLDaoException("Parse Exception", e);
        }
    }
}
