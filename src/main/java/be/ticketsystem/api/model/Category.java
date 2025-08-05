package be.ticketsystem.api.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends AbstractModelForCreatedBy {

  public static final long serialVersionUID = 1L;

  @Column(name = "name", nullable = false)
  private String name;

  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
  private Set<Ticket> tickets = new HashSet<>();

  @ManyToMany(mappedBy = "categories")
  private Set<Department> departments = new HashSet<>();

  public Category(String name) {
    this.name = name;
  }
}
