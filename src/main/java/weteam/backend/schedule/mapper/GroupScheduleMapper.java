package weteam.backend.schedule.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import weteam.backend.member.domain.Member;
import weteam.backend.member.mapper.MemberMapper;
import weteam.backend.schedule.domain.MemberSchedule;
import weteam.backend.schedule.dto.MemberScheduleDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GroupScheduleMapper {
    GroupScheduleMapper instance = Mappers.getMapper(GroupScheduleMapper.class);


}
