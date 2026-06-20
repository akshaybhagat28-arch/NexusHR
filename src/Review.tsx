import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function AddReview() {

  const navigate = useNavigate();

  // STATES

  const [employeeId, setEmployeeId] = useState("");

  const [reviewerId, setReviewerId] = useState("");

  const [rating, setRating] = useState("");

  const [feedback, setFeedback] = useState("");

  const [reviewDate, setReviewDate] = useState("");

  // TOKEN

  const token = localStorage.getItem("token");

  // LOGIN CHECK

  useEffect(() => {

    if (!token) {

      alert("Please Login First");

      navigate("/");
    }

  }, []);

  // ROLE BASED DASHBOARD

  const goDashboard = () => {

    const role = localStorage.getItem("role");

    if (role === "ROLE_ADMIN") {

      navigate("/PerformanceReviewDashboard");

    } else if (role === "ROLE_HR") {

      navigate("/HrDashboard");

    } else {

      navigate("/EmployeeDashboard");
    }
  };

  // SUBMIT REVIEW

  const submitReview = async (
    e: React.FormEvent
  ) => {

    e.preventDefault();

    // VALIDATION

    if (
      !employeeId ||
      !reviewerId ||
      !rating ||
      !feedback ||
      !reviewDate
    ) {

      alert("Please Fill All Fields");

      return;
    }

    try {

      // REQUEST BODY

      const reviewData = {

        employeeId: Number(employeeId),

        reviewerId: Number(reviewerId),

        rating: Number(rating),

        feedback: feedback,

        reviewDate: reviewDate,
      };

      console.log(reviewData);

      // API CALL

      const response = await axios.post(

        "http://localhost:8080/api/reviews",

        reviewData,

        {
          headers: {

            Authorization: `Bearer ${token}`,

            "Content-Type": "application/json",
          },
        }
      );

      console.log(response.data);

      alert("Review Added Successfully");

      // ROLE BASED RETURN

      goDashboard();

    } catch (error: any) {

      console.log(error);

      // 401

      if (error.response?.status === 401) {

        alert("Session Expired! Login Again");

        localStorage.clear();

        navigate("/");
      }

      // 403

      else if (error.response?.status === 403) {

        alert("Access Denied");
      }

      // 500

      else if (error.response?.status === 500) {

        alert(
          "Server Error - Check Employee ID / Reviewer ID"
        );
      }

      else {

        alert("Failed To Add Review");
      }
    }
  };

  return (

    <div
      style={{

        backgroundImage:
          "url('https://images.unsplash.com/photo-1521791136064-7986c2920216?q=80&w=1920&auto=format&fit=crop')",

        backgroundSize: "cover",

        backgroundPosition: "center",

        backgroundRepeat: "no-repeat",

        minHeight: "100vh",

        display: "flex",

        justifyContent: "center",

        alignItems: "center",

        padding: "20px",
      }}
    >

      {/* CARD */}

      <div
        style={{

          background:
            "rgba(30, 41, 59, 0.9)",

          backdropFilter: "blur(8px)",

          padding: "25px",

          borderRadius: "20px",

          width: "350px",

          boxShadow:
            "0 0 20px rgba(0,0,0,0.4)",
        }}
      >

        {/* TITLE */}

        <h1
          style={{
            color: "white",

            textAlign: "center",

            marginBottom: "25px",

            fontSize: "24px",
          }}
        >
          Add Review
        </h1>

        {/* FORM */}

        <form onSubmit={submitReview}>

          {/* EMPLOYEE ID */}

          <input
            type="number"

            placeholder="Employee ID"

            value={employeeId}

            onChange={(e) =>
              setEmployeeId(e.target.value)
            }

            style={inputStyle}
          />

          {/* REVIEWER ID */}

          <input
            type="number"

            placeholder="Reviewer ID"

            value={reviewerId}

            onChange={(e) =>
              setReviewerId(e.target.value)
            }

            style={inputStyle}
          />

          {/* RATING */}

          <div
            style={{
              marginBottom: "20px",
            }}
          >

            <label
              style={{
                color: "white",

                fontSize: "16px",

                display: "block",

                marginBottom: "8px",
              }}
            >
              Rating
            </label>

            <div
              style={{
                display: "flex",

                gap: "8px",

                cursor: "pointer",

                fontSize: "30px",
              }}
            >

              {[1, 2, 3, 4, 5].map(
                (star) => (

                <span
                  key={star}

                  onClick={() =>
                    setRating(
                      star.toString()
                    )
                  }

                  style={{

                    color:
                      Number(rating) >= star
                        ? "#facc15"
                        : "#64748b",

                    transition: "0.3s",
                  }}
                >
                  ★
                </span>

              ))}

            </div>

          </div>

          {/* FEEDBACK */}

          <textarea

            placeholder="Feedback"

            value={feedback}

            onChange={(e) =>
              setFeedback(
                e.target.value
              )
            }

            style={textareaStyle}
          />

          {/* DATE */}

          <input
            type="date"

            value={reviewDate}

            onChange={(e) =>
              setReviewDate(
                e.target.value
              )
            }

            style={inputStyle}
          />

          {/* BUTTONS */}

          <div
            style={{
              display: "flex",

              justifyContent:
                "space-between",

              marginTop: "15px",
            }}
          >

            {/* BACK */}

            <button
              type="button"

              onClick={goDashboard}

              style={backButton}
            >
              Back
            </button>

            {/* SUBMIT */}

            <button
              type="submit"

              style={submitButton}
            >
              Add
            </button>

          </div>

        </form>

      </div>

    </div>
  );
}

// INPUT STYLE

const inputStyle = {

  width: "100%",

  padding: "10px",

  marginBottom: "15px",

  borderRadius: "8px",

  border: "none",

  outline: "none",

  fontSize: "14px",
};

// TEXTAREA STYLE

const textareaStyle = {

  width: "100%",

  height: "90px",

  padding: "10px",

  marginBottom: "15px",

  borderRadius: "8px",

  border: "none",

  outline: "none",

  fontSize: "14px",

  resize: "none" as const,
};

// SUBMIT BUTTON

const submitButton = {

  background: "#16a34a",

  color: "white",

  border: "none",

  padding: "10px 16px",

  borderRadius: "8px",

  cursor: "pointer",

  fontSize: "14px",
};

// BACK BUTTON

const backButton = {

  background: "#2563eb",

  color: "white",

  border: "none",

  padding: "10px 16px",

  borderRadius: "8px",

  cursor: "pointer",

  fontSize: "14px",
};