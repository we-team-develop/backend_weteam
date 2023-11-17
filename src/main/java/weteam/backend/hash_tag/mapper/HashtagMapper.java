package weteam.backend.hash_tag.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import weteam.backend.hash_tag.domain.Hashtag;
import weteam.backend.hash_tag.dto.HashtagDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HashtagMapper {
    HashtagMapper instance = Mappers.getMapper(HashtagMapper.class);


    Hashtag toEntity(HashtagDto.create request);

    default String setType(int type) {
        return switch (type) {
            case 1 -> "희망업무";
            case 2 -> "mbti";
            case 3 -> "특기";
            case 4 -> "성격";
            default -> "기타";
        };
    }
}
