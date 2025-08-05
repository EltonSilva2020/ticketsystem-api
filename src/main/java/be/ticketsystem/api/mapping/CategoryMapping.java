package be.ticketsystem.api.mapping;

import be.ticketsystem.api.dto.CategoryDTO;
import be.ticketsystem.api.model.Category;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapping {

  CategoryMapping INSTANCE_CATEGORY = Mappers.getMapper(CategoryMapping.class);

  CategoryDTO entityToDTO(Category category, @Context CycleAvoidingMappingContext context);

  // Set<CategoryDTO> entityToDTO(Set<Category> categories);

  Category dtoToEntity(CategoryDTO category);

  //  @AfterMapping
  //  default void ignoreFathersChildren(Category category, @MappingTarget CategoryDTO categoryDTO)
  // {
  //    categoryDTO.setTickets(null);
  //    categoryDTO.setDepartments(null);
  //  }
}
