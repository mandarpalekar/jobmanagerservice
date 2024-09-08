package com.springsynergy.jobmanagerservice.job.service;


import com.springsynergy.jobmanagerservice.job.entity.Job;
import com.springsynergy.jobmanagerservice.job.exception.JobAlreadyExistsException;
import com.springsynergy.jobmanagerservice.job.model.JobDto;
import com.springsynergy.jobmanagerservice.job.repository.JobRepository;
import com.springsynergy.jobmanagerservice.job.util.JobEntityDtoMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobEntityDtoMapper jobEntityDtoMapper;

    @Override
    public Set<JobDto> getAllJobs() {
        Set<JobDto> jobDtos = new HashSet<>();
        Set<Job> jobs = new HashSet<>(jobRepository.findAll());
        for (Job job : jobs) {
            jobDtos.add(jobEntityDtoMapper.jobToJobDto(job));
        }
        return jobDtos;
    }

    @Override
    public JobDto getJobById(UUID id) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        return jobOptional.map(jobEntityDtoMapper::jobToJobDto).orElse(null);
    }

    @Override
    public void createJob(JobDto jobDto) {
        Optional<Job> existingJob = jobRepository.findByJobTitle(jobDto.getJobTitle());
        if (existingJob.isPresent()) {
            throw new JobAlreadyExistsException("Job with title " + jobDto.getJobTitle() + " already exists.");
        } else {
            Job job = jobEntityDtoMapper.jobDtoToJob(jobDto);
            log.info("Job: {}", job);
            jobRepository.save(job);
        }
    }

    @Override
    public JobDto getJobByTitle(String jobTitle) {
        Optional<Job> jobOptional = jobRepository.findByJobTitle(jobTitle);
        return jobOptional.map(jobEntityDtoMapper::jobToJobDto).orElse(null);
    }

    @Override
    public boolean deleteJobById(UUID jobId) {
        Optional<Job> jobOptional = jobRepository.findById(jobId);
        if (jobOptional.isPresent()) {
            jobRepository.delete(jobOptional.get());
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean updateJobById(UUID jobId, JobDto jobDto) {
        Optional<Job> jobOptional = jobRepository.findById(jobId);
        if(!jobOptional.isEmpty()){
            log.info("Updating JobDto: {}", jobDto);
            Job job = jobOptional.get();
            BeanUtils.copyProperties(jobDto, job);
            log.info("Updated job: {}", job);
            try {
                jobRepository.save(job);
                return true;
            } catch (Exception e) {
                log.error("Error updating job: {}", e.getMessage());
            }
        } else {
            throw new JobAlreadyExistsException("Job with name " + jobDto.getJobTitle() + " does not exist.");
        }
        return false;
    }
}
