package by.epam.task5.logic.command.impl;

import by.epam.task5.controller.JspPageName;
import by.epam.task5.exception.CommandException;
import by.epam.task5.logic.command.ICommand;

import javax.servlet.http.HttpServletRequest;

public class UnknownCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return JspPageName.ERROR_PAGE;
    }
}
