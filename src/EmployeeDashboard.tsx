import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function EmployeeDashboard() {

  interface Employee {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  basicSalary: number;
}

interface Attendance {
  id: number;
  date: string;
  status: string;
}

interface Leave {
  id: number;
  fromDate: string;
  toDate: string;
  reason: string;
  status: string;
}

interface Review {
  id: number;
  feedback: string;
  rating: number;
  reviewDate: string;
}

  const navigate = useNavigate();

 const [employee, setEmployee] = useState<Employee | null>(null);
const [attendance, setAttendance] = useState<Attendance[]>([]);
const [leaves, setLeaves] = useState<(string | number)[][]>([]);
const [reviews, setReviews] = useState<Review[]>([]);

  const token = localStorage.getItem("token");

  // =========================================
  // FETCH DATA
  // =========================================

  useEffect(() => {

    fetchEmployee();
    fetchAttendance();
    fetchLeaves();
    fetchReviews();

  }, []);

  // =========================================
  // EMPLOYEE DETAILS
  // =========================================

  const fetchEmployee = async () => {

    try {

      const response = await axios.get(
        "http://localhost:8080/api/employee",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      setEmployee(response.data[0]);
      console.log("employee data:",response.data);

    } catch (error) {
      console.log(error);
    }
  };

  // =========================================
  // ATTENDANCE
  // =========================================

  const fetchAttendance = async () => {

    try {

      const response = await axios.get(
        "http://localhost:8080/api/attendance",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      setAttendance(response.data);

    } catch (error) {
      console.log(error);
    }
  };

  // =========================================
  // LEAVES
  // =========================================

  const fetchLeaves = async () => {

    try {

      const response = await axios.get(
        "http://localhost:8080/api/leaves",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      setLeaves(response.data);
      setLeaves(response.data);
      console.log("leave data:",response.data);

    } catch (error) {
      console.log(error);
    }
  };

  // =========================================
  // PERFORMANCE REVIEWS
  // =========================================

  const fetchReviews = async () => {

    try {

      const response = await axios.get(
        "http://localhost:8080/api/reviews",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      setReviews(response.data);

    } catch (error) {
      console.log(error);
    }
  };

  // =========================================
  // LOGOUT
  // =========================================

  const logout = () => {

    localStorage.removeItem("token");
    localStorage.removeItem("role");

    navigate("/login");
  };

  // =========================================
// CALCULATIONS
// =========================================

const totalAttendance = attendance.length;

const approvedLeaves = leaves.filter(
  (leave) => leave[7] === "APPROVED"
).length;

const pendingLeaves = leaves.filter(
  (leave) => leave[7] === "PENDING"
).length;


const averageRating =
  reviews.length > 0
    ? (
        reviews.reduce(
          (sum, review) => sum + Number(review.rating || 0),
          0
        ) / reviews.length
      ).toFixed(1)
    : 0;
  // =========================================
  // UI
  // =========================================

  return (
    <div
      className="min-h-screen bg-cover bg-center p-8"
      style={{
        backgroundImage:
          "url('https://images.unsplash.com/photo-1520607162513-77705c0f0d4a')",
      }}
    >
      {/* HEADER */}

      <div className="flex justify-between items-center mb-8">

        <div>
          <h1 className="text-4xl font-bold text-white">
            Employee Dashboard
          </h1>

          <p className="text-gray-200 mt-2">
            Welcome back {employee?.firstName}
          </p>
        </div>
 {/* Buttons */}
  <div className="flex gap-3">

    <button
      onClick={() => navigate("/AdminDashboard")}
      className="bg-blue-500 hover:bg-blue-600 text-white px-5 py-2 rounded-lg"
    >
      Admin Dashboard
    </button>

    <button
     onClick={() => navigate("/")}
      className="bg-red-500 hover:bg-red-600 text-white px-5 py-2 rounded-lg"
    >
      Logout
    </button>

  </div>
      </div>

      {/* METRICS */}

      <div className="grid grid-cols-1 md:grid-cols-4 gap-6 mb-10">

        {/* Attendance */}

        <div className="bg-white shadow-lg rounded-xl p-6">
          <h2 className="text-gray-500 text-lg">
            Attendance
          </h2>

          <p className="text-4xl font-bold text-blue-600 mt-3">
            {totalAttendance}
          </p>
        </div>

        {/* Approved Leaves */}

        <div className="bg-white shadow-lg rounded-xl p-6">
          <h2 className="text-gray-500 text-lg">
            Approved Leaves
          </h2>

          <p className="text-4xl font-bold text-green-600 mt-3">
            {approvedLeaves}
          </p>
        </div>

        {/* Pending Leaves */}

        <div className="bg-white shadow-lg rounded-xl p-6">
          <h2 className="text-gray-500 text-lg">
            Pending Leaves
          </h2>

          <p className="text-4xl font-bold text-yellow-500 mt-3">
            {pendingLeaves}
          </p>
        </div>

        {/* Performance Rating */}

        <div className="bg-white shadow-lg rounded-xl p-6">
          <h2 className="text-gray-500 text-lg">
            Performance Rating
          </h2>

          <p className="text-4xl font-bold text-purple-600 mt-3">
            {averageRating}
          </p>
        </div>
      </div>

      {/* EMPLOYEE INFO */}

      <div className="bg-white rounded-xl shadow-lg p-6 mb-10">

        <h2 className="text-2xl font-bold mb-5">
          Employee Information
        </h2>

        <div className="grid md:grid-cols-2 gap-5">

          <div>
            <p className="text-gray-500">
              First Name
            </p>

            <p className="font-semibold">
              {employee?.firstName}
            </p>
          </div>

          <div>
            <p className="text-gray-500">
              Last Name
            </p>

            <p className="font-semibold">
              {employee?.lastName}
            </p>
          </div>

          <div>
            <p className="text-gray-500">
              Email
            </p>

            <p className="font-semibold">
              {employee?.email}
            </p>
          </div>

          <div>
            <p className="text-gray-500">
              Salary
            </p>

            <p className="font-semibold">
              ₹ {employee?.basicSalary}
            </p>
          </div>
        </div>
      </div>

      {/* RECENT REVIEWS */}

      <div className="bg-white rounded-xl shadow-lg p-6">

        <h2 className="text-2xl font-bold mb-5">
          Recent Performance Reviews
        </h2>

        <div className="overflow-auto">

          <table className="w-full border">

            <thead className="bg-gray-100">

              <tr>

                <th className="p-3 border">
                  Feedback
                </th>

                <th className="p-3 border">
                  Rating
                </th>

                <th className="p-3 border">
                  Date
                </th>

              </tr>
            </thead>

            <tbody>

              {reviews.map((review) => (

                <tr key={review.id}>

                  <td className="p-3 border">
                    {review.feedback}
                  </td>

                  <td className="p-3 border text-center">
                    {review.rating}
                  </td>

                  <td className="p-3 border text-center">
                    {review.reviewDate}
                  </td>

                </tr>
              ))}

            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}