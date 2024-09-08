package com.springsynergy.jobmanagerservice.job.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Job {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false, length = 16)
    private UUID jobId;

    @Column(name = "title" , nullable = false, length = 255, unique = true)
    private String jobTitle;

    @Column(name = "description", length = 255)
    private String jobDescription;

    @Column(name = "minsalary", length = 10)
    private Long minSalary;

    @Column(name = "maxsalary", length = 10)
    private Long maxSalary;

    @Column(name = "location", length = 255)
    private String location;

    @Column(name = "company_id", nullable = false)
    private UUID companyId;

    @Column(name = "company_name", nullable = false, length = 255)
    private String companyName;

}
