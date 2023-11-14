package weteam.backend.member.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import weteam.backend.member.domain.Member;
import weteam.backend.member.domain.dto.MemberJoin;
import weteam.backend.member.domain.dto.MemberResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    MemberMapper instance = Mappers.getMapper(MemberMapper.class);

    @Mapping(target = "image", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "password", ignore = true)
    Member toEntity(MemberJoin request);

    MemberResponse toRes(Member member);
}
