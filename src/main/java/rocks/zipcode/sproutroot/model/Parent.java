package rocks.zipcode.sproutroot.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity // this tells the database this is a database model
@Table(name = "parent") // names the table parent in postgresql.
public class Parent {
    @Id //primary key field
    @GeneratedValue(strategy = GenerationType.UUID) //postgresql generated uuid for every row automatically.
    private UUID id; 

    @Column(nullable = false, unique = true)
    private String email; // this makes sure no two parent can have the same email.
    
    @Column(nullable = false)
    private String passwordhash; // this is the hashed password, not the plain text password.

    @Column(nullable = false)
    private String name; // this is the name of the parent.

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // this is when the parent was created.

    @OneToMany(mappedBy = "parent", cascade = CascadeType.All, orphanRemoval = true)
    private List<Child> children; // this is the list of children belonging to the parent.
     

    //getters and setters

    public Parent() {
        public UUID getId() { return id; }

        public String getEmail() { return email; }
        public void setEmail(String email) {this.email = email;}

        public String getPasswordhash() { return passwordhash; }
        public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }


        public LocalDateTime getCreatedAt() { return createdAt; }

        public List<Child> getChildren() { return children; }
        public void setChildren(List<Child> children) { this.children = children;}
    }
}