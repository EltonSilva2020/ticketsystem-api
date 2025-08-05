package be.congregationchretienne.ticketsystem.api.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class Department extends AbstractModelForCreatedBy {

  public static final long serialVersionUID = 1L;

  @Column(name = "name", length = 255, nullable = false)
  private String name;

  @ManyToMany
  @JoinTable(
      name = "departments_categories",
      joinColumns = @JoinColumn(name = "department_fk", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "category_fk", referencedColumnName = "id"))
  private Set<Category> categories = new HashSet<>();

  @ManyToMany
  @JoinTable(
      name = "departments_users",
      joinColumns = @JoinColumn(name = "department_fk", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "user_fk", referencedColumnName = "id"))
  private Set<User> users = new HashSet<>();

  @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
  private Set<Ticket> tickets = new HashSet<>();

  public Department(String name) {
    this.name = name;
  }
}
