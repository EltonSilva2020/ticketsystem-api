package be.congregationchretienne.ticketsystem.api.dto;

import be.congregationchretienne.ticketsystem.api.model.Category;
import be.congregationchretienne.ticketsystem.api.model.Ticket;
import be.congregationchretienne.ticketsystem.api.model.User;
import java.time.LocalDateTime;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepartmentDTO {

  @NotNull String id;

  @NotNull
  @Size(max = 255)
  String name;

  Set<Category> categories;
  Set<User> users;
  Set<Ticket> tickets;
  LocalDateTime createdAt;
  UserDTO createdBy;
}
