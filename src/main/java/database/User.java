package database;

//import lombok.Getter;
//import lombok.Setter;


public class User {

    private int id;
    private String username;
    private String password;
    private int score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return getId() == user.getId()
                && getScore() == user.getScore()
                && getUsername().equals(user.getUsername())
                && getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
