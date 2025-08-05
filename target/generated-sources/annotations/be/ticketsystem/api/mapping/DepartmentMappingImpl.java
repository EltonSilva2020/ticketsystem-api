package be.ticketsystem.api.mapping;

import be.ticketsystem.api.dto.CategoryDTO;
import be.ticketsystem.api.dto.DepartmentDTO;
import be.ticketsystem.api.dto.TicketDTO;
import be.ticketsystem.api.dto.type.PriorityDTO;
import be.ticketsystem.api.dto.type.StatusDTO;
import be.ticketsystem.api.dto.type.UserDTO;
import be.ticketsystem.api.model.Category;
import be.ticketsystem.api.model.Department;
import be.ticketsystem.api.model.Ticket;
import be.ticketsystem.api.model.User;
import be.ticketsystem.api.model.type.Priority;
import be.ticketsystem.api.model.type.Status;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
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
            set1.add( entityToDTO( department, context ) );
        }

        return set1;
    }

    protected CategoryDTO categoryToCategoryDTO(Category category, CycleAvoidingMappingContext context) {
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
        department = entityToDTO( ticket.getDepartment(), context );
        category = categoryToCategoryDTO( ticket.getCategory(), context );
        estimation = ticket.getEstimation();
        startedOn = ticket.getStartedOn();
        resolvedOn = ticket.getResolvedOn();
        status = statusToStatusDTO( ticket.getStatus(), context );
        createdBy = userToUserDTO( ticket.getCreatedBy(), context );

        TicketDTO ticketDTO = new TicketDTO( id, title, description, reference, priority, assignedTo, department, category, estimation, startedOn, resolvedOn, status, createdBy );

        context.storeMappedInstance( ticket, ticketDTO );

        return ticketDTO;
    }

    protected List<TicketDTO> ticketSetToTicketDTOList(Set<Ticket> set, CycleAvoidingMappingContext context) {
        List<TicketDTO> target = context.getMappedInstance( set, List.class );
        if ( target != null ) {
            return target;
        }

        if ( set == null ) {
            return null;
        }

        List<TicketDTO> list = new ArrayList<TicketDTO>( set.size() );
        context.storeMappedInstance( set, list );

        for ( Ticket ticket : set ) {
            list.add( ticketToTicketDTO( ticket, context ) );
        }

        return list;
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
        List<TicketDTO> ticketsAssignedToMe = null;

        if ( user.getId() != null ) {
            id = user.getId().toString();
        }
        name = user.getName();
        email = user.getEmail();
        ticketsAssignedToMe = ticketSetToTicketDTOList( user.getTicketsAssignedToMe(), context );

        UserDTO userDTO = new UserDTO( id, name, email, ticketsAssignedToMe );

        context.storeMappedInstance( user, userDTO );

        return userDTO;
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
            set1.add( dtoToEntity( departmentDTO ) );
        }

        return set1;
    }

    protected Category categoryDTOToCategory(CategoryDTO categoryDTO) {
        if ( categoryDTO == null ) {
            return null;
        }

        Category category = new Category();

        if ( categoryDTO.getId() != null ) {
            category.setId( UUID.fromString( categoryDTO.getId() ) );
        }
        category.setCreatedAt( categoryDTO.getCreatedAt() );
        category.setCreatedBy( userDTOToUser( categoryDTO.getCreatedBy() ) );
        category.setName( categoryDTO.getName() );
        category.setTickets( ticketDTOSetToTicketSet( categoryDTO.getTickets() ) );
        category.setDepartments( departmentDTOSetToDepartmentSet( categoryDTO.getDepartments() ) );

        return category;
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
        ticket.setDepartment( dtoToEntity( ticketDTO.department() ) );
        ticket.setCategory( categoryDTOToCategory( ticketDTO.category() ) );
        ticket.setEstimation( ticketDTO.estimation() );
        ticket.setStartedOn( ticketDTO.startedOn() );
        ticket.setResolvedOn( ticketDTO.resolvedOn() );
        ticket.setStatus( statusDTOToStatus( ticketDTO.status() ) );

        return ticket;
    }

    protected Set<Ticket> ticketDTOListToTicketSet(List<TicketDTO> list) {
        if ( list == null ) {
            return null;
        }

        Set<Ticket> set = new LinkedHashSet<Ticket>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( TicketDTO ticketDTO : list ) {
            set.add( ticketDTOToTicket( ticketDTO ) );
        }

        return set;
    }

    protected User userDTOToUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        if ( userDTO.getId() != null ) {
            user.setId( UUID.fromString( userDTO.getId() ) );
        }
        user.setName( userDTO.getName() );
        user.setEmail( userDTO.getEmail() );
        user.setTicketsAssignedToMe( ticketDTOListToTicketSet( userDTO.getTicketsAssignedToMe() ) );

        return user;
    }
}
