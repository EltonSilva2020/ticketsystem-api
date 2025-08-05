package be.ticketsystem.api.service.impl;

import be.ticketsystem.api.exception.NotFoundException;
import be.ticketsystem.api.helper.ValidationHelper;
import be.ticketsystem.api.repository.AbstractRepository;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
public abstract class AbstractServiceImpl {

  protected AbstractRepository repository;

  protected final UUID checkAndConvertID(String id) {
    if (!ValidationHelper.isNotBlank(id)) {
      throw new IllegalArgumentException("The resource reference must be not null.");
    }

    UUID uuid = null;
    try {
      uuid = UUID.fromString(id);
    } catch (Exception exception) {
      throw new IllegalArgumentException("The resource reference is invalid.");
    }

    if (!repository.existsById(uuid)) {
      throw new NotFoundException(
          String.format("The resource with reference [%s] was not found.", uuid));
    }
    return uuid;
  }

  protected final Pageable getPageable(
      Integer page, Integer pageSize, String orderBy, String sort) {
    Pageable pageable = PageRequest.of(page, pageSize);

    if (!ValidationHelper.isNotBlank(orderBy)) {
      try {
        pageable =
            PageRequest.of(page, pageSize).withSort(Sort.by(getSortDirection(sort), orderBy));
      } catch (Exception e) {
        throw new IllegalArgumentException(
            String.format("The parameter orderBy [%s] is invalid.", orderBy));
      }
    }

    return pageable;
  }

  protected final Sort.Direction getSortDirection(String sort) {
    if (ValidationHelper.isNotBlank(sort)) {
      try {
        return Sort.Direction.fromString(sort);
      } catch (Exception exception) {
        return Sort.Direction.ASC;
      }
    }
    return Sort.Direction.ASC;
  }
}
