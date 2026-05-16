package com.nexusHr.leaveManagementService.entity;

public class LeaveResponse {

    private Long id;
    private LeaveStatus status;

    public LeaveResponse(LeaveRequest entity) {
        this.id = entity.getId();
        this.status = entity.getStatus();
    }

    public Long getId() {
        return id;
    }

    public LeaveStatus getStatus() {
        return status;
    }
}