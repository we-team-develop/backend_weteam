package weteam.backend.member.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import weteam.backend.auth.dto.TokenInfo;
import weteam.backend.member.domain.Member;
import weteam.backend.member.dto.MemberDto;

import java.util.List;

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
    @Mapping(target = "tokenInfo", source = "tokenInfo")
    @Mapping(target = "imageUrl", ignore = true)
    MemberDto.Res toRes(Member member, TokenInfo tokenInfo);
}
