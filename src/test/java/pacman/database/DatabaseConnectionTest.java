package pacman.database;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
// It's not a bean, but a test with
//some helper test scope variables
@PrepareForTest({DbConnect.class})
public class DatabaseConnectionTest {


    private DbConnect dbConnect;

    private DbConnect newDbConnect;
    private Connection connection;

    @Test
    public void testDbConnection() throws Exception {
        newDbConnect = new DbConnect();
        connection = newDbConnect.getMyConnection();
        assertNotNull(connection);
    }

    @Test
    public void testDbConnectionMockito() {
        dbConnect = Mockito.mock(DbConnect.class);
        Mockito.when(dbConnect.getMyConnection()).thenReturn(connection);
    }
}
