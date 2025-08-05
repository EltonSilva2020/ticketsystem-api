package be.congregationchretienne.ticketsystem.api.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import be.congregationchretienne.ticketsystem.api.exception.NotFoundException;
import be.congregationchretienne.ticketsystem.api.service.TicketService;
import java.util.UUID;
import java.util.stream.Stream;
import org.flywaydb.core.Flyway;
import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.junit5.annotation.FlywayTestExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@FlywayTest
@Import({TicketServiceImpl.class})
@FlywayTestExtension
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TicketServiceImplIT {

  @Autowired private TicketService ticketService;

  @Autowired private Flyway flyway;

  @BeforeEach
  public void before() throws Exception {
    // flyway.clean();
    flyway.migrate();
  }

  @Test
  void testGetTicketOk() {
    // given
    var uuid = UUID.fromString("082722c7-856f-4a39-b8dd-20cb08a6996c");

    // when
    var result = ticketService.get(uuid.toString());

    // Then
    Assertions.assertNotNull(result);
    Assertions.assertEquals(uuid.toString(), result.id());
  }

  private static Stream<Arguments> invalidIds() {
    String nullVar = null;
    return Stream.of(
        Arguments.of(nullVar, "The resource reference must be not null."),
        Arguments.of("", "The resource reference must be not null."),
        Arguments.of("  ", "The resource reference must be not null."),
        Arguments.of("123", "The resource reference is invalid."));
  }

  @ParameterizedTest
  @MethodSource("invalidIds")
  void testGetTicketWIthInvalidId(String id, String expectedMessage) {
    // given
    // when
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              ticketService.get(id);
            });

    // Then
    Assertions.assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void testGetTicketWithValidIdButNotFound() {
    // given
    // when
    Exception exception =
        assertThrows(
            NotFoundException.class,
            () -> {
              ticketService.get("082722c7-1234-4a39-b8dd-20cb08a6996c");
            });

    // Then
    Assertions.assertEquals(
        "The resource with reference [082722c7-1234-4a39-b8dd-20cb08a6996c] was not found.",
        exception.getMessage());
  }
}
