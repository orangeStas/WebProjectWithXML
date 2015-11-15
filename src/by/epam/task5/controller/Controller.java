package by.epam.task5.controller;

import by.epam.task5.exception.CommandException;
import by.epam.task5.logic.command.CommandHelper;
import by.epam.task5.logic.command.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
public class Controller extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Controller.class.getPackage().getName());
    private static final int recordsPerPage = 5;


    public Controller() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);
        ICommand command = CommandHelper.getCommand(commandName);
        String page = null;

        try {
            page = command.execute(request);
        } catch (CommandException e) {
            LOGGER.debug("Execute command failed");
            page = JspPageName.ERROR_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);

        if (requestDispatcher != null) {
            requestDispatcher.forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

}
