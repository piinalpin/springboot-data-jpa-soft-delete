package com.piinalpin.customsoftdeletes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.piinalpin.customsoftdeletes.entity.base.BaseEntityWithDeletedAt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "M_BOOK_DETAIL")
public class BookDetail extends BaseEntityWithDeletedAt {

    private static final long serialVersionUID = -4930414280222129820L;

    /**
     * @Id column should exists for one to one relationship
     */
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @OneToOne(mappedBy = "detail")
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "page", nullable = false)
    private Integer page;

    @Column(name = "weight", nullable = false)
    private Integer weight;

}
