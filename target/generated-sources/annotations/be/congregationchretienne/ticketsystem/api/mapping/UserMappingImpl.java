package be.congregationchretienne.ticketsystem.api.mapping;

import be.congregationchretienne.ticketsystem.api.dto.UserDTO;
import be.congregationchretienne.ticketsystem.api.model.User;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class UserMappingImpl implements UserMapping {

    @Override
    public UserDTO entityToDTO(User user, CycleAvoidingMappingContext context) {
        UserDTO target = context.getMappedInstance( user, UserDTO.class );
        if ( target != null ) {
            return target;
        }

        if ( user == null ) {
            return null;
        }

        String id = null;
        String name = null;
        String email = null;

        if ( user.getId() != null ) {
            id = user.getId().toString();
        }
        name = user.getName();
        email = user.getEmail();

        UserDTO userDTO = new UserDTO( id, name, email );

        context.storeMappedInstance( user, userDTO );

        return userDTO;
    }

    @Override
    public User dtoToEntity(UserDTO user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        if ( user.id() != null ) {
            user1.setId( UUID.fromString( user.id() ) );
        }
        user1.setName( user.name() );
        user1.setEmail( user.email() );

        return user1;
    }
}
