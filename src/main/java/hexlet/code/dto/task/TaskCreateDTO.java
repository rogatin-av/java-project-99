package hexlet.code.dto.task;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.Set;

@Getter
@Setter
public class TaskCreateDTO {

    private Long index;

    @NotNull
    @NotBlank
    private String title;
    private String content;

    @NotNull
    private String status;

    @JsonProperty("assignee_id")
    private JsonNullable<Long> assigneeId;

    private Set<Long> taskLabelIds;
}
