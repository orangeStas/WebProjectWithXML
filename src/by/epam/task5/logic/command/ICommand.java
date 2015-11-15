package by.epam.task5.logic.command;

import by.epam.task5.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface ICommand {
    String execute(HttpServletRequest request) throws CommandException;
}
