package weteam.backend.schedule.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Builder
public class MemberScheduleDto {
    @Size(min = 1, max = 50)
    @Schema(description = "개인스케줄 제목", nullable = false, example = "교수님이랑 밥먹기")
    private String title;

    @FutureOrPresent
    @Schema(description = "시작 날짜", nullable = false, example = "2023.11.23 10:23:32")
    private LocalDateTime startedAt;

    @FutureOrPresent
    @Schema(description = "시작 날짜", nullable = true, example = "2023.11.23 10:23:32")
    private LocalDateTime endedAt;

    @Schema(description = "장소 ", nullable = true, example = "2공학관 302호")
    private String place;

    @FutureOrPresent
    @Schema(description = "알람 / null 가능", nullable = true, example = "2023.11.21 11:11:11")
    private LocalDateTime alarm;

    //TODO: annotaion으로 전환
    @Schema(description = "반복  ", nullable = true, example = "2023.11.21 11:11:11")
    private int repeatType;

    @Schema(description = "메모 / null 가능", nullable = true, example = "오늘 저녁은 참치김치찌개 jmt 예정")
    private String memo;

    @Schema(description = "스케줄 색상 설정", nullable = true, example = "오렌지 이스 더 뉴 블랙")
    private String color;
}
