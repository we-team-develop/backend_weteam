package weteam.backend.group_project;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import weteam.backend.group_project.dto.GroupProjectCreate;

@RestController
@RequestMapping("/api/group-projects")
@RequiredArgsConstructor
@Tag(name = "Group Project", description = "group project API")
public class GroupProjectController {
    private final GroupProjectService groupProjectService;

        @PostMapping("/create")
        @Operation(summary = "팀플 생성")
        @PreAuthorize("hasAnyRole('USER')")
        public String createProject(@RequestBody @Valid GroupProjectCreate request) {
            return "sss";
        }
}
