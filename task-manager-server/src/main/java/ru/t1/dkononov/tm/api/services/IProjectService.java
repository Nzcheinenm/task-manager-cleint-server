package ru.t1.dkononov.tm.api.services;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.model.Project;

import java.sql.SQLException;

public interface IProjectService extends IUserOwnedService<Project> {

    @NotNull
    Project create(@Nullable String userId, @Nullable String name, @Nullable String description) throws AbstractFieldException;

    @NotNull
    Project create(@Nullable String userId, @Nullable String name) throws AbstractFieldException, SQLException, ProjectNotFoundException;

    @Nullable Project updateById(@Nullable String userId, @Nullable String id, @Nullable String name, @Nullable String description) throws AbstractException;

    @Nullable Project updateByIndex(@Nullable String userId, @Nullable Integer index, @Nullable String name, @Nullable String description) throws AbstractException;

    @Nullable Project changeProjectStatusById(@Nullable String userId, @Nullable String id, @NotNull Status status) throws AbstractException;

    @Nullable Project changeProjectStatusByIndex(@Nullable String userId, @Nullable Integer index, @NotNull Status status) throws AbstractException;

}
