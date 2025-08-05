package be.congregationchretienne.ticketsystem.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

public record UserDTO(
    @NotNull String id,
    @NotNull @Size(max = 255) String name,
    @NotNull @Size(max = 255) @UniqueElements @Email(message = "Email should be valid.")
        String email) {}
@JsonIgnore
private List<TicketDTO> ticketsAssignedToMe;
}
