package pacman.database;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

@PrepareForTest({DbConnect.class})
@SuppressWarnings("PMD")
public class DatabaseConnectionTest {


    private DbConnect dbConnect;

    private DbConnect newDbConnect;
    private Connection connection;

    @Test
    public void testdbconnection() throws Exception {
        newDbConnect = new DbConnect();
        connection = newDbConnect.getMyConnection();
        assertNotNull(connection);
    }

    @Test
    public void testdbconnectionmockito() {
        dbConnect = Mockito.mock(DbConnect.class);
        Mockito.when(dbConnect.getMyConnection()).thenReturn(connection);
    }
}
