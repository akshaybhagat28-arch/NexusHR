import { useEffect, useState } from "react";

import { useNavigate } from "react-router-dom";

import axios from "axios";

import bgImage from "./assets/t3.jpg";

export default function Payroll() {

  const navigate = useNavigate();

  const [employees, setEmployees] = useState([]);

  const [selectedEmployeeId, setSelectedEmployeeId] =
    useState("");

  const [month, setMonth] = useState("");

  // GET DATA FROM LOCALSTORAGE
  const token = localStorage.getItem("token");

  const username =
    localStorage.getItem("username");
    console.log(username)

  const role =
    localStorage.getItem("role");
    console.log(role)

  // FETCH EMPLOYEES
  useEffect(() => {

    // TOKEN CHECK
    if (!token) {

      navigate("/");

      return;
    }

    fetchEmployees();

  }, []);

  // FETCH EMPLOYEE API
  const fetchEmployees = async () => {

    try {

      const response = await axios.get(
        "http://localhost:8080/api/employee",
         {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
       
      );

      setEmployees(response.data);

    } catch (error) {

      console.log(error);

      alert("Unable To Load Employees");
    }
  };

  // SELECTED EMPLOYEE
  const selectedEmployee = employees.find(
    (emp) => emp.id == selectedEmployeeId
  );

  // GENERATE PAYSLIP
  const generatePayslip = async () => {

    if (!selectedEmployeeId || !month) {

      alert("Please Select Employee And Month");

      return;
    }

    try {

      const response = await axios.get(
        `http://localhost:8080/api/payroll/payslip/${selectedEmployeeId}/${month}`,
        {
          responseType: "blob",

          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      // CREATE PDF URL
      const fileURL =
        window.URL.createObjectURL(

          new Blob(
            [response.data],
            {
              type: "application/pdf",
            }
          )
        );

      // DOWNLOAD PDF
      const link =
        document.createElement("a");

      link.href = fileURL;

      link.setAttribute(
        "download",
        `${selectedEmployee.firstName}_${month}_Payslip.pdf`
      );

      document.body.appendChild(link);

      link.click();

      link.remove();

    } catch (error) {

      console.log(error);

      alert("Payslip Generation Failed");
    }
  };

  // LOGOUT
  const logout = () => {

    navigate("/");
  };

  return (

    <div
      className="min-h-screen bg-cover bg-center relative"
      style={{
        backgroundImage: `url(${bgImage})`,
      }}
    >

      {/* OVERLAY */}
      <div className="absolute inset-0 bg-black/60"></div>

      {/* MAIN CONTENT */}
      <div className="relative z-10">

        {/* NAVBAR */}
        <div className="bg-black/30 backdrop-blur-md text-white flex justify-between items-center px-8 py-4 shadow-lg">

          <div>

            <h1 className="text-3xl font-bold">
              Nexus HR Payroll
            </h1>

            <p className="text-gray-300 text-sm mt-1">

              Welcome {username}

            </p>

          </div>

          <div className="flex gap-4">

            {/* DASHBOARD BUTTON */}
           <button
  onClick={() => {

    if (role === "ROLE_ADMIN") {

      navigate("/AdminDashboard");

    } else if (role === "ROLE_HR") {

      navigate("/HrDashboard");

    } else if (role === "ROLE_EMPLOYEE") {

      navigate("/EmployeeDashboard");

    } else {

      navigate("/");
    }
  }}
  className="bg-blue-600 hover:bg-blue-700 px-5 py-2 rounded-xl"
>
  Dashboard
</button>

            {/* LOGOUT */}
            <button
              onClick={logout}
              className="bg-red-500 hover:bg-red-600 px-5 py-2 rounded-xl"
            >
              Logout
            </button>

          </div>

        </div>

        {/* PAYROLL CARD */}
        <div className="flex justify-center items-center mt-16">

          <div className="w-[470px] bg-white/10 backdrop-blur-xl border border-white/20 rounded-3xl shadow-2xl p-10">

            <h2 className="text-3xl text-white font-bold text-center mb-8">

              Generate Payslip

            </h2>

            {/* EMPLOYEE DROPDOWN */}
            <div className="mb-6">

              <label className="block text-white font-semibold mb-2">

                Select Employee

              </label>

              <select
                value={selectedEmployeeId}
                onChange={(e) =>
                  setSelectedEmployeeId(
                    e.target.value
                  )
                }
                className="w-full p-3 rounded-xl bg-white/20 text-white border border-white/30 outline-none"
              >

                <option
                  className="text-black"
                  value=""
                >
                  Choose Employee
                </option>

                {employees.map((emp) => (

                  <option
                    key={emp.id}
                    value={emp.id}
                    className="text-black"
                  >
                    {emp.id}
                    {" "}
                    {emp.firstName}
                    {" "}
                    {emp.lastName}

                  </option>

                ))}

              </select>

            </div>

      
            

            {/* MONTH DROPDOWN */}
            <div className="mb-8">

              <label className="block text-white font-semibold mb-2">

                Select Month

              </label>

              <select
                value={month}
                onChange={(e) =>
                  setMonth(e.target.value)
                }
                className="w-full p-3 rounded-xl bg-white/20 text-white border border-white/30 outline-none"
              >

                <option
                  className="text-black"
                  value=""
                >
                  Choose Month
                </option>

                <option className="text-black">
                  January
                </option>

                <option className="text-black">
                  February
                </option>

                <option className="text-black">
                  March
                </option>

                <option className="text-black">
                  April
                </option>

                <option className="text-black">
                  May
                </option>

                <option className="text-black">
                  June
                </option>

                <option className="text-black">
                  July
                </option>

                <option className="text-black">
                  August
                </option>

                <option className="text-black">
                  September
                </option>

                <option className="text-black">
                  October
                </option>

                <option className="text-black">
                  November
                </option>

                <option className="text-black">
                  December
                </option>

              </select>

            </div>

            {/* GENERATE BUTTON */}
            <button
              onClick={generatePayslip}
              className="w-full bg-green-600 hover:bg-green-700 text-white py-3 rounded-2xl text-lg font-bold transition"
            >

              Generate & Download Payslip

            </button>

          </div>

        </div>

      </div>

    </div>
  );
}