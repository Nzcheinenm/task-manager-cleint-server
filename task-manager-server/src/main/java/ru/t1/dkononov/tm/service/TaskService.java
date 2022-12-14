package ru.t1.dkononov.tm.service;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.api.services.ITaskService;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.model.Task;
import ru.t1.dkononov.tm.repository.TaskRepository;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

public final class TaskService extends AbstractUserOwnedService<Task, ITaskRepository> implements ITaskService {

    @NotNull
    public ITaskRepository getRepository(@NotNull Connection connection) {
        return new TaskRepository(connection);
    }

    public TaskService(@NotNull final IConnectionService connectionService) {
        super(connectionService);
    }

    @NotNull
    @Override
    public List<Task> findAllByProjectId(@Nullable final String userId, @Nullable final String projectId)
            throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (projectId == null || projectId.isEmpty()) return Collections.emptyList();
        try (@NotNull final Connection connection = getConnection()) {
            @NotNull final ITaskRepository repository = getRepository(connection);
            return repository.findAllByProjectId(userId, projectId);
        }
    }

    @NotNull
    @Override
    public Task create(@Nullable final String userId, @Nullable final String name, @Nullable final String description)
            throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        @NotNull final Connection connection = getConnection();
        @Nullable final Task task;
        try {
            @NotNull final ITaskRepository repository = getRepository(connection);
            task = repository.create(userId, name, description);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return task;
    }

    @NotNull
    @Override
    public Task create(@Nullable final String userId, @Nullable final String name) throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        @NotNull final Connection connection = getConnection();
        @Nullable final Task task;
        try {
            @NotNull final ITaskRepository repository = getRepository(connection);
            task = repository.create(userId, name);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return task;
    }

    @Override
    @Nullable
    public Task updateById(
            @Nullable final String userId,
            @Nullable final String id,
            @Nullable final String name,
            @Nullable final String description
    )
            throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        @NotNull final Connection connection = getConnection();
        @Nullable final Task task;
        try {
            @NotNull final ITaskRepository repository = getRepository(connection);
            task = repository.findById(userId, id);
            if (task == null) throw new TaskIdEmptyException();
            task.setName(name);
            task.setDescription(description);
            task.setUserId(userId);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return task;
    }

    @Override
    @Nullable
    public Task updateByIndex(
            @Nullable final String userId,
            @Nullable final Integer index,
            @Nullable final String name,
            @Nullable final String description
    )
            throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        @NotNull final Connection connection = getConnection();
        @Nullable final Task task;
        try {
            @NotNull final ITaskRepository repository = getRepository(connection);
            task = repository.findByIndex(userId, index);
            if (task == null) throw new TaskIdEmptyException();
            task.setName(name);
            task.setDescription(description);
            task.setUserId(userId);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return task;
    }

    @Override
    @Nullable
    public Task changeTaskStatusById(
            @Nullable final String userId,
            @Nullable final String id,
            @Nullable final Status status
    )
            throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NotNull final Connection connection = getConnection();
        @Nullable final Task task;
        try {
            @NotNull final ITaskRepository repository = getRepository(connection);
            task = findById(userId, id);
            if (task == null) throw new TaskIdEmptyException();
            task.setStatus(status);
            task.setUserId(userId);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return task;
    }

    @Override
    @Nullable
    public Task changeTaskStatusByIndex(
            @Nullable final String userId,
            @Nullable final Integer index,
            @NotNull final Status status
    ) throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        @NotNull final Connection connection = getConnection();
        @Nullable final Task task;
        try {
            @NotNull final ITaskRepository repository = getRepository(connection);
            task = findByIndex(userId, index);
            if (task == null) throw new TaskIdEmptyException();
            task.setStatus(status);
            task.setUserId(userId);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return task;
    }

    @Override
    public void updateProjectIdById(@NotNull String userId, @Nullable String taskId, @Nullable String projectId) throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (taskId == null || taskId.isEmpty()) throw new TaskIdEmptyException();
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();

        @NotNull final Connection connection = getConnection();
        @Nullable final Task task;
        try {
            @NotNull final ITaskRepository repository = getRepository(connection);
            task = repository.findTaskIdByProjectId(userId, taskId, projectId);
            if (task == null) throw new TaskIdEmptyException();
            task.setProjectId(projectId);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

}
