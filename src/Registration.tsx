import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import bgImage from "./assets/t3.jpg";

export default function Registration() {

  const navigate = useNavigate();

  const [employee, setEmployee] = useState({
    firstName: "",
    lastName: "",
    email: "",
    basicSalary: "",
    totalWorkingDays: "",
    department: {
      id: ""
    }
  });

  // Handle Input Change
  const handleChange = (e: any) => {

    const { name, value } = e.target;

    if (name === "departmentId") {

      setEmployee({
        ...employee,
        department: {
          id: value
        }
      });

    } else {

      setEmployee({
        ...employee,
        [name]: value
      });
    }
  };

  // Submit Form
  const handleSubmit = async (e: any) => {

    e.preventDefault();

    try {

      await axios.post(
        "http://localhost:8080/employees",
        employee
      );

      alert("Employee Added Successfully");

      navigate("/employees");

    } catch (error) {

      console.log(error);
      alert("Error Adding Employee");
    }
  };

  return (

    <div
      style={{
        minHeight: "100vh",
        backgroundImage: `url(${bgImage})`,
        backgroundSize: "cover",
        backgroundPosition: "center",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        fontFamily: "Arial"
      }}
    >

      {/* Overlay */}

      <div
        style={{
          background: "rgba(0,0,0,0.7)",
          padding: "40px",
          borderRadius: "20px",
          width: "420px",
          boxShadow: "0px 5px 25px rgba(0,0,0,0.4)"
        }}
      >

        <h1
          style={{
            color: "white",
            textAlign: "center",
            marginBottom: "10px",
            fontSize: "34px"
          }}
        >
          Employee Registration
        </h1>

        <p
          style={{
            color: "#cbd5e1",
            textAlign: "center",
            marginBottom: "30px"
          }}
        >
          Add new employee details
        </p>

        <form onSubmit={handleSubmit}>

          <input
            type="text"
            name="firstName"
            placeholder="First Name"
            value={employee.firstName}
            onChange={handleChange}
            required
            style={inputStyle}
          />

          <input
            type="text"
            name="lastName"
            placeholder="Last Name"
            value={employee.lastName}
            onChange={handleChange}
            required
            style={inputStyle}
          />

          <input
            type="email"
            name="email"
            placeholder="Email"
            value={employee.email}
            onChange={handleChange}
            required
            style={inputStyle}
          />

          <input
            type="number"
            name="basicSalary"
            placeholder="Basic Salary"
            value={employee.basicSalary}
            onChange={handleChange}
            required
            style={inputStyle}
          />

          <input
            type="number"
            name="totalWorkingDays"
            placeholder="Total Working Days"
            value={employee.totalWorkingDays}
            onChange={handleChange}
            required
            style={inputStyle}
          />

          <input
            type="number"
            name="departmentId"
            placeholder="Department ID"
            onChange={handleChange}
            required
            style={inputStyle}
          />

          <button
            type="submit"
            style={{
              width: "100%",
              padding: "14px",
              background: "linear-gradient(90deg,#2563eb,#38bdf8)",
              color: "white",
              border: "none",
              borderRadius: "10px",
              fontSize: "16px",
              fontWeight: "bold",
              cursor: "pointer",
              marginTop: "10px"
            }}
          >
            Register Employee
          </button>

          <button
            type="button"
            onClick={() => navigate("/employees")}
            style={{
              width: "100%",
              padding: "14px",
              background: "#ef4444",
              color: "white",
              border: "none",
              borderRadius: "10px",
              fontSize: "16px",
              fontWeight: "bold",
              cursor: "pointer",
              marginTop: "15px"
            }}
          >
            Back
          </button>

        </form>

      </div>

    </div>
  );
}

// Input Style
const inputStyle = {
  width: "100%",
  padding: "14px",
  marginBottom: "18px",
  borderRadius: "10px",
  border: "none",
  outline: "none",
  fontSize: "15px",
  background: "#f8fafc"
};