package me.edrone.recruitmenttask.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "strings")
@Getter
@Setter
public class StringEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    private FileEntity fileEntity;
}
