package user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String password;
}
