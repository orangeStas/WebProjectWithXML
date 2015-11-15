package by.epam.task5.logic.command.impl;

import by.epam.task5.bean.Computer;
import by.epam.task5.controller.JspPageName;
import by.epam.task5.controller.RequestParameterName;
import by.epam.task5.dao.InfoDao;
import by.epam.task5.dao.XMLDaoFactory;
import by.epam.task5.exception.CommandException;
import by.epam.task5.logic.command.ICommand;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LoadInfoCommand implements ICommand {

    private Lock lock = new ReentrantLock();
    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_RECORDS_PER_PAGE = 5;

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        InfoDao dao;
        List<Computer> devices = null;
        try {
            lock.tryLock();
            try {
                InfoDao infoDao = XMLDaoFactory.getInstance().getInfoDao(XMLDaoFactory.DAOType.INFO);
                devices = infoDao.getDevices();
            }
            finally {
                lock.unlock();
            }

            devices = getDevicesForPage(request, devices);

            request.setAttribute(RequestParameterName.DEVICES, devices);
            page = JspPageName.TABLE_PAGE;
        } catch (Exception e) {
            throw new CommandException("Can't get InfoDao", e);
        }

        return page;
    }

    private List<Computer> getDevicesForPage(HttpServletRequest request, List<Computer> devices) {
        int pageNumber = DEFAULT_PAGE_NUMBER;
        int recordsPerPage = DEFAULT_RECORDS_PER_PAGE;


        if (request.getParameter(RequestParameterName.RECORDS_PER_PAGE) != null) {
            recordsPerPage = Integer.parseInt(request.getParameter(RequestParameterName.RECORDS_PER_PAGE));
        }

        if (request.getParameter(RequestParameterName.PAGE) != null) {
            pageNumber = Integer.parseInt(request.getParameter(RequestParameterName.PAGE));
        }

        int startPos = (pageNumber - 1) * recordsPerPage;
        int endPos = startPos + recordsPerPage;

        if (endPos > devices.size()) {
            endPos = devices.size();
        }

        int noOfRecords = devices.size();

        devices = devices.subList(startPos, endPos);

        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        
        request.setAttribute(RequestParameterName.NO_OF_PAGES, noOfPages);
        request.setAttribute(RequestParameterName.CURRENT_PAGE, pageNumber);

        return devices;
    }
}
