package database;

import lombok.Getter;
import lombok.Setter;



public class User {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private int score;
}
