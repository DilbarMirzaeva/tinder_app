package az.turing.tinderdemo.mapper;

import az.turing.tinderdemo.domain.entity.Message;
import az.turing.tinderdemo.dto.response.MessageResponse;
import az.turing.tinderdemo.dto.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper extends EntityMapper<Message, MessageResponse>{
    MessageResponse toDto(Message m);
    Message toEntity(MessageResponse m);
}
