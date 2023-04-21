package code.with.me.security;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author andong@xiaomalixing.com
 */

@Data
@Entity
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @JoinColumn(name = "user")
    @ManyToOne
    private User user;
}
