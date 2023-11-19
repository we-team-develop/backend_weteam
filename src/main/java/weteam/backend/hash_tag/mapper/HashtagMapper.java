package weteam.backend.hash_tag.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import weteam.backend.hash_tag.domain.Hashtag;
import weteam.backend.hash_tag.domain.MemberHashtag;
import weteam.backend.hash_tag.dto.HashtagDto;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HashtagMapper {
    HashtagMapper instance = Mappers.getMapper(HashtagMapper.class);


    Hashtag toEntity(HashtagDto.Create request);

    @Named("E2D")
    @Mapping(target = "name", source = "memberHashtag.hashtag.name")
    @Mapping(target = "type", source = "memberHashtag.hashtag.type")
    HashtagDto.Res toRes(MemberHashtag memberHashtag);

    @IterableMapping(qualifiedByName = "E2D")
    List<HashtagDto.Res> toResList(List<MemberHashtag> memberHashtagList);

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
