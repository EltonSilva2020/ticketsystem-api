package be.congregationchretienne.ticketsystem.api.service.impl;

import static be.congregationchretienne.ticketsystem.api.mapping.UserMapping.INSTANCE_USER;

import be.congregationchretienne.ticketsystem.api.dto.UserDTO;
import be.congregationchretienne.ticketsystem.api.exception.NotFoundException;
import be.congregationchretienne.ticketsystem.api.helper.ValidationHelper;
import be.congregationchretienne.ticketsystem.api.mapping.CycleAvoidingMappingContext;
import be.congregationchretienne.ticketsystem.api.model.User;
import be.congregationchretienne.ticketsystem.api.repository.UserRepository;
import be.congregationchretienne.ticketsystem.api.service.UserService;
import java.util.Optional;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Getter
@Transactional
@Service
public class UserServiceImpl extends AbstractServiceImpl implements UserService {

  private UserRepository repository;

  public UserServiceImpl(UserRepository repository) {
    super(repository);
  }

  @Override
  @Transactional(readOnly = true)
  public UserDTO get(String id) {
    var uuid = checkAndConvertID(id);

    Optional<User> user = repository.findById(uuid);

    user.orElseThrow(
        () ->
            new NotFoundException(
                String.format("The user with reference [%s] was not found.", id)));

    return INSTANCE_USER.entityToDTO(user.get(), new CycleAvoidingMappingContext());
  }

  @Override
  @Transactional(readOnly = true)
  public Page<UserDTO> getAll(Integer page, Integer pageSize, String orderBy, String sort) {

    Pageable pageable = null;

    if (pageSize <= 0 || pageSize > 50) {
      throw new IllegalArgumentException("The number items per page should be between 1 and 50.");
    }
    if (null == orderBy || orderBy.isEmpty()) {
      pageable = PageRequest.of(page, pageSize);

    } else {

      try {
        pageable = PageRequest.of(page, pageSize).withSort(Sort.by(orderBy));

      } catch (Exception e) {
        throw new IllegalArgumentException(
            String.format("The parameter %s is invalid.", orderBy.toString()));
      }
    }

    Page<User> user = repository.findAll(pageable);
    ValidationHelper.requireNonNull(user);

    //    return user.map(INSTANCE_USER::entityToDTO);
    return user.map(
        currentUser -> (INSTANCE_USER.entityToDTO(currentUser, new CycleAvoidingMappingContext())));
  }

  @Override
  public void create(UserDTO bean) {
    User user = INSTANCE_USER.dtoToEntity(bean);

    repository.save(user);
  }

  @Override
  public void update(UserDTO userDTO) {
    checkAndConvertID(userDTO.id());

    repository.save(INSTANCE_USER.dtoToEntity(userDTO));
  }

  @Override
  public void delete(String id) {
    var uuid = checkAndConvertID(id);

    repository.deleteById(uuid);
  }
}
