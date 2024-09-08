package com.springsynergy.jobmanagerservice.job.repository;

import com.springsynergy.jobmanagerservice.job.entity.Job;

import java.util.Optional;

public interface JobRepositoryCustom {
    Optional<Job> findByJobTitle(String jobTitle);
}
