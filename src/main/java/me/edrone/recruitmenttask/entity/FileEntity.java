package me.edrone.recruitmenttask.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "files")
@Getter
@Setter
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String availableChars;

    private int minLengthOfTargetString;

    private int maxLengthOfTargetString;

    @Column(nullable = false)
    private int numberOfTargetStrings;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<StringEntity> setOfStrings;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FileEntity that = (FileEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}