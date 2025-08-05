package be.ticketsystem.api.mapping;

import be.ticketsystem.api.dto.type.UserDTO;
import be.ticketsystem.api.model.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapping {

  UserMapping INSTANCE_USER = Mappers.getMapper(UserMapping.class);

  UserDTO entityToDTO(User user, @Context CycleAvoidingMappingContext context);

  User dtoToEntity(UserDTO user);
}
