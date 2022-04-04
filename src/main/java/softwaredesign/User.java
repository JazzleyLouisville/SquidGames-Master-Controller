package softwaredesign;

public class User {
    String name;
    private boolean isAlive;
    public User(String name) {
        this.name = name;
        this.isAlive = true;
    }
    boolean isAlive() {
        return isAlive;
    }
    void setLifeStatus(boolean isAlive) {
        this.isAlive = isAlive;
    }
}
