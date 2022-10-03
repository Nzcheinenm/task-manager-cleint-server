package ru.t1.dkononov.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.model.Project;

@Getter
@Setter
@NoArgsConstructor
public class ProjectStartByIndexResponse extends AbstractProjectResponse {

    public ProjectStartByIndexResponse(@Nullable final Project project) {
        super(project);
    }

}
