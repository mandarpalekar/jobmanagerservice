package com.springsynergy.jobmanagerservice.job.model;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class JobDto {

    private String jobTitle;
    private String jobDescription;
    private Long minSalary;
    private Long maxSalary;
    private String location;
    private UUID companyId;
    private String companyName;
}
