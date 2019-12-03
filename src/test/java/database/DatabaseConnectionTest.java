package database;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.configuration.IMockitoConfiguration;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DbConnect.class})
public class DatabaseConnectionTest {


    @InjectMocks
    private DbConnect dbConnect;

    private Connection connection;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        connection = dbConnect.getMyConnection();
    }
    @Test
    public void testDBConnection() throws Exception{
        assertEquals(connection != null, true);
    }
    @Test
    public void testDBConnectionMockito()
    {
        Mockito.verify(dbConnect, Mockito.times(1)).getMyConnection();
    }
}
