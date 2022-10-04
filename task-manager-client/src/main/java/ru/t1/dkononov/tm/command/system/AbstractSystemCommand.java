package ru.t1.dkononov.tm.command.system;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.client.IProjectEndpointClient;
import ru.t1.dkononov.tm.api.client.ISystemEndpointClient;
import ru.t1.dkononov.tm.api.services.ICommandService;
import ru.t1.dkononov.tm.api.services.IPropertyService;
import ru.t1.dkononov.tm.command.AbstractCommand;
import ru.t1.dkononov.tm.enumerated.Role;

public abstract class AbstractSystemCommand extends AbstractCommand {

    @NotNull
    protected ISystemEndpointClient getSystemEndpoint() {
        return serviceLocator.getSystemEndpointClient();
    }

    @NotNull
    protected ICommandService getCommandService() {
        return serviceLocator.getCommandService();
    }

    @NotNull
    protected IPropertyService getPropertyService() {
        return serviceLocator.getPropertyService();
    }

    @Nullable
    public Role[] getRoles() {
        return null;
    }
}
