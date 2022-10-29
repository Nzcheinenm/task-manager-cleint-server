package ru.t1.dkononov.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.api.repository.IUserOwnedRepository;
import ru.t1.dkononov.tm.api.repository.IUserRepository;
import ru.t1.dkononov.tm.api.services.*;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.entity.TaskNotFoundException;
import ru.t1.dkononov.tm.exception.field.ProjectIdEmptyException;
import ru.t1.dkononov.tm.exception.field.TaskIdEmptyException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.Task;
import ru.t1.dkononov.tm.repository.ProjectRepository;
import ru.t1.dkononov.tm.repository.TaskRepository;
import ru.t1.dkononov.tm.repository.UserRepository;

import java.sql.Connection;
import java.util.List;

public final class ProjectTaskService implements IProjectTaskService {

    @NotNull
    private final IProjectService projectService;

    @NotNull
    private final ITaskService taskService;

    public ProjectTaskService(
            @NotNull final IProjectService projectService,
            @NotNull final ITaskService taskService
    ) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @Override
    public void removeProjectById(
            @Nullable final String userId,
            @Nullable final String projectId
    ) throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();
        if (!projectService.existsById(userId, projectId)) throw new ProjectNotFoundException();
        @NotNull final List<Task> tasks = taskService.findAllByProjectId(userId, projectId);
        tasks.forEach(m -> {
            try {
                taskService.removeById(userId, m.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        projectService.removeById(userId, projectId);
    }

    @Override
    public void unbindTaskFromProject(
            @Nullable final String userId,
            @Nullable final String projectId,
            @Nullable final String taskId
    ) throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();
        if (taskId == null || taskId.isEmpty()) throw new TaskIdEmptyException();
        if (!projectService.existsById(userId, projectId)) throw new ProjectNotFoundException();
        @Nullable final Task task = taskService.findById(userId, taskId);
        if (task == null) throw new TaskNotFoundException();
        task.setProjectId(null);
        taskService.updateProjectIdById(userId, taskId, null);
    }

    @Override
    public void bindTaskToProject(
            @Nullable final String userId,
            @Nullable final String projectId,
            @Nullable final String taskId
    ) throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();
        if (taskId == null || taskId.isEmpty()) throw new TaskIdEmptyException();
        if (!projectService.existsById(userId, projectId)) throw new ProjectNotFoundException();
        @Nullable final Task task = taskService.findById(userId, taskId);
        if (task == null) throw new TaskNotFoundException();
        task.setProjectId(projectId);
        taskService.updateProjectIdById(userId, taskId, projectId);
    }

}
