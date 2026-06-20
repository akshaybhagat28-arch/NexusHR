// ===========================
// ManagerDashboard.jsx
// ===========================

export default function ManagerDashboard() {

  const team = [

    {
      id: 1,
      name: "Akshay Singh",
      role: "Frontend Developer",
    },

    {
      id: 2,
      name: "Rahul Sharma",
      role: "Backend Developer",
    },

    {
      id: 3,
      name: "Priya Verma",
      role: "HR Manager",
    },
  ];

  return (

    <div
      style={{
        background: "#111827",
        minHeight: "100vh",
        padding: "30px",
        color: "white",
      }}
    >

      <h1
        style={{
          fontSize: "40px",
          marginBottom: "30px",
        }}
      >
        Manager Dashboard
      </h1>

      <div
        style={{
          background: "#1f2937",
          padding: "30px",
          borderRadius: "20px",
        }}
      >

        <h2
          style={{
            marginBottom: "20px",
          }}
        >
          Team Overview
        </h2>

        <table
          style={{
            width: "100%",
          }}
        >

          <thead>

            <tr>

              <th>Name</th>

              <th>Role</th>

            </tr>

          </thead>

          <tbody>

            {team.map((member) => (

              <tr key={member.id}>

                <td>{member.name}</td>

                <td>{member.role}</td>

              </tr>

            ))}

          </tbody>

        </table>

      </div>

    </div>
  );
}