package weteam.backend.schedule.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import weteam.backend.member.domain.Member;
import weteam.backend.schedule.domain.MemberSchedule;
import weteam.backend.schedule.dto.MemberScheduleDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberScheduleMapper {
    MemberScheduleMapper instance = Mappers.getMapper(MemberScheduleMapper.class);

    @Mapping(target = "member", source = "member")
    MemberSchedule toEntity(MemberScheduleDto request, Member member);
}
