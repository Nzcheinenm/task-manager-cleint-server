package ru.t1.dkononov.tm.command.project;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.ProjectUpdateByIndexRequest;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectUpdateByIndexCommand extends AbstractProjectCommand {

    @Getter
    @NotNull
    public final String NAME = "project-update-by-index";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Обновить проект по индексу.";

    @Override
    public void execute() throws Exception {
        System.out.println("[ENTER INDEX]");
        @NotNull final Integer index = TerminalUtil.nextNumber() - 1;
        System.out.println("[ENTER NAME]");
        @NotNull final String name = TerminalUtil.inLine();
        System.out.println("[ENTER DESCRIPTION]");
        @NotNull final String description = TerminalUtil.inLine();
        @NotNull final ProjectUpdateByIndexRequest request = new ProjectUpdateByIndexRequest(getToken());
        request.setIndex(index);
        request.setDescription(description);
        request.setName(name);
        getProjectEndpoint().updateProjectByIndex(request);
    }

}
