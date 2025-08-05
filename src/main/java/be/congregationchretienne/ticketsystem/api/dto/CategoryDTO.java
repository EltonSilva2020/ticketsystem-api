package be.congregationchretienne.ticketsystem.api.dto;

import java.time.LocalDateTime;
import java.util.Set;
import javax.validation.constraints.NotNull;
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
