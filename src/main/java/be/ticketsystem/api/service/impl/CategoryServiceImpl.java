package be.ticketsystem.api.service.impl;

import static be.ticketsystem.api.mapping.CategoryMapping.INSTANCE_CATEGORY;

import be.ticketsystem.api.dto.CategoryDTO;
import be.ticketsystem.api.exception.NotFoundException;
import be.ticketsystem.api.mapping.CycleAvoidingMappingContext;
import be.ticketsystem.api.model.Category;
import be.ticketsystem.api.repository.CategoryRepository;
import be.ticketsystem.api.service.CategoryService;
import java.util.Optional;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Getter
@Service
public class CategoryServiceImpl extends AbstractServiceImpl implements CategoryService {

  private final CategoryRepository repository;

  public CategoryServiceImpl(CategoryRepository repository) {
    super(repository);
    this.repository = repository;
  }

  @Override
  public CategoryDTO get(String id) {
    var uuid = checkAndConvertID(id);
    Optional<Category> category = repository.findById(uuid);

    category.orElseThrow(
        () -> new NotFoundException(String.format("Category with ID [%s] not found.", id)));

    return INSTANCE_CATEGORY.entityToDTO(category.get(), new CycleAvoidingMappingContext());
  }

  @Override
  public Page<CategoryDTO> getAll(Integer page, Integer pageSize, String orderBy, String sort) {
    return null;
  }

  @Override
  public void create(CategoryDTO dto) {
    var category = INSTANCE_CATEGORY.dtoToEntity(dto);
    repository.save(category);
  }

  @Override
  public void update(CategoryDTO bean) {}

  @Override
  public void delete(String id) {}

  // Implemente outros métodos conforme necessário...
}
