export default function PerformanceReview() {

  const reviews = [

    {
      id: 1,
      employee: "Akshay Singh",
      department: "IT",
      rating: 5,
      feedback: "Excellent Performance",
    },

    {
      id: 2,
      employee: "Rahul Sharma",
      department: "HR",
      rating: 4,
      feedback: "Very Good Communication",
    },

    {
      id: 3,
      employee: "Priya Verma",
      department: "Finance",
      rating: 3,
      feedback: "Need Improvement",
    },
  ];

  return (

    <div
      style={{
        padding: "30px",
        background: "#111827",
        minHeight: "100vh",
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
        Performance Review Module
      </h1>

      <div
        style={{
          display: "grid",
          gridTemplateColumns: "repeat(3,1fr)",
          gap: "20px",
        }}
      >

        {reviews.map((review) => (

          <div
            key={review.id}
            style={{
              background: "#1f2937",
              padding: "25px",
              borderRadius: "20px",
            }}
          >

            <h2
              style={{
                fontSize: "24px",
                fontWeight: "bold",
                marginBottom: "15px",
              }}
            >
              {review.employee}
            </h2>

            <p>
              Department: {review.department}
            </p>

            <p
              style={{
                marginTop: "10px",
                fontSize: "20px",
              }}
            >
              Rating: {"⭐".repeat(review.rating)}
            </p>

            <p
              style={{
                marginTop: "15px",
                color: "#d1d5db",
              }}
            >
              {review.feedback}
            </p>

          </div>

        ))}

      </div>

    </div>
  );
}