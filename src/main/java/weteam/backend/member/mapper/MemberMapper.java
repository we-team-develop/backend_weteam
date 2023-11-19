package weteam.backend.member.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import weteam.backend.hash_tag.domain.MemberHashtag;
import weteam.backend.hash_tag.dto.HashtagDto;
import weteam.backend.member.domain.Member;
import weteam.backend.member.dto.MemberDto;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    MemberMapper instance = Mappers.getMapper(MemberMapper.class);

    @Mapping(target = "image", ignore = true)
    @Mapping(target = "roles", source = "role")
    @Mapping(target = "password", source = "password")
    Member toEntity(MemberDto.Join request, String password, List<String> role);

    @Mapping(target = "imageUrl", ignore = true)
    MemberDto.Res toRes(Member member);

//    @Mapping(target = "memberId", source = "member.id")
    @Mapping(target = "jwt", source = "jwt")
    @Mapping(target = "imageUrl", ignore = true)
    @Mapping(target = "hashtagList", source = "member.memberHashtagList", qualifiedByName = "setHashtagList")
    MemberDto.Res toRes(Member member, String jwt);

    @Named("setHashtagList")
    default List<HashtagDto.Res> setHashtagList(List<MemberHashtag> hashtagList) {
        return hashtagList.stream().map(memberHashtag -> {
            return HashtagDto.Res.builder()
                                 .id(memberHashtag.getId())
                                 .name(memberHashtag.getHashtag().getName())
                                 .type(memberHashtag.getHashtag().getType())
                                 .isUse(memberHashtag.isUse())
                                 .build();
        }).collect(Collectors.toList());
    }
}
