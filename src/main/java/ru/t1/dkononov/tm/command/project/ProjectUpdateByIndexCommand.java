package ru.t1.dkononov.tm.command.project;

import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectUpdateByIndexCommand extends AbstractProjectCommand {

    public static final String NAME = "project-update-by-index";

    public static final String DESCRIPTION = "Обновить проект по индексу.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractException {
        System.out.println("[ENTER INDEX]");
        final Integer index = TerminalUtil.nextNumber() - 1;
        System.out.println("[ENTER NAME]");
        final String name = TerminalUtil.inLine();
        System.out.println("[ENTER DESCRIPTION]");
        final String description = TerminalUtil.inLine();
        getProjectService().updateByIndex(index, name, description);
    }

}