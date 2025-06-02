package in.project.billingsoftware.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

//@Entity annotation defines that a class can be mapped to a table.It is just like a marker
@Entity
//The @Table annotation in Spring Boot is used to specify the details of the database table that corresponds to an entity class. It is part of the Java Persistence API (JPA) and is crucial for mapping Java objects to database tables.
@Table(name = "tbl_category")
//The @Builder annotation, provided by the Lombok library, can simplify object creation, reduce boilerplate code, and enhance the readability of your Java code.
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String categoryId;
    @Column(unique = true)
    private String name;
    private String description;
    private String bgColor;
    private String imgUrl;
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;


}
