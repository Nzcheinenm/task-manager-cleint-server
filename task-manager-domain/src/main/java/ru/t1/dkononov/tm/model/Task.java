package ru.t1.dkononov.tm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.model.IWBS;
import ru.t1.dkononov.tm.enumerated.Status;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Task extends AbstractUserOwnedModel implements IWBS {

    private final static long serialVersionUID = 1;

    @NotNull
    private String name = "";

    @NotNull
    private String description = "";

    @NotNull
    private Status status = Status.NOT_STARTED;

    @Nullable
    private String projectId;

    @NotNull
    private Date created = new Date();

    public Task(@NotNull final String name, @NotNull final Status status) {
        this.name = name;
        this.status = status;
    }

}
