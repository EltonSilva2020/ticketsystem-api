package be.congregationchretienne.ticketsystem.api.mapping;

import be.ticketsystem.api.dto.DepartmentDTO;
import be.congregationchretienne.ticketsystem.api.dto.UserDTO;
import be.ticketsystem.api.mapping.CycleAvoidingMappingContext;
import be.ticketsystem.api.mapping.DepartmentMapping;
import be.ticketsystem.api.model.Category;
import be.ticketsystem.api.model.Department;
import be.ticketsystem.api.model.Ticket;
import be.ticketsystem.api.model.User;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class DepartmentMappingImpl implements DepartmentMapping {

    @Override
    public DepartmentDTO entityToDTO(Department department, CycleAvoidingMappingContext context) {
        DepartmentDTO target = context.getMappedInstance( department, DepartmentDTO.class );
        if ( target != null ) {
            return target;
        }

        if ( department == null ) {
            return null;
        }

        DepartmentDTO departmentDTO = new DepartmentDTO();

        context.storeMappedInstance( department, departmentDTO );

        if ( department.getId() != null ) {
            departmentDTO.setId( department.getId().toString() );
        }
        departmentDTO.setName( department.getName() );
        Set<Category> set = department.getCategories();
        if ( set != null ) {
            departmentDTO.setCategories( new LinkedHashSet<Category>( set ) );
        }
        Set<User> set1 = department.getUsers();
        if ( set1 != null ) {
            departmentDTO.setUsers( new LinkedHashSet<User>( set1 ) );
        }
        Set<Ticket> set2 = department.getTickets();
        if ( set2 != null ) {
            departmentDTO.setTickets( new LinkedHashSet<Ticket>( set2 ) );
        }
        departmentDTO.setCreatedAt( department.getCreatedAt() );
        departmentDTO.setCreatedBy( userToUserDTO( department.getCreatedBy(), context ) );

        return departmentDTO;
    }

    @Override
    public Department dtoToEntity(DepartmentDTO department) {
        if ( department == null ) {
            return null;
        }

        Department department1 = new Department();

        if ( department.getId() != null ) {
            department1.setId( UUID.fromString( department.getId() ) );
        }
        department1.setCreatedAt( department.getCreatedAt() );
        department1.setCreatedBy( userDTOToUser( department.getCreatedBy() ) );
        department1.setName( department.getName() );
        Set<Category> set = department.getCategories();
        if ( set != null ) {
            department1.setCategories( new LinkedHashSet<Category>( set ) );
        }
        Set<User> set1 = department.getUsers();
        if ( set1 != null ) {
            department1.setUsers( new LinkedHashSet<User>( set1 ) );
        }
        Set<Ticket> set2 = department.getTickets();
        if ( set2 != null ) {
            department1.setTickets( new LinkedHashSet<Ticket>( set2 ) );
        }

        return department1;
    }

    protected UserDTO userToUserDTO(User user, CycleAvoidingMappingContext context) {
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

    protected User userDTOToUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        if ( userDTO.id() != null ) {
            user.setId( UUID.fromString( userDTO.id() ) );
        }
        user.setName( userDTO.name() );
        user.setEmail( userDTO.email() );

        return user;
    }
}
