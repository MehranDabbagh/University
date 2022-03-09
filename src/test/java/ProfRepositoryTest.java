import D.Entities.Course;
import D.Entities.Prof;
import D.Repository.Impl.ProfRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfRepositoryTest {
    private ProfRepositoryImpl profRepository = new ProfRepositoryImpl();
    ;

    @BeforeAll
    public static void setup() {

    }

    @BeforeEach
    public void beforeEach() {

    }

    @AfterEach
    public void cleanUp() {
        List<Prof> profs = profRepository.findAll();
        if (profs != null) {
            for (Prof prof : profs
            ) {
                prof.setCourse(null);
                profRepository.delete(prof);
            }
        }
    }

    @Test
    public void CreateTest() {
        // Arrange -->

        Prof prof = new Prof("heyatelmi", null);
        // Act
        Integer accId = profRepository.save(prof).getId();

        // Assert
        Prof loadedProf = profRepository.findById(accId);
        assertAll(
                () -> assertNotNull(accId),
                () -> assertNotNull(loadedProf),
                () -> assertEquals(accId, loadedProf.getId()),
                () -> assertEquals("heyatelmi", loadedProf.getType()),
                () -> assertEquals(0, loadedProf.getCourse().size()),
                () -> assertEquals(accId, loadedProf.getId())
        );
    }

    @Test
    public void findAllTest() throws SQLException {
        // Arrange
        profRepository.save( new Prof("heyatelmi", null));
        profRepository.save( new Prof("hagholtadris", null));
        profRepository.save( new Prof("hagholtadris", null));



        // Act
        List<Prof> profs = profRepository.findAll();

        // Assert
        assertThat(profs).hasSize(3);

    }

    @Test
    public void updateTest() throws SQLException {
        // Arrange
        Integer accId = profRepository.save(new Prof("heyatelmi", null)).getId();
        Prof prof = profRepository.findById(accId);
        // Act
        prof.setType("newOne");
        profRepository.update(prof);
        // Assert
        Prof loadedProf = profRepository.findById(accId);
        assertThat(loadedProf.getType()).isEqualTo("newOne");
        assertThat(loadedProf.getId()).isEqualTo(accId);
    }

    @Test
    public void deleteTest() throws SQLException {
        // Arrange
        Prof prof =new Prof("heyatelmi", null);
        profRepository.save(prof);
        // Act
        profRepository.delete(prof);
        // Assert
        assertThat(profRepository.findAll()).isEmpty();
    }


}
