package com.reon.ingestservice.service.impl;

import com.reon.ingestservice.dto.DeploymentRequest;
import com.reon.ingestservice.dto.response.DeploymentResponse;
import com.reon.ingestservice.mapper.DeploymentMapper;
import com.reon.ingestservice.model.Deployment;
import com.reon.ingestservice.repository.DeploymentRepository;
import com.reon.ingestservice.service.DeploymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;

@Service
@Slf4j
public class DeploymentServiceImpl implements DeploymentService {

    private final DeploymentRepository deploymentRepository;
    private final DeploymentMapper deploymentMapper;
    private final Clock clock;

    public DeploymentServiceImpl(DeploymentRepository deploymentRepository, DeploymentMapper deploymentMapper, Clock clock) {
        this.deploymentRepository = deploymentRepository;
        this.deploymentMapper = deploymentMapper;
        this.clock = clock;
    }

    @Override
    public DeploymentResponse record(DeploymentRequest request) {
        log.info("Processing new deployment request..");

        Instant deployedAt = request.deployedAt() == null ? clock.instant() : request.deployedAt();

        Deployment deployment = deploymentMapper.mapToEntity(request, deployedAt);
        Deployment save = deploymentRepository.save(deployment);

        log.info("Deployment saved for service: {}, version: {} & time: {}", save.getServiceName(), save.getVersion(), save.getDeployedAt());

        return deploymentMapper.toResponse(save);
    }
}
