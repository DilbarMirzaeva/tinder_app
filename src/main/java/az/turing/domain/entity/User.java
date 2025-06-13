package az.turing.domain.entity;

import az.turing.domain.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@SuperBuilder
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

    @Column(nullable = false)
    private Status status;

}
