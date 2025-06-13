package az.turing.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Column(unique=true,nullable=false)
    private String email;

    @Column(unique=true,nullable=false)
    private String password;

    @NotNull
    @Column(nullable = false)
    private Integer age;

}
