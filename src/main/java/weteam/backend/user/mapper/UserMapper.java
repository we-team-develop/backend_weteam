package weteam.backend.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import weteam.backend.user.domain.User;
import weteam.backend.user.domain.dto.UserResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper instance = Mappers.getMapper(UserMapper.class);

    UserResponse toRes(User user);
}
