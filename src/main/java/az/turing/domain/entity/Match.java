    package az.turing.domain.entity;

    import jakarta.persistence.*;
    import jakarta.validation.constraints.NotNull;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.time.LocalDateTime;

    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Table(name = "matches")
    @Builder
    public class Match {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq")
        @SequenceGenerator(name = "seq",sequenceName = "seq", allocationSize = 1)
        private Long id;


        @ManyToOne(optional = false)
        @JoinColumn(name = "user1_id")
        private User user1;

        @ManyToOne(optional = false)
        @JoinColumn(name = "user2_id")
        private User user2;

        @NotNull
        private LocalDateTime matchedAt;

    }
