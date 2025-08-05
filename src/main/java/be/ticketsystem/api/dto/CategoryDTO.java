package be.ticketsystem.api.dto;

import java.time.LocalDateTime;
import java.util.Set;
import javax.validation.constraints.NotNull;

import be.ticketsystem.api.dto.type.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryDTO {

  @NotNull String id;
  @NotNull String name;
  Set<TicketDTO> tickets;
  Set<DepartmentDTO> departments;
  LocalDateTime createdAt;
  UserDTO createdBy;
}
