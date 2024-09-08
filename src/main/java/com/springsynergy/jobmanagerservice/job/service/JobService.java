package com.springsynergy.jobmanagerservice.job.service;

import com.springsynergy.jobmanagerservice.job.model.JobDto;

import java.util.Set;
import java.util.UUID;

public interface JobService {

    Set<JobDto> getAllJobs();

    JobDto getJobById(UUID id);

    void createJob(JobDto jobDto);

    JobDto getJobByTitle(String jobTitle);

    boolean deleteJobById(UUID jobId);

    boolean updateJobById(UUID jobId, JobDto jobDto);
}
