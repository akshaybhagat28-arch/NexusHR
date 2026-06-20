import { useNavigate } from "react-router-dom";
import bgImage from "./assets/t3.jpg";

export default function AdminDashboard() {

  const navigate = useNavigate();

  // LOGOUT
  const logout = () => {

    localStorage.clear();

    navigate("/");
  };

  return (

    <div
      className="min-h-screen bg-cover bg-center"
      style={{
        backgroundImage: `url(${bgImage})`,
      }}
    >

      {/* OVERLAY */}
      <div className="min-h-screen bg-black/50">

        {/* NAVBAR */}
        <div className="flex justify-between items-center px-10 py-5 bg-black/30 backdrop-blur-md">

          {/* TITLE */}
          <h1 className="text-white text-3xl font-bold">
            Nexus HR Admin Dashboard
          </h1>

          {/* LOGOUT BUTTON */}
          <button
            onClick={logout}
            className="bg-red-500 hover:bg-red-600 text-white px-5 py-2 rounded-lg"
          >
            Logout
          </button>

        </div>

        {/* DASHBOARD CARDS */}
        <div className="flex justify-center items-center gap-10 pt-24 flex-wrap">

          {/* PAYROLL CARD */}
          <div className="w-[260px] bg-white/10 backdrop-blur-lg border border-white/20 rounded-3xl p-8 shadow-2xl text-center">

            <h2 className="text-white text-2xl font-bold mb-4">
              Payroll
            </h2>

            <p className="text-gray-200 mb-6">
              Generate Employee Payslips
            </p>

            <button
              onClick={() => navigate("/payroll")}
              className="bg-green-600 hover:bg-green-700 text-white px-5 py-2 rounded-lg"
            >
              Open
            </button>

          </div>

          {/* EMPLOYEE CARD */}
          <div className="w-[260px] bg-white/10 backdrop-blur-lg border border-white/20 rounded-3xl p-8 shadow-2xl text-center">

            <h2 className="text-white text-2xl font-bold mb-4">
              Employees
            </h2>

            <p className="text-gray-200 mb-6">
              Manage Employee Records
            </p>

            <button
              onClick={() => navigate("/EmployeeDashboard")}
              className="bg-blue-600 hover:bg-blue-700 text-white px-5 py-2 rounded-lg"
            >
              Open
            </button>

          </div>

          {/* PERFORMANCE CARD */}
          <div className="w-[260px] bg-white/10 backdrop-blur-lg border border-white/20 rounded-3xl p-8 shadow-2xl text-center">

            <h2 className="text-white text-2xl font-bold mb-4">
              Performance
            </h2>

            <p className="text-gray-200 mb-6">
              Employee Performance Review
            </p>

            <button
              onClick={() =>
                navigate("/PerformanceReviewDashboard")
              }
              className="bg-purple-600 hover:bg-purple-700 text-white px-5 py-2 rounded-lg"
            >
              Open
            </button>

          </div>

          {/* LEAVE CARD */}
          <div className="w-[260px] bg-white/10 backdrop-blur-lg border border-white/20 rounded-3xl p-8 shadow-2xl text-center">

            <h2 className="text-white text-2xl font-bold mb-4">
              Leave
            </h2>

            <p className="text-gray-200 mb-6">
              Manage Employee Leaves
            </p>

            <button
              onClick={() =>
                navigate("/LeaveDashboard")
              }
              className="bg-yellow-500 hover:bg-yellow-600 text-white px-5 py-2 rounded-lg"
            >
              Open
            </button>

          </div>

        </div>

      </div>

    </div>
  );
}