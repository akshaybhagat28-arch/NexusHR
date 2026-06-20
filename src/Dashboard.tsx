import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function Dashboard() {

  const navigate = useNavigate();

  const [employees, setEmployees] = useState([]);

  const [leaves, setLeaves] = useState([]);

  // GET TOKEN
  const token = localStorage.getItem("token");

  // GET ROLE
  const role = localStorage.getItem("role");

  // GET USERNAME
  const username =
    localStorage.getItem("username");

  // FETCH DATA
  useEffect(() => {

    // TOKEN CHECK
    if (!token) {

      navigate("/");

      return;
    }

    // ONLY ADMIN FETCH EMPLOYEES
    if (role === "ROLE_ADMIN") {

      fetchEmployees();
    }

    // ONLY EMPLOYEE FETCH LEAVES
    if (role === "ROLE_EMPLOYEE") {

      fetchLeaves();
    }

  }, []);

  // FETCH EMPLOYEE API
  const fetchEmployees = async () => {

    try {

      const response = await axios.get(
        "http://localhost:8080/api/employee"
      );

      setEmployees(response.data);

    } catch (error) {

      console.log(error);

      alert("Unable To Load Employees");
    }
  };

  // FETCH LEAVES API
  const fetchLeaves = async () => {

    try {

      const response = await axios.get(
        "http://localhost:8080/api/leave"
      );

      setLeaves(response.data);

    } catch (error) {

      console.log(error);

      alert("Unable To Load Leave Details");
    }
  };

  // LOGOUT
  const logout = () => {

    localStorage.removeItem("token");
    localStorage.removeItem("role");
    localStorage.removeItem("username");

    navigate("/");
  };

  return (

    <div className="min-h-screen bg-black text-white p-10">

      {/* HEADER */}
      <div className="flex justify-between items-center mb-10">

        <div>

          <h1 className="text-4xl font-bold">
            NexusHR Dashboard
          </h1>

          <p className="text-gray-400 mt-2">
            Welcome {username}
          </p>

        </div>

        <div className="flex gap-4 flex-wrap">

          {/* ADMIN BUTTON */}
          {role === "ROLE_ADMIN" && (

            <button
              onClick={() =>
                navigate("/payroll")
              }
              className="bg-green-600 hover:bg-green-700 px-5 py-3 rounded-xl"
            >
              Payroll Module
            </button>

          )}

          {/* EMPLOYEE BUTTON */}
          {role === "ROLE_EMPLOYEE" && (

            <button
              onClick={() =>
                navigate("/Leave")
              }
              className="bg-yellow-500 hover:bg-yellow-600 px-5 py-3 rounded-xl"
            >
              Apply Leave
            </button>

          )}

          {/* LEAVE MANAGEMENT BUTTON */}
          <button
            onClick={() =>
              navigate("/LeaveDashboard")
            }
            className="bg-blue-600 hover:bg-blue-700 px-5 py-3 rounded-xl"
          >
            Leave Management
          </button>

          {/* LOGOUT */}
          <button
            onClick={logout}
            className="bg-red-600 hover:bg-red-700 px-5 py-3 rounded-xl"
          >
            Logout
          </button>

        </div>

      </div>

      {/* EMPLOYEE TABLE ONLY ADMIN */}
      {role === "ROLE_ADMIN" && (

        <div className="bg-gray-900 rounded-2xl p-6 overflow-x-auto mb-10">

          <h2 className="text-2xl font-bold mb-5">
            Employee Details
          </h2>

          <table className="w-full">

            <thead>

              <tr className="bg-blue-600">

                <th className="p-3 text-left">
                  ID
                </th>

                <th className="p-3 text-left">
                  Employee
                </th>

                <th className="p-3 text-left">
                  Department
                </th>

                <th className="p-3 text-left">
                  Email
                </th>

                <th className="p-3 text-left">
                  Salary
                </th>

                <th className="p-3 text-left">
                  Actions
                </th>

              </tr>

            </thead>

            <tbody>

              {employees.map((employee) => (

                <tr
                  key={employee.id}
                  className="border-b border-gray-700"
                >

                  <td className="p-3">
                    {employee.id}
                  </td>

                  <td className="p-3">

                    {employee.firstName}
                    {" "}
                    {employee.lastName}

                  </td>

                  <td className="p-3">

                    {employee.department
                      ?.departmentName}

                  </td>

                  <td className="p-3">
                    {employee.email}
                  </td>

                  <td className="p-3">

                    ₹
                    {employee.basicSalary}

                  </td>

                  <td className="p-3">

                    <button
                      onClick={() =>
                        navigate("/payroll")
                      }
                      className="bg-green-600 hover:bg-green-700 px-3 py-1 rounded-lg"
                    >
                      Generate Payslip
                    </button>

                  </td>

                </tr>

              ))}

            </tbody>

          </table>

        </div>

      )}

      {/* LEAVE TABLE ONLY EMPLOYEE */}
      {role === "ROLE_EMPLOYEE" && (

        <div className="bg-gray-900 rounded-2xl p-6 overflow-x-auto">

          <h2 className="text-2xl font-bold mb-5">
            Leave Details
          </h2>

          <table className="w-full">

            <thead>

              <tr className="bg-yellow-600">

                <th className="p-3 text-left">
                  Leave ID
                </th>

                <th className="p-3 text-left">
                  From Date
                </th>

                <th className="p-3 text-left">
                  To Date
                </th>

                <th className="p-3 text-left">
                  Reason
                </th>

                <th className="p-3 text-left">
                  Status
                </th>

              </tr>

            </thead>

            <tbody>

              {leaves.map((leave) => (

                <tr
                  key={leave.id}
                  className="border-b border-gray-700"
                >

                  <td className="p-3">
                    {leave.id}
                  </td>

                  <td className="p-3">
                    {leave.fromDate}
                  </td>

                  <td className="p-3">
                    {leave.toDate}
                  </td>

                  <td className="p-3">
                    {leave.reason}
                  </td>

                  <td className="p-3">
                    {leave.status}
                  </td>

                </tr>

              ))}

            </tbody>

          </table>

        </div>

      )}

    </div>
  );
}