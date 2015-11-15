package by.epam.task5.dao;

import by.epam.task5.dao.dao_impl.DevicesDao;
import by.epam.task5.dao.dao_impl.DomXmlDao;
import by.epam.task5.dao.dao_impl.SaxXmlDao;
import by.epam.task5.dao.dao_impl.StaxXmlDao;
import by.epam.task5.exception.XMLDaoException;
import org.apache.log4j.Logger;

public class XMLDaoFactory {

    private static final Logger LOGGER = Logger.getLogger(XMLDaoFactory.class.getPackage().getName());

    private static final XMLDaoFactory instance = new XMLDaoFactory();

    private XMLDaoFactory() {}

    public static XMLDaoFactory getInstance() {
        return instance;
    }

    public XMLDao getDao(DAOType daoType) throws XMLDaoException {
        switch (daoType) {
            case SAX: {
                return SaxXmlDao.getInstance();
            }
            case STAX: {
                return StaxXmlDao.getInstance();
            }
            case DOM: {
                return DomXmlDao.getInstance();
            }
            default:
                LOGGER.debug("Unknown DAO type");
                throw new XMLDaoException("XML DAO not exist");
        }
    }

    public InfoDao getInfoDao(DAOType daoType) throws XMLDaoException {
        switch (daoType) {
            case INFO: {
                return DevicesDao.getInstance();
            }
            default: {
                LOGGER.debug("Unknown DAO type");
                throw new XMLDaoException("Info DAO not exist");
            }
        }
    }

    public enum DAOType {
        SAX,
        STAX,
        DOM,
        INFO
    }

}
