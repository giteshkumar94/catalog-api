package com.omnirio.catalog.model;

import com.omnirio.catalog.auditing.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "categories")
public class Category extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )

    // @Fetch(FetchMode.SELECT)
    private List<Attribute> attributes = new ArrayList<>();

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
        attribute.setCategory(this);
    }

    public void removeAttribute(Attribute attribute) {
        attributes.remove(attribute);
        attribute.setCategory(null);
    }

}