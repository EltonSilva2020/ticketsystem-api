package be.congregationchretienne.ticketsystem.api.mapping;

import be.congregationchretienne.ticketsystem.api.dto.CategoryDTO;
import be.congregationchretienne.ticketsystem.api.dto.DepartmentDTO;
import be.congregationchretienne.ticketsystem.api.dto.TicketDTO;
import be.congregationchretienne.ticketsystem.api.dto.UserDTO;
import be.congregationchretienne.ticketsystem.api.dto.type.PriorityDTO;
import be.congregationchretienne.ticketsystem.api.dto.type.StatusDTO;
import be.congregationchretienne.ticketsystem.api.model.Category;
import be.congregationchretienne.ticketsystem.api.model.Department;
import be.congregationchretienne.ticketsystem.api.model.Ticket;
import be.congregationchretienne.ticketsystem.api.model.User;
import be.congregationchretienne.ticketsystem.api.model.type.Priority;
import be.congregationchretienne.ticketsystem.api.model.type.Status;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class CategoryMappingImpl implements CategoryMapping {

    @Override
    public CategoryDTO entityToDTO(Category category, CycleAvoidingMappingContext context) {
        CategoryDTO target = context.getMappedInstance( category, CategoryDTO.class );
        if ( target != null ) {
            return target;
        }

        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        context.storeMappedInstance( category, categoryDTO );

        if ( category.getId() != null ) {
            categoryDTO.setId( category.getId().toString() );
        }
        categoryDTO.setName( category.getName() );
        categoryDTO.setTickets( ticketSetToTicketDTOSet( category.getTickets(), context ) );
        categoryDTO.setDepartments( departmentSetToDepartmentDTOSet( category.getDepartments(), context ) );
        categoryDTO.setCreatedAt( category.getCreatedAt() );
        categoryDTO.setCreatedBy( userToUserDTO( category.getCreatedBy(), context ) );

        return categoryDTO;
    }

    @Override
    public Category dtoToEntity(CategoryDTO category) {
        if ( category == null ) {
            return null;
        }

        Category category1 = new Category();

        if ( category.getId() != null ) {
            category1.setId( UUID.fromString( category.getId() ) );
        }
        category1.setCreatedAt( category.getCreatedAt() );
        category1.setCreatedBy( userDTOToUser( category.getCreatedBy() ) );
        category1.setName( category.getName() );
        category1.setTickets( ticketDTOSetToTicketSet( category.getTickets() ) );
        category1.setDepartments( departmentDTOSetToDepartmentSet( category.getDepartments() ) );

        return category1;
    }

    protected PriorityDTO priorityToPriorityDTO(Priority priority, CycleAvoidingMappingContext context) {
        PriorityDTO target = context.getMappedInstance( priority, PriorityDTO.class );
        if ( target != null ) {
            return target;
        }

        if ( priority == null ) {
            return null;
        }

        PriorityDTO priorityDTO;

        switch ( priority ) {
            case HIGH: priorityDTO = PriorityDTO.HIGH;
            break;
            case MEDIUM: priorityDTO = PriorityDTO.MEDIUM;
            break;
            case LOW: priorityDTO = PriorityDTO.LOW;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + priority );
        }

        context.storeMappedInstance( priority, priorityDTO );

        return priorityDTO;
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

    protected DepartmentDTO departmentToDepartmentDTO(Department department, CycleAvoidingMappingContext context) {
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

    protected StatusDTO statusToStatusDTO(Status status, CycleAvoidingMappingContext context) {
        StatusDTO target = context.getMappedInstance( status, StatusDTO.class );
        if ( target != null ) {
            return target;
        }

        if ( status == null ) {
            return null;
        }

        StatusDTO statusDTO;

        switch ( status ) {
            case OPEN: statusDTO = StatusDTO.OPEN;
            break;
            case PENDING: statusDTO = StatusDTO.PENDING;
            break;
            case IN_PROGRESS: statusDTO = StatusDTO.IN_PROGRESS;
            break;
            case COMPLETE: statusDTO = StatusDTO.COMPLETE;
            break;
            case CANCELLED: statusDTO = StatusDTO.CANCELLED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + status );
        }

        context.storeMappedInstance( status, statusDTO );

        return statusDTO;
    }

    protected TicketDTO ticketToTicketDTO(Ticket ticket, CycleAvoidingMappingContext context) {
        TicketDTO target = context.getMappedInstance( ticket, TicketDTO.class );
        if ( target != null ) {
            return target;
        }

        if ( ticket == null ) {
            return null;
        }

        String id = null;
        String title = null;
        String description = null;
        String reference = null;
        PriorityDTO priority = null;
        UserDTO assignedTo = null;
        DepartmentDTO department = null;
        CategoryDTO category = null;
        Integer estimation = null;
        LocalDateTime startedOn = null;
        LocalDateTime resolvedOn = null;
        StatusDTO status = null;
        UserDTO createdBy = null;

        if ( ticket.getId() != null ) {
            id = ticket.getId().toString();
        }
        title = ticket.getTitle();
        description = ticket.getDescription();
        reference = ticket.getReference();
        priority = priorityToPriorityDTO( ticket.getPriority(), context );
        assignedTo = userToUserDTO( ticket.getAssignedTo(), context );
        department = departmentToDepartmentDTO( ticket.getDepartment(), context );
        category = entityToDTO( ticket.getCategory(), context );
        estimation = ticket.getEstimation();
        startedOn = ticket.getStartedOn();
        resolvedOn = ticket.getResolvedOn();
        status = statusToStatusDTO( ticket.getStatus(), context );
        createdBy = userToUserDTO( ticket.getCreatedBy(), context );

        TicketDTO ticketDTO = new TicketDTO( id, title, description, reference, priority, assignedTo, department, category, estimation, startedOn, resolvedOn, status, createdBy );

        context.storeMappedInstance( ticket, ticketDTO );

        return ticketDTO;
    }

    protected Set<TicketDTO> ticketSetToTicketDTOSet(Set<Ticket> set, CycleAvoidingMappingContext context) {
        Set<TicketDTO> target = context.getMappedInstance( set, Set.class );
        if ( target != null ) {
            return target;
        }

        if ( set == null ) {
            return null;
        }

        Set<TicketDTO> set1 = new LinkedHashSet<TicketDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        context.storeMappedInstance( set, set1 );

        for ( Ticket ticket : set ) {
            set1.add( ticketToTicketDTO( ticket, context ) );
        }

        return set1;
    }

    protected Set<DepartmentDTO> departmentSetToDepartmentDTOSet(Set<Department> set, CycleAvoidingMappingContext context) {
        Set<DepartmentDTO> target = context.getMappedInstance( set, Set.class );
        if ( target != null ) {
            return target;
        }

        if ( set == null ) {
            return null;
        }

        Set<DepartmentDTO> set1 = new LinkedHashSet<DepartmentDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        context.storeMappedInstance( set, set1 );

        for ( Department department : set ) {
            set1.add( departmentToDepartmentDTO( department, context ) );
        }

        return set1;
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

    protected Priority priorityDTOToPriority(PriorityDTO priorityDTO) {
        if ( priorityDTO == null ) {
            return null;
        }

        Priority priority;

        switch ( priorityDTO ) {
            case HIGH: priority = Priority.HIGH;
            break;
            case MEDIUM: priority = Priority.MEDIUM;
            break;
            case LOW: priority = Priority.LOW;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + priorityDTO );
        }

        return priority;
    }

    protected Department departmentDTOToDepartment(DepartmentDTO departmentDTO) {
        if ( departmentDTO == null ) {
            return null;
        }

        Department department = new Department();

        if ( departmentDTO.getId() != null ) {
            department.setId( UUID.fromString( departmentDTO.getId() ) );
        }
        department.setCreatedAt( departmentDTO.getCreatedAt() );
        department.setCreatedBy( userDTOToUser( departmentDTO.getCreatedBy() ) );
        department.setName( departmentDTO.getName() );
        Set<Category> set = departmentDTO.getCategories();
        if ( set != null ) {
            department.setCategories( new LinkedHashSet<Category>( set ) );
        }
        Set<User> set1 = departmentDTO.getUsers();
        if ( set1 != null ) {
            department.setUsers( new LinkedHashSet<User>( set1 ) );
        }
        Set<Ticket> set2 = departmentDTO.getTickets();
        if ( set2 != null ) {
            department.setTickets( new LinkedHashSet<Ticket>( set2 ) );
        }

        return department;
    }

    protected Status statusDTOToStatus(StatusDTO statusDTO) {
        if ( statusDTO == null ) {
            return null;
        }

        Status status;

        switch ( statusDTO ) {
            case OPEN: status = Status.OPEN;
            break;
            case PENDING: status = Status.PENDING;
            break;
            case IN_PROGRESS: status = Status.IN_PROGRESS;
            break;
            case COMPLETE: status = Status.COMPLETE;
            break;
            case CANCELLED: status = Status.CANCELLED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + statusDTO );
        }

        return status;
    }

    protected Ticket ticketDTOToTicket(TicketDTO ticketDTO) {
        if ( ticketDTO == null ) {
            return null;
        }

        Ticket ticket = new Ticket();

        if ( ticketDTO.id() != null ) {
            ticket.setId( UUID.fromString( ticketDTO.id() ) );
        }
        ticket.setCreatedBy( userDTOToUser( ticketDTO.createdBy() ) );
        ticket.setTitle( ticketDTO.title() );
        ticket.setDescription( ticketDTO.description() );
        ticket.setReference( ticketDTO.reference() );
        ticket.setPriority( priorityDTOToPriority( ticketDTO.priority() ) );
        ticket.setAssignedTo( userDTOToUser( ticketDTO.assignedTo() ) );
        ticket.setDepartment( departmentDTOToDepartment( ticketDTO.department() ) );
        ticket.setCategory( dtoToEntity( ticketDTO.category() ) );
        ticket.setEstimation( ticketDTO.estimation() );
        ticket.setStartedOn( ticketDTO.startedOn() );
        ticket.setResolvedOn( ticketDTO.resolvedOn() );
        ticket.setStatus( statusDTOToStatus( ticketDTO.status() ) );

        return ticket;
    }

    protected Set<Ticket> ticketDTOSetToTicketSet(Set<TicketDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Ticket> set1 = new LinkedHashSet<Ticket>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TicketDTO ticketDTO : set ) {
            set1.add( ticketDTOToTicket( ticketDTO ) );
        }

        return set1;
    }

    protected Set<Department> departmentDTOSetToDepartmentSet(Set<DepartmentDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Department> set1 = new LinkedHashSet<Department>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( DepartmentDTO departmentDTO : set ) {
            set1.add( departmentDTOToDepartment( departmentDTO ) );
        }

        return set1;
    }
}
