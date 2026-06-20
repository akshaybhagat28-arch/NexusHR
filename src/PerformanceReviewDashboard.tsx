import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

// ================================
// REVIEW DTO
// ================================

interface Review {

  id: number;

  employeeId: number;

  reviewerId: number;

  employeeName: string;

  reviewerName: string;

  feedback: string;

  rating: number;

  reviewDate: string;
}

// ================================
// COMPONENT
// ================================

export default function PerformanceReviewDashboard() {

  const navigate = useNavigate();

  // ================================
  // STATE
  // ================================

  const [reviews, setReviews] =
    useState<Review[]>([]);

  const [loading, setLoading] =
    useState(true);

  // ================================
  // TOKEN
  // ================================

  const token =
    localStorage.getItem("token");

  // ================================
  // PAGE LOAD
  // ================================

  useEffect(() => {

    // TOKEN CHECK

    if (!token) {

      alert(
        "Please Login First"
      );

      navigate("/");

      return;
    }

    fetchReviews();

  }, []);

  // ================================
  // FETCH REVIEWS
  // ================================

  const fetchReviews = async () => {

    try {

      const response =
        await axios.get(
          "http://localhost:8080/api/reviews",
          {
            headers: {
              Authorization:
                `Bearer ${token}`,
            },
          }
        );

      console.log(
        "API RESPONSE => ",
        response.data
      );

      setReviews(
        response.data
      );

    } catch (error: any) {

      console.log(error);

      // 401

      if (
        error.response?.status === 401
      ) {

        alert(
          "Session Expired! Login Again"
        );

        localStorage.clear();

        navigate("/");
      }

      // 403

      else if (
        error.response?.status === 403
      ) {

        alert(
          "Access Denied"
        );
      }

      else {

        alert(
          "Failed To Fetch Reviews"
        );
      }

    } finally {

      setLoading(false);
    }
  };

  // ================================
  // DELETE REVIEW
  // ================================

  const deleteReview = async (
    id: number
  ) => {

    // SAFETY CHECK

    if (
      !id ||
      isNaN(id)
    ) {

      alert(
        "Invalid Review ID"
      );

      return;
    }

    const confirmDelete =
      window.confirm(
        "Are you sure you want to delete this review?"
      );

    if (!confirmDelete) {

      return;
    }

    try {

      await axios.delete(
        `http://localhost:8080/api/reviews/delete/${id}`,
        {
          headers: {
            Authorization:
              `Bearer ${token}`,
          },
        }
      );

      // REMOVE FROM UI

      setReviews(

        reviews.filter(

          (review) =>
            review.id !== id
        )
      );

      alert(
        "Review Deleted Successfully"
      );

    } catch (error: any) {

      console.log(error);

      // 401

      if (
        error.response?.status === 401
      ) {

        alert(
          "Session Expired"
        );

        localStorage.clear();

        navigate("/");
      }

      // 403

      else if (
        error.response?.status === 403
      ) {

        alert(
          "Only HR/Admin Can Delete"
        );
      }

      else {

        alert(
          "Failed To Delete Review"
        );
      }
    }
  };

  // ================================
  // DASHBOARD NAVIGATION
  // ================================

  const goToDashboard = () => {

    const role =
      localStorage.getItem("role");

    if (
      role === "ROLE_ADMIN"
    ) {

      navigate(
        "/AdminDashboard"
      );
    }

    else if (
      role === "ROLE_HR"
    ) {

      navigate(
        "/HrDashboard"
      );
    }

    else {

      navigate(
        "/EmployeeDashboard"
      );
    }
  };

  // ================================
  // LOGOUT
  // ================================

  const logout = () => {

    localStorage.clear();

    navigate("/");
  };

  // ================================
  // STAR RENDER
  // ================================

  const renderStars = (
    rating: number
  ) => {

    let stars = "";

    for (
      let i = 1;
      i <= 5;
      i++
    ) {

      if (i <= rating) {

        stars += "★";
      }

      else {

        stars += "☆";
      }
    }

    return stars;
  };

  // ================================
  // UI
  // ================================

  return (

    <div
      style={{

        backgroundImage:
          "url('https://images.unsplash.com/photo-1552664730-d307ca884978?q=80&w=1920&auto=format&fit=crop')",

        backgroundSize: "cover",

        backgroundPosition: "center",

        backgroundRepeat: "no-repeat",

        minHeight: "100vh",

        padding: "30px",

        color: "white",
      }}
    >

      {/* OVERLAY */}

      <div
        style={{

          background:
            "rgba(15,23,42,0.92)",

          minHeight: "100vh",

          padding: "30px",

          borderRadius: "20px",
        }}
      >

        {/* NAVBAR */}

        <div
          style={{

            display: "flex",

            justifyContent:
              "space-between",

            alignItems: "center",

            marginBottom: "30px",

            flexWrap: "wrap",

            gap: "20px",
          }}
        >

          {/* TITLE */}

          <h1
            style={{

              fontSize: "38px",

              fontWeight: "bold",
            }}
          >
            Performance Reviews
          </h1>

          {/* BUTTONS */}

          <div
            style={{

              display: "flex",

              gap: "15px",

              flexWrap: "wrap",
            }}
          >

            {/* DASHBOARD */}

            <button
              onClick={
                goToDashboard
              }
              style={
                dashboardButton
              }
            >
              Dashboard
            </button>

            {/* ADD REVIEW */}

            <button
              onClick={() =>
                navigate("/review")
              }
              style={
                reviewButton
              }
            >
              Add Review
            </button>

            {/* LOGOUT */}

            <button
              onClick={logout}
              style={
                logoutButton
              }
            >
              Logout
            </button>

          </div>

        </div>

        {/* TABLE CARD */}

        <div
          style={{

            background:
              "rgba(30,41,59,0.95)",

            padding: "25px",

            borderRadius: "20px",

            overflowX: "auto",

            boxShadow:
              "0 0 20px rgba(0,0,0,0.4)",
          }}
        >

          <h2
            style={{

              marginBottom: "20px",

              fontSize: "28px",
            }}
          >
            Review Details
          </h2>

          {/* LOADING */}

          {loading ? (

            <h3>
              Loading Reviews...
            </h3>

          ) : (

            <table
              style={{

                width: "100%",

                borderCollapse:
                  "collapse",
              }}
            >

              {/* TABLE HEAD */}

              <thead>

                <tr
                  style={{

                    background:
                      "#334155",
                  }}
                >

                  <th style={tableHead}>
                    Review ID
                  </th>

                  <th style={tableHead}>
                    Employee
                  </th>

                  <th style={tableHead}>
                    Reviewer
                  </th>

                  <th style={tableHead}>
                    Rating
                  </th>

                  <th style={tableHead}>
                    Feedback
                  </th>

                  <th style={tableHead}>
                    Date
                  </th>

                  <th style={tableHead}>
                    Action
                  </th>

                </tr>

              </thead>

              {/* TABLE BODY */}

              <tbody>

                {reviews.length > 0 ? (

                  reviews.map(
                    (review) => (

                      <tr
                        key={review.id}
                        style={{

                          textAlign:
                            "center",

                          borderBottom:
                            "1px solid #475569",
                        }}
                      >

                        {/* REVIEW ID */}

                        <td style={tableData}>
                          {review.id}
                        </td>

                        {/* EMPLOYEE */}

                        <td style={tableData}>
                          {
                            review.employeeName
                          }
                        </td>

                        {/* REVIEWER */}

                        <td style={tableData}>
                          {
                            review.reviewerName
                          }
                        </td>

                        {/* RATING */}

                        <td
                          style={{

                            ...tableData,

                            color:
                              "#facc15",

                            fontSize:
                              "22px",
                          }}
                        >
                          {renderStars(
                            review.rating
                          )}
                        </td>

                        {/* FEEDBACK */}

                        <td style={tableData}>
                          {
                            review.feedback
                          }
                        </td>

                        {/* DATE */}

                        <td style={tableData}>
                          {
                            review.reviewDate
                          }
                        </td>

                        {/* DELETE */}

                        <td style={tableData}>

                          <button
                            onClick={() => {

                              const reviewId =
                                Number(review.id);

                    

                              // SAFETY CHECK

                              if (
                                !reviewId ||
                                isNaN(reviewId)
                              ) {

                                alert(
                                  "Review ID Not Found"
                                );

                                return;
                              }

                              deleteReview(
                                reviewId
                              );
                            }}

                            style={
                              deleteButton
                            }
                          >
                            Delete
                          </button>

                        </td>

                      </tr>

                    )
                  )

                ) : (

                  <tr>

                    <td
                      colSpan={7}
                      style={{

                        padding:
                          "20px",

                        textAlign:
                          "center",

                        color:
                          "#cbd5e1",
                      }}
                    >
                      No Reviews Found
                    </td>

                  </tr>

                )}

              </tbody>

            </table>

          )}

        </div>

      </div>

    </div>
  );
}

// ================================
// BUTTON STYLES
// ================================

const dashboardButton = {

  background: "#2563eb",

  border: "none",

  padding: "10px 20px",

  borderRadius: "10px",

  color: "white",

  cursor: "pointer",

  fontSize: "16px",
};

const reviewButton = {

  background: "#16a34a",

  border: "none",

  padding: "10px 20px",

  borderRadius: "10px",

  color: "white",

  cursor: "pointer",

  fontSize: "16px",
};

const logoutButton = {

  background: "#dc2626",

  border: "none",

  padding: "10px 20px",

  borderRadius: "10px",

  color: "white",

  cursor: "pointer",

  fontSize: "16px",
};

const deleteButton = {

  background: "#ef4444",

  border: "none",

  padding: "8px 15px",

  borderRadius: "8px",

  color: "white",

  cursor: "pointer",

  fontSize: "14px",
};

const tableHead = {

  padding: "15px",

  color: "white",

  fontSize: "18px",
};

const tableData = {

  padding: "15px",

  color: "#e2e8f0",
};