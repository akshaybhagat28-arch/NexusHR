import { BrowserRouter, Routes, Route } from "react-router-dom";

import Login from "./Login";
import Dashboard from "./Dashboard";
import Payroll from "./Payroll";
import Leave from "./Leave";

import LeaveDashboard from "./LeaveDashboard";
import AdminDashboard from "./AdminDashboard";
import EmployeeDashboard from "./EmployeeDashboard";
import HrDashboard from "./HrDashboard";
import PerformanceReviewDashboard from "./PerformanceReviewDashboard";
import Review from "./Review";



function App() {
  return (
    <BrowserRouter>
      <Routes>

        <Route
          path="/"
          element={<Login />}
        />

        <Route
          path="/dashboard"
          element={<Dashboard />}
        />

        <Route
          path="/AdminDashboard"
          element={<AdminDashboard />}
        />

        <Route
          path="/EmployeeDashboard"
          element={<EmployeeDashboard />}
        />

        <Route
          path="/HrDashboard"
          element={<HrDashboard />}
        />

        <Route
          path="/LeaveDashboard"
          element={<LeaveDashboard />}
        />

        <Route
          path="/payroll"
          element={<Payroll />}
        />

        <Route
          path="/leave"
          element={<Leave />}
        />

         <Route
          path="/PerformanceReviewDashboard"
          element={<PerformanceReviewDashboard />}
        />
        
         <Route
          path="/Review"
          element={<Review />}
        />
        

      </Routes>
    </BrowserRouter>
  );
}

export default App;