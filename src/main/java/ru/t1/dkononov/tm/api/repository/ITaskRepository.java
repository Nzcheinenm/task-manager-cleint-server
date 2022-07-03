package ru.t1.dkononov.tm.api.repository;

import ru.t1.dkononov.tm.model.Task;

import java.util.List;
import java.util.Objects;

public interface ITaskRepository {
    List<Task> findAll();

    Task add(final Task task);

    void clear();

    Task create(final String name);

    Task create(final String name, final String description);

    Task findById(final String id);

    Task findByIndex(final Integer index);

    Task remove(final Task task);

    Task removeById(final String id);

    Task removeByIndex(final Integer index);

    List<Task> findAllByProjectId(String projectId);
}
