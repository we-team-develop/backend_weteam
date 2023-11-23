package weteam.backend.auth.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import weteam.backend.auth.domain.Auth;
import weteam.backend.auth.dto.AuthDto;
import weteam.backend.member.domain.Member;
import weteam.backend.member.mapper.MemberMapper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {
    AuthMapper instance = Mappers.getMapper(AuthMapper.class);

    @Mapping(target = "roles",expression = "java(java.util.List.of(\"USER\"))")
    @Mapping(target = "password",source = "password")
    @Mapping(target = "member",source = "member")
    Auth toEntity(AuthDto.Join request, String password, Member member);
}
