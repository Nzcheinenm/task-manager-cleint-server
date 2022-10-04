package ru.t1.dkononov.tm.command.task;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.TaskUnbindFromProjectRequest;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskUnbindFromProjectCommand extends AbstractTaskCommand {

    @Getter
    @NotNull
    public final String NAME = "task-unbind-from-project";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Отвязать задачу от Проекта";

    @Override
    public void execute() throws Exception {
        System.out.println("[UNBIND TASK TO PROJECT]");
        System.out.println("[ENTER PROJECT ID:]");
        @NotNull final String projectId = TerminalUtil.inLine();
        System.out.println("[ENTER TASK ID:]");
        @NotNull final String taskId = TerminalUtil.inLine();
        @NotNull final TaskUnbindFromProjectRequest request = new TaskUnbindFromProjectRequest();
        request.setTaskId(taskId);
        request.setProjectId(projectId);
        getTaskEndpointClient().unbindTaskToProject(request);
    }

}
