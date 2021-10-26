package com.johan.project.taskapiservice.repository.entity;

import com.johan.project.taskapiservice.model.response.TaskResponse;
import com.johan.project.taskapiservice.utils.TimeUtils;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "TASKS")
@Entity(name = "TASKS")
@Setter
@EntityListeners(AuditingEntityListener.class)
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TasksSeq")
    @SequenceGenerator(name = "TasksSeq", sequenceName = "TASKS_SEQ", allocationSize = 1)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private UserEntity user;

    @CreatedDate
    @Column(name = "CREATED_DATE_TIME", nullable = false, updatable = false)
    private Timestamp createdDateTime;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE_TIME", nullable = false)
    private Timestamp lastModifiedDateTime;

    public TaskResponse toResponse() {
        return TaskResponse
                .builder()
                .title(title)
                .description(description)
                .createdTime(TimeUtils.convertTimeStampToString.apply(createdDateTime))
                .lastModifiedTime(TimeUtils.convertTimeStampToString.apply(lastModifiedDateTime))
                .build();
    }
}
