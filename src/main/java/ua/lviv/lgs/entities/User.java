package ua.lviv.lgs.entities;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String role;
    private String password;
    private String username;
    private String bio;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String address;
    private int age;
    private String gender;
    private String profession;

    public User(){
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String email;
        private String firstName;
        private String lastName;
        private String role;
        private String password;
        private String username;
        private String bio;
        private String phoneNumber;
        private String address;
        private int age;
        private String gender;
        private String profession;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setRole(String role) {
            this.role = role;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setBio(String bio) {
            this.bio = bio;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder setProfession(String profession) {
            this.profession = profession;
            return this;
        }

        public User build() {
            User user = new User();
            user.setId(id);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setRole(role);
            user.setPassword(password);
            user.setUsername(username);
            user.setBio(bio);
            user.setPhoneNumber(phoneNumber);
            user.setAddress(address);
            user.setAge(age);
            user.setGender(gender);
            user.setProfession(profession);

            return user;
        }
    }

    public static User of(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String role = resultSet.getString("role");
            String password = resultSet.getString("password");
            String username = resultSet.getString("username") == null ? "" : resultSet.getString("username");
            String bio = resultSet.getString("bio") == null ? "" : resultSet.getString("bio");
            String phoneNumber = resultSet.getString("phone_number") == null ? "" : resultSet.getString("phone_number");
            String address = resultSet.getString("address") == null ? "" : resultSet.getString("address");
            int age = resultSet.getInt("age");
            String gender = resultSet.getString("gender") == null ? "" : resultSet.getString("gender");
            String profession = resultSet.getString("profession") == null ? "" : resultSet.getString("profession");

            return User.builder()
                    .setId(id)
                    .setEmail(email)
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setRole(role)
                    .setPassword(password)
                    .setUsername(username)
                    .setBio(bio)
                    .setPhoneNumber(phoneNumber)
                    .setAddress(address)
                    .setAge(age)
                    .setGender(gender)
                    .setProfession(profession)
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
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
                ", username='" + username + '\'' +
                ", bio='" + bio + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", profession='" + profession + '\'' +
                '}';
    }
}
