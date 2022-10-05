package ru.t1.dkononov.tm.command.data;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.client.IDomainEndpointClient;
import ru.t1.dkononov.tm.command.AbstractCommand;

public abstract class AbstractDataCommand extends AbstractCommand {

    @NotNull
    protected IDomainEndpointClient getDomainEndpoint() {
        return serviceLocator.getDomainEndpointClient();
    }

    public AbstractDataCommand() {
    }

}