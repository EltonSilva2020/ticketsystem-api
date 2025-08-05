package be.ticketsystem.api.service.impl;

import static be.ticketsystem.api.mapping.DepartmentMapping.INSTANCE_DEPARTMENT;

import be.ticketsystem.api.dto.DepartmentDTO;
import be.ticketsystem.api.exception.NotFoundException;
import be.ticketsystem.api.mapping.CycleAvoidingMappingContext;
import be.ticketsystem.api.model.Department;
import be.ticketsystem.api.repository.DepartmentRepository;
import be.ticketsystem.api.service.DepartmentService;
import java.util.Optional;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Getter
@Service
public class DepartmentServiceImpl extends AbstractServiceImpl implements DepartmentService {

  private DepartmentRepository repository;

  public DepartmentServiceImpl(DepartmentRepository repository) {
    super(repository);
  }

  @Override
  public DepartmentDTO get(String id) {
    var uuid = checkAndConvertID(id);

    Optional<Department> department = repository.findById(uuid);

    department.orElseThrow(
        () ->
            new NotFoundException(
                String.format("The department with reference [%s] was not found.", id)));

    return INSTANCE_DEPARTMENT.entityToDTO(department.get(), new CycleAvoidingMappingContext());
  }

  @Override
  public Page<DepartmentDTO> getAll(Integer page, Integer pageSize, String orderBy, String sort) {
    if (pageSize <= 0 || pageSize > 50) {
      throw new IllegalArgumentException("The number items per page should be between 1 and 50.");
    }

    var pageable = getPageable(page, pageSize, orderBy, sort);
    Page<Department> departments = repository.findAll(pageable);

    //    return ticket.map(INSTANCE_DEPARTMENT::entityToDTO);
    return departments.map(
        currentDepartment ->
            (INSTANCE_DEPARTMENT.entityToDTO(
                currentDepartment, new CycleAvoidingMappingContext())));
  }

  @Override
  public void create(DepartmentDTO bean) {
    var department = INSTANCE_DEPARTMENT.dtoToEntity(bean);

    repository.save(department);
  }

  @Override
  public void update(DepartmentDTO departmentDTO) {
    checkAndConvertID(departmentDTO.getId());

    repository.save(INSTANCE_DEPARTMENT.dtoToEntity(departmentDTO));
  }

  @Override
  public void delete(String id) {
    var uuid = checkAndConvertID(id);

    repository.deleteById(uuid);
  }
}
