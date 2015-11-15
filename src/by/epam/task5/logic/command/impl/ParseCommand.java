package by.epam.task5.logic.command.impl;

import by.epam.task5.bean.Computer;
import by.epam.task5.controller.JspPageName;
import by.epam.task5.controller.RequestParameterName;
import by.epam.task5.dao.InfoDao;
import by.epam.task5.dao.XMLDao;
import by.epam.task5.dao.XMLDaoFactory;
import by.epam.task5.exception.CommandException;
import by.epam.task5.logic.command.CommandHelper;
import by.epam.task5.logic.command.CommandName;
import by.epam.task5.logic.command.ICommand;
import by.epam.task5.logic.command.LockManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class ParseCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        XMLDao dao;
        List<Computer> devices = null;
        try {
            Lock lock = LockManager.getLock();
            lock.tryLock();
            try {
                dao = XMLDaoFactory.getInstance().getDao(XMLDaoFactory.DAOType.valueOf(request.getParameter(RequestParameterName.PARSER).toUpperCase()));
                devices = dao.parse(request.getPart(RequestParameterName.FILE).getInputStream());

                InfoDao infoDao = XMLDaoFactory.getInstance().getInfoDao(XMLDaoFactory.DAOType.INFO);
                infoDao.setDevices(devices);

                ICommand command = CommandHelper.getCommand(CommandName.LOAD_INFO_COMMAND.toString());
                command.execute(request);
            }
            finally {
                lock.unlock();
            }

            page = JspPageName.TABLE_PAGE;
        } catch (Exception e) {
            throw new CommandException("Can't get XMLDao", e);
        }

        return page;
    }

}
