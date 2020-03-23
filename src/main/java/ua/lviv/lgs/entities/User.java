package ua.lviv.lgs.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class User {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private String password;

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String email;
        private String firstName;
        private String lastName;
        private String role;
        private String password;

        public Builder setId(int id){
            this.id = id;
            return this;
        }

        public Builder setEmail(String email){
            this.email = email;
            return this;
        }

        public Builder setFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public Builder setRole(String role){
            this.role = role;
            return this;
        }

        public Builder setPassword(String password){
            this.password = password;
            return this;
        }

        public User build(){
            User user = new User();
            user.setId(id);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setRole(role);
            user.setPassword(password);

            return user;
        }
    }

    public static User of(ResultSet resultSet){
        try {
            int id = resultSet.getInt("id");
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String role = resultSet.getString("role");
            String password = resultSet.getString("password");

            return User.builder()
                    .setId(id)
                    .setEmail(email)
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setRole(role)
                    .setPassword(password)
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't create user");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
