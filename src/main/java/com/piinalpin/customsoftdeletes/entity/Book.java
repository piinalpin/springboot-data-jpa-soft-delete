package com.piinalpin.customsoftdeletes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.piinalpin.customsoftdeletes.entity.base.BaseEntityWithDeletedAt;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "M_BOOK")
public class Book extends BaseEntityWithDeletedAt {

    private static final long serialVersionUID = 3000665212891573963L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Generated by database book ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    @ApiModelProperty(notes = "Author object")
    private Author author;

    @Column(name = "title", nullable = false)
    @ApiModelProperty(notes = "The book title")
    private String title;

    @Column(name = "price", nullable = false)
    @ApiModelProperty(notes = "The book price")
    private Integer price;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private BookDetail detail;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "book")
    private List<TransactionDetail> transactionDetails;

}
