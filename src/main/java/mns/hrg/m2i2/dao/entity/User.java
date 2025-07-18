package mns.hrg.m2i2.dao.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "The psoeudo can't be blank.")
    @NotNull(message = "The psoeudo can't be null.")
    @Column(unique = true)
    private String psoeudo;

    @NotNull(message = "The password can't be null.")
    @NotBlank(message = "The password can't be blank.")
    @Size(min = 8, max = 60, message = "The password should be between 8 to 60 characters.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).+$",
            message = "The password must contain an uppercase letter, a lowercase letter, a digit, and a special character."
    )
    private String password;

    @ManyToOne(optional = false)
    @JsonView()
    protected Role role;

    private boolean isAdmin;

    @OneToMany(mappedBy = "submitter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("ticketsSubmited")
    private List<Ticket> ticketsSubmited;

    @OneToMany(mappedBy = "solver", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("ticketsSolved")
    private List<Ticket> ticketsSolved;
}
