package by.epam.task5.dao.dao_impl;

import by.epam.task5.bean.Computer;
import by.epam.task5.dao.XMLDao;
import by.epam.task5.exception.XMLDaoException;
import by.epam.task5.logic.parser.DevicesStaxParser;
import com.sun.xml.internal.stream.XMLInputFactoryImpl;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.List;

public class StaxXmlDao implements XMLDao {

    private static final Logger LOGGER = Logger.getLogger(StaxXmlDao.class.getPackage().getName());

    private static final StaxXmlDao instance = new StaxXmlDao();

    public static XMLDao getInstance() {
        return instance;
    }

    private StaxXmlDao() {}

    @Override
    public List<Computer> parse(InputStream inputStream) throws XMLDaoException {
        try {
            XMLStreamReader reader = XMLInputFactoryImpl.newInstance().createXMLStreamReader(inputStream);
            return DevicesStaxParser.parse(reader);
        } catch (XMLStreamException e) {
            LOGGER.debug("Can't get list with objects");
            throw new XMLDaoException("Parse Exception", e);
        }
    }
}
