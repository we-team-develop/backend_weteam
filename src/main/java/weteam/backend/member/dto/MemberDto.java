package weteam.backend.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import weteam.backend.hash_tag.dto.HashtagDto;

import java.util.ArrayList;
import java.util.List;


public class MemberDto {
    @Getter
    @Builder
    public static class Login{
        @NotBlank(message = "uid 누락")
        @Size(min = 1,max = 11)
        @Schema(description = "사용자 아이디", nullable = false, example = "user11111")
        private String uid;

        @NotBlank(message = "password 누락")
        @Size(min = 1,max = 11)
        @Schema(description = "사용자 비밀번호", nullable = false, example = "1111")
        private String password;
    }
    @Getter
    @Builder
    public static class LoginRes {
        @Schema(description = "사용자 pk", nullable = false, example = "12")
        private Long id;

        @Schema(description = "사용자 이름", nullable = false, example = "홍유진")
        private String username;

        @Schema(description = "사용자 닉네임", nullable = false, example = "nickname111")
        private String nickname;

        @Schema(description = "사용자 프로필 사진", nullable = true, example = "image url/asdafasdcsvasdasdasd")
        private String imageUrl;

        @Schema(description = "사용자 속", nullable = true, example = "인덕대학교")
        private String organization;

        @Schema(description = "해시태그 리스트", nullable = true)
        private List<HashtagDto.Res> hashtagList = new ArrayList<>();

        @Schema(description = "jwt", nullable = false)
        private String jwt;
    }
    @Getter
    @Builder
    public static class JoinRes{
        @Schema(description = "사용자 pk", nullable = false, example = "12")
        private Long id;

        @Schema(description = "사용자 아이디", nullable = false, example = "user1111")
        private String uid;

        @Schema(description = "사용자 이름", nullable = false, example = "홍유진")
        private String username;

        @Schema(description = "사용자 닉네임", nullable = false, example = "nickname111")
        private String nickname;

        @Schema(description = "jwt", nullable = false)
        private String jwt;
    }
}
