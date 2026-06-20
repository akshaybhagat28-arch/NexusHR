import axios from "axios";

export default function Payslip() {

  const downloadPayslip = async () => {

    try {

      const employeeId = 1;
      const month = "May";

      const response = await axios.get(
        `http://localhost:8080/payslip/${employeeId}?month=${month}`,
        {
          responseType: "blob",
        }
      );

      // PDF Download
      const url = window.URL.createObjectURL(
        new Blob([response.data], {
          type: "application/pdf",
        })
      );

      const link = document.createElement("a");

      link.href = url;

      link.setAttribute(
        "download",
        `employee_${employeeId}_${month}_payslip.pdf`
      );

      document.body.appendChild(link);

      link.click();

      link.remove();

    } catch (error) {

      console.log(error);

      alert("Payslip Download Failed");
    }
  };

  return (

    <div className="min-h-screen flex items-center justify-center bg-gray-100">

      <button
        onClick={downloadPayslip}
        className="bg-green-600 hover:bg-green-700 text-white px-6 py-3 rounded-xl"
      >
        Download Payslip
      </button>

    </div>
  );
}