package mns.hrg.m2i2.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotNull(message = "The title can't be null.")
    @NotBlank(message = "The title can't be blank.")
    private String title;

    @NotBlank(message = "The description can't be blank.")
    private String description;

    private boolean isSolved;

    @ManyToOne
    @JoinColumn(name = "submitter_id")
    @JsonBackReference("ticketsSubmited")
    private User submitter;

    @ManyToOne
    @JoinColumn(name = "solver_id")
    @JsonBackReference("ticketsSolved")
    private User solver;

    @ManyToMany
    @JoinTable(
            name = "ticket_category",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @ManyToOne
    @JoinColumn(name = "priority_id")
    @JsonBackReference
    private Priority priority;
}
