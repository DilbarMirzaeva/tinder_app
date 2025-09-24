package az.turing.mapper;

import az.turing.domain.entity.Match;
import az.turing.dto.response.MatchResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MatchMapper{
    MatchResponse toDto(Match match);
    Match toEntityFromResponse(MatchResponse matchResponse);
}
