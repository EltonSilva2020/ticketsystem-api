package be.congregationchretienne.ticketsystem.api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.UUID;

public class AbstractModelTest {

    @Test
    public void testIdAssignment() {
        TestModel model = new TestModel();
        UUID uuid = UUID.randomUUID();
        model.setId(uuid);
        assertEquals(uuid, model.getId());
    }

    @Test
    public void testCreatedAtISSetOnPersist() {
        TestModel model = new TestModel();
        model.persisteDate();
        assertNotNull(model.getCreatedAt());
        assertTrue(model.getCreatedAt().isBefore(LocalDateTime.now().plusSeconds(1)));
    }

    @Test
    public void testEqualsAndHashCode() {
        UUID uuid = UUID.randomUUID();

        TestModel model1 = new TestModel();
        model1.setId(uuid);

        TestModel model2 = new TestModel();
        model2.setId(uuid);

        assertEquals(model1, model2);
        assertEquals(model1.hashCode(), model2.hashCode());
    }

    @Test
    public void testNotEqualsDifferentId() {
        TestModel model1 = new TestModel();
        model1.setId(UUID.randomUUID());

        TestModel model2 = new TestModel();
        model2.setId(UUID.randomUUID());

        assertNotEquals(model1, model2);
    }
}
