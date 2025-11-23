package com.example.UserService.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
// its also for auditing purposes ; we have already added annotation at the main class, the changes that makes in the entity , this annotation will just listen & monitor the changes
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
// here we are not gonna create object for it; so marking it as abstract
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date lastModifiedAt;
}


/*
 * A note on how to enable auditing features (createdAt, lastModifiedAt by default
 *
 * 1. Go to main class & add @EnableJpaAuditing
 * 2. Then to this class add @EntityListeners(AuditingEntityListener.class)
 * 3. Then for the respective attributes add the respective annotations like @CreatedDate , @LastModifiedDate
 *
 * If we manually update any row in the table, lastModifiedAt won't be changed, because it's done by jpa, so we need to change it by using jpa to see the changes visible.
 * */