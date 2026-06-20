export default function EmployeeDashboard() {

  return (

    <div
      style={{
        background: "#0f172a",
        minHeight: "100vh",
        padding: "30px",
        color: "white",
      }}
    >

      <h1
        style={{
          fontSize: "40px",
          fontWeight: "bold",
          marginBottom: "30px",
        }}
      >
        Employee Dashboard
      </h1>

      <div
        style={{
          display: "grid",
          gridTemplateColumns: "repeat(4,1fr)",
          gap: "20px",
        }}
      >

        <div
          style={{
            background: "#2563eb",
            padding: "25px",
            borderRadius: "20px",
          }}
        >
          <h2>Total Tasks</h2>

          <p
            style={{
              fontSize: "40px",
              fontWeight: "bold",
              marginTop: "10px",
            }}
          >
            24
          </p>
        </div>

        <div
          style={{
            background: "#16a34a",
            padding: "25px",
            borderRadius: "20px",
          }}
        >
          <h2>Attendance</h2>

          <p
            style={{
              fontSize: "40px",
              fontWeight: "bold",
              marginTop: "10px",
            }}
          >
            96%
          </p>
        </div>

        <div
          style={{
            background: "#9333ea",
            padding: "25px",
            borderRadius: "20px",
          }}
        >
          <h2>Performance</h2>

          <p
            style={{
              fontSize: "40px",
              fontWeight: "bold",
              marginTop: "10px",
            }}
          >
            4.8⭐
          </p>
        </div>

        <div
          style={{
            background: "#dc2626",
            padding: "25px",
            borderRadius: "20px",
          }}
        >
          <h2>Leave Balance</h2>

          <p
            style={{
              fontSize: "40px",
              fontWeight: "bold",
              marginTop: "10px",
            }}
          >
            12
          </p>
        </div>

      </div>

    </div>
  );
}