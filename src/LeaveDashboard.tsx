import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

import bgImage from "./assets/t3.jpg";

export default function LeaveDashboard() {

  const [leaves, setLeaves] =
    useState<any[]>([]);

  const navigate =
    useNavigate();

  const token =
    localStorage.getItem("token");

  // ====================================
  // FETCH LEAVES
  // ====================================

  useEffect(() => {

    fetchLeaves();

  }, []);

  const fetchLeaves = async () => {

    try {

      const response =
        await axios.get(
          "http://localhost:8080/api/leaves",
          {
            headers: {
              Authorization:
                `Bearer ${token}`,
            },
          }
        );

      console.log(
        "LEAVE DATA => ",
        response.data
      );

      setLeaves(response.data);

    } catch (error) {

      console.log(error);

      alert(
        "Failed To Fetch Leaves"
      );
    }
  };

  // ====================================
  // BEAUTIFUL POPUP
  // ====================================

  const showPopup = (
    message: string,
    color: string
  ) => {

    const popup =
      document.createElement(
        "div"
      );

    popup.innerHTML =
      message;

    popup.style.position =
      "fixed";

    popup.style.top =
      "30px";

    popup.style.right =
      "30px";

    popup.style.padding =
      "16px 25px";

    popup.style.background =
      color;

    popup.style.color =
      "white";

    popup.style.fontSize =
      "18px";

    popup.style.fontWeight =
      "bold";

    popup.style.borderRadius =
      "12px";

    popup.style.boxShadow =
      "0 0 20px rgba(0,0,0,0.4)";

    popup.style.zIndex =
      "9999";

    document.body.appendChild(
      popup
    );

    setTimeout(() => {

      popup.remove();

    }, 3000);
  };

  // ====================================
  // APPROVE LEAVE
  // ====================================

  const approveLeave = async (
    id: number
  ) => {

    const confirmApprove =
      window.confirm(
        "Do You Want To Approve This Leave?"
      );

    if (!confirmApprove)
      return;

    try {

      await axios.put(

        `http://localhost:8080/api/leaves/${id}/approve`,

        {},

        {
          headers: {
            Authorization:
              `Bearer ${token}`,
          },
        }
      );

      showPopup(
        "✅ Leave Approved Successfully",
        "#16a34a"
      );

      fetchLeaves();

    } catch (error) {

      console.log(error);

      showPopup(
        "❌ Failed To Approve Leave",
        "#dc2626"
      );
    }
  };

  // ====================================
  // REJECT LEAVE
  // ====================================

  const rejectLeave = async (
    id: number
  ) => {

    const confirmReject =
      window.confirm(
        "Do You Want To Reject This Leave?"
      );

    if (!confirmReject)
      return;

    try {

      await axios.put(

        `http://localhost:8080/api/leaves/${id}/reject`,

        {},

        {
          headers: {
            Authorization:
              `Bearer ${token}`,
          },
        }
      );

      showPopup(
        "❌ Leave Rejected Successfully",
        "#dc2626"
      );

      fetchLeaves();

    } catch (error) {

      console.log(error);

      showPopup(
        "❌ Failed To Reject Leave",
        "#dc2626"
      );
    }
  };

  // ====================================
  // LOGOUT
  // ====================================

  const logout = () => {

    localStorage.removeItem(
      "token"
    );

    navigate("/");
  };

  return (

    <div
      style={{
        padding: "30px",

        minHeight: "100vh",

        color: "white",

        backgroundImage:
          `linear-gradient(
            rgba(0,0,0,0.75),
            rgba(0,0,0,0.75)
          ),
          url(${bgImage})`,

        backgroundSize:
          "cover",

        backgroundPosition:
          "center",

        backgroundRepeat:
          "no-repeat",

        backgroundAttachment:
          "fixed",
      }}
    >

      {/* TOP BAR */}

      <div
        style={{
          display: "flex",

          justifyContent:
            "space-between",

          alignItems:
            "center",

          marginBottom:
            "40px",

          gap: "20px",
        }}
      >

        {/* DASHBOARD BUTTON */}

        <button
          onClick={() =>
            navigate(
              "/AdminDashboard"
            )
          }
          style={
            dashboardButton
          }
        >
          ← Dashboard
        </button>

        {/* PAGE TITLE */}

        <h1
          style={{
            textAlign:
              "center",

            fontSize:
              "55px",

            fontWeight:
              "bold",

            letterSpacing:
              "2px",

            textShadow:
              "0 0 20px rgba(255,255,255,0.5)",

            flex: 1,
          }}
        >
          Leave Dashboard
        </h1>

        {/* LOGOUT BUTTON */}

        <button
          onClick={logout}
          style={
            logoutButton
          }
        >
          Logout
        </button>

      </div>

      {/* TABLE */}

      <div
        style={{
          overflowX: "auto",
        }}
      >

        <table
          style={{
            width: "100%",

            borderCollapse:
              "collapse",

            background:
              "rgba(15,23,42,0.75)",

            backdropFilter:
              "blur(12px)",

            border:
              "1px solid rgba(255,255,255,0.1)",

            borderRadius:
              "15px",

            overflow:
              "hidden",
          }}
        >

          <thead>

            <tr
              style={{
                background:
                  "rgba(255,255,255,0.1)",
              }}
            >

              <th style={tableHead}>
                Leave ID
              </th>

              <th style={tableHead}>
                Employee ID
              </th>

              <th style={tableHead}>
                Employee Name
              </th>

              <th style={tableHead}>
                Email
              </th>

              <th style={tableHead}>
                Reason
              </th>

              <th style={tableHead}>
                Start Date
              </th>

              <th style={tableHead}>
                End Date
              </th>

              <th style={tableHead}>
                Status
              </th>

              <th style={tableHead}>
                Actions
              </th>

            </tr>

          </thead>

          <tbody>

            {leaves.map(
              (
                leave,
                index
              ) => (

                <tr
                  key={index}
                  style={{
                    textAlign:
                      "center",

                    borderBottom:
                      "1px solid rgba(255,255,255,0.08)",
                  }}
                >

                  <td style={tableData}>
                    {leave[0]}
                  </td>

                  <td style={tableData}>
                    {leave[1]}
                  </td>

                  <td style={tableData}>
                    {leave[2]}
                  </td>

                  <td style={tableData}>
                    {leave[3]}
                  </td>

                  <td style={tableData}>
                    {leave[4]}
                  </td>

                  <td style={tableData}>
                    {leave[5]}
                  </td>

                  <td style={tableData}>
                    {leave[6]}
                  </td>

                  <td style={tableData}>

                    <span
                      style={{
                        padding:
                          "8px 15px",

                        borderRadius:
                          "20px",

                        fontWeight:
                          "bold",

                        background:
                          leave[7] ===
                          "APPROVED"
                            ? "#16a34a"
                            : leave[7] ===
                              "REJECTED"
                            ? "#dc2626"
                            : "#eab308",

                        color:
                          "white",
                      }}
                    >
                      {leave[7]}
                    </span>

                  </td>

                  <td style={tableData}>

                    <div
                      style={{
                        display:
                          "flex",

                        justifyContent:
                          "center",

                        gap: "12px",
                      }}
                    >

                      {/* APPROVE */}

                      <button
                        onClick={() =>
                          approveLeave(
                            leave[0]
                          )
                        }
                        style={
                          approveButton
                        }
                      >
                        Approve
                      </button>

                      {/* REJECT */}

                      <button
                        onClick={() =>
                          rejectLeave(
                            leave[0]
                          )
                        }
                        style={
                          rejectButton
                        }
                      >
                        Reject
                      </button>

                    </div>

                  </td>

                </tr>

              )
            )}

          </tbody>

        </table>

      </div>

    </div>
  );
}

// ====================================
// STYLES
// ====================================

const tableHead = {

  padding: "18px",

  color: "white",

  fontSize: "18px",

  fontWeight: "bold",
};

const tableData = {

  padding: "18px",

  color: "#f1f5f9",

  fontSize: "15px",
};

const approveButton = {

  background:
    "linear-gradient(45deg,#16a34a,#22c55e)",

  border: "none",

  padding: "10px 18px",

  borderRadius: "10px",

  color: "white",

  cursor: "pointer",

  fontWeight: "bold",

  boxShadow:
    "0 0 10px rgba(34,197,94,0.5)",
};

const rejectButton = {

  background:
    "linear-gradient(45deg,#dc2626,#ef4444)",

  border: "none",

  padding: "10px 18px",

  borderRadius: "10px",

  color: "white",

  cursor: "pointer",

  fontWeight: "bold",

  boxShadow:
    "0 0 10px rgba(239,68,68,0.5)",
};

const dashboardButton = {

  background:
    "linear-gradient(45deg,#2563eb,#3b82f6)",

  border: "none",

  padding: "12px 22px",

  borderRadius: "12px",

  color: "white",

  cursor: "pointer",

  fontWeight: "bold",

  fontSize: "16px",

  boxShadow:
    "0 0 15px rgba(59,130,246,0.5)",
};

const logoutButton = {

  background:
    "linear-gradient(45deg,#dc2626,#ef4444)",

  border: "none",

  padding: "12px 22px",

  borderRadius: "12px",

  color: "white",

  cursor: "pointer",

  fontWeight: "bold",

  fontSize: "16px",

  boxShadow:
    "0 0 15px rgba(239,68,68,0.5)",
};