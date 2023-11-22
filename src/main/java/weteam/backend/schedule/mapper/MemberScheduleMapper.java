package weteam.backend.schedule.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import weteam.backend.member.domain.Member;
import weteam.backend.schedule.domain.MemberSchedule;
import weteam.backend.schedule.dto.MemberScheduleDto;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberScheduleMapper {
    MemberScheduleMapper instance = Mappers.getMapper(MemberScheduleMapper.class);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "member", source = "member")
    MemberSchedule toEntity(MemberScheduleDto request, Member member);

    @Named("E2D")
    MemberScheduleDto.Res toRes(MemberSchedule memberSchedule);

    @IterableMapping(qualifiedByName = "E2D")
    List<MemberScheduleDto.Res> toResList(List<MemberSchedule> memberScheduleList);
}
