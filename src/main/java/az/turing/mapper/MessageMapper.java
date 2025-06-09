package az.turing.mapper;

import az.turing.domain.entity.Message;
import az.turing.dto.response.MessageResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper extends EntityMapper<Message, MessageResponse>{
    MessageResponse toDto(Message m);
    Message toEntity(MessageResponse m);
}
