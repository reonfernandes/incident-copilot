-- Runs once, on the first start of an empty pg_data volume.
-- One database per service.

create database ingest_service;
create database grouper_service;
create database agent_service;
create database dashboard_service;

-- copilot-agent stores runbook embeddings with pgvector
\connect agent_service
create extension if not exists vector;
