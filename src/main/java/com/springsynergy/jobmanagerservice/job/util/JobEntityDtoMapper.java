package com.springsynergy.jobmanagerservice.job.util;

import com.springsynergy.jobmanagerservice.job.entity.Job;
import com.springsynergy.jobmanagerservice.job.model.JobDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobEntityDtoMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public JobDto jobToJobDto(Job job) {

        return modelMapper.map(job, JobDto.class);
    }

    public Job jobDtoToJob(JobDto jobDto) {
        return modelMapper.map(jobDto, Job.class);
    }
}


