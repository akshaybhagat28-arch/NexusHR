import { useState } from "react";

import { useNavigate } from "react-router-dom";

import axios from "axios";

import bgImage from "./assets/t3.jpg";

export default function Login() {

  const navigate = useNavigate();

  const [username, setUsername] =
    useState("");

  const [password, setPassword] =
    useState("");

  const handleLogin = async () => {

    try {

      const response = await axios.post(
        "http://localhost:8080/api/payroll/login",
        {
          username,
          password,
        }
      );

      // TOKEN SAVE
      localStorage.setItem(
        "token",
        response.data.accessToken
      );

      // ROLE SAVE
      localStorage.setItem(
        "role",
        response.data.role
      );

      // USERNAME SAVE
      localStorage.setItem(
        "username",
        response.data.username
      );

      console.log(response.data);

      // ROLE
      const role = response.data.role;

      // ROLE BASED NAVIGATION
      if (role === "ROLE_ADMIN") {

        navigate("/AdminDashboard");

      } else if (role === "ROLE_EMPLOYEE") {

        navigate("/EmployeeDashboard");

      } else if (role === "ROLE_HR") {

        navigate("/HrDashboard");

      } else {

        navigate("/");
      }

    } catch (error) {

      console.log(error);

      alert("Invalid Username Or Password");
    }
  };

  return (

    <div
      className="min-h-screen bg-cover bg-center flex justify-center items-center"
      style={{
        backgroundImage: `url(${bgImage})`,
      }}
    >

      <div className="bg-black/40 backdrop-blur-xl p-10 rounded-3xl w-[380px] border border-white/20">

        <h1 className="text-4xl text-white font-bold text-center mb-8">
          Nexus HR
        </h1>

        {/* USERNAME */}
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) =>
            setUsername(e.target.value)
          }
          className="w-full p-3 rounded-xl mb-5 outline-none"
        />

        {/* PASSWORD */}
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) =>
            setPassword(e.target.value)
          }
          className="w-full p-3 rounded-xl mb-6 outline-none"
        />

        {/* LOGIN BUTTON */}
        <button
          onClick={handleLogin}
          className="w-full bg-blue-600 hover:bg-blue-700 text-white py-3 rounded-xl font-bold"
        >
          Login
        </button>

      </div>

    </div>
  );
}