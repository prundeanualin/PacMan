package database;

import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.assertEquals;

@PrepareForTest({DbConnect.class})
public class DatabaseConnectionTest {


    private DbConnect dbConnect;

    private DbConnect newDbConnect;
    private Connection connection;

    @Test
    public void testDBConnection() throws Exception{
        newDbConnect = new DbConnect();
        connection = newDbConnect.getMyConnection();
        assertEquals(true, connection != null);
    }
    @Test
    public void testDBConnectionMockito()
    {
        dbConnect = Mockito.mock(DbConnect.class);
        Mockito.when(dbConnect.getMyConnection()).thenReturn(connection);
    }
}
