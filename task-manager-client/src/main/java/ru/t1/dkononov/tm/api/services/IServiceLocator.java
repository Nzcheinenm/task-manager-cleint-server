package ru.t1.dkononov.tm.api.services;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.client.IEndpointClient;
import ru.t1.dkononov.tm.api.endpoint.*;


public interface IServiceLocator {

    @NotNull
    ICommandService getCommandService();

    @NotNull
    ILoggerService getLoggerService();

    @NotNull
    IPropertyService getPropertyService();

    @NotNull
    IAuthEndpoint getAuthEndpointClient();

    @NotNull
    ISystemEndpoint getSystemEndpointClient();

    @NotNull
    IDomainEndpoint getDomainEndpointClient();

    @NotNull
    IProjectEndpoint getProjectEndpointClient();

    @NotNull
    ITaskEndpoint getTaskEndpointClient();

    @NotNull
    IUserEndpoint getUserEndpointClient();

}
