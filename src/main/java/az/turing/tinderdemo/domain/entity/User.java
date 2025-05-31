package az.turing.tinderdemo.domain.entity;

import az.turing.tinderdemo.domain.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq")
    @SequenceGenerator(name = "seq",sequenceName = "seq", allocationSize = 1)
    private Long id;

    @Column(unique=true,nullable=false)
    private String username;

    @Email
    @Column(unique=true,nullable=false)
    private String email;

    @Column(unique=true,nullable=false)
    private String password;

    private String bio;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Gender gender;

}
