import React from "react";

const HrDashboard = () => {
  return (
    <div style={{ padding: "20px" }}>
      <h1>HR Dashboard</h1>

      <div
        style={{
          display: "flex",
          gap: "20px",
          marginTop: "20px",
          flexWrap: "wrap",
        }}
      >
        {/* Employee Management */}
        <div
          style={{
            border: "1px solid #ccc",
            padding: "20px",
            width: "250px",
            borderRadius: "10px",
          }}
        >
          <h3>Employee Management</h3>
          <p>Manage employee records and details.</p>
          <button>View Employees</button>
        </div>

        {/* Leave Requests */}
        <div
          style={{
            border: "1px solid #ccc",
            padding: "20px",
            width: "250px",
            borderRadius: "10px",
          }}
        >
          <h3>Leave Requests</h3>
          <p>Approve or reject employee leave requests.</p>
          <button>Manage Leaves</button>
        </div>

        {/* Payroll */}
        <div
          style={{
            border: "1px solid #ccc",
            padding: "20px",
            width: "250px",
            borderRadius: "10px",
          }}
        >
          <h3>Payroll</h3>
          <p>Manage salary and payroll details.</p>
          <button>Open Payroll</button>
        </div>
      </div>
    </div>
  );
};

export default HrDashboard;