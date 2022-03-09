import D.MyConnection.SessionFactorySingleton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ConnectionTest {

    @Test
    public void testConnection() {
        assertDoesNotThrow(() -> SessionFactorySingleton.getInstance());
    }
}
