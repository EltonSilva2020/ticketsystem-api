package be.ticketsystem.api.dto.type;

import be.ticketsystem.api.dto.TicketDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class UserDTO {

    @NotNull
    private String id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Size(max = 255)
    @UniqueElements
    @Email(message = "Email should be valid.")
    private String email;

    private List<TicketDTO> ticketsAssignedToMe;

    // Construtor
    public UserDTO(String id, String name, String email, List<TicketDTO> ticketsAssignedToMe) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.ticketsAssignedToMe = ticketsAssignedToMe;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TicketDTO> getTicketsAssignedToMe() {
        return ticketsAssignedToMe;
    }

    public void setTicketsAssignedToMe(List<TicketDTO> ticketsAssignedToMe) {
        this.ticketsAssignedToMe = ticketsAssignedToMe;
    }
}