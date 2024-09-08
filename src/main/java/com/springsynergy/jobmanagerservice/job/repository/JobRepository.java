package com.springsynergy.jobmanagerservice.job.repository;

import com.springsynergy.jobmanagerservice.job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID>, JobRepositoryCustom {

}
