package com.reon.ingestservice.repository;

import com.reon.ingestservice.model.RawEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawEventRepository extends JpaRepository<RawEvent,Long> {
}
