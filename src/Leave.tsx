
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useState, type FormEvent } from "react";

// IMPORT IMAGE
import bgImage from "./assets/t3.jpg";


export default function ApplyLeave() {

  const navigate = useNavigate();

  const token = localStorage.getItem("token");

  const [startDate, setStartDate] =
    useState("");

  const [endDate, setEndDate] =
    useState("");

  const [reason, setReason] =
    useState("");

  const [paid, setPaid] =
    useState(false);

  // APPLY LEAVE
  const applyLeave = async (
  e: FormEvent<HTMLFormElement>
) => {

    e.preventDefault();

    try {

      const leaveData = {

        startDate,
        endDate,
        reason,
        paid
      };

      await axios.post(
        "http://localhost:8080/api/leaves/apply",
        leaveData,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );


      
      alert("Leave Applied Successfully");

      navigate("/dashboard");

    } catch (error) {

      console.log(error);

      alert("Unable To Apply Leave");
      
    }
  };

  return (

    <div
      className="min-h-screen flex justify-center items-center p-5 bg-cover bg-center"
      style={{
        backgroundImage: `url(${bgImage})`,
      }}
    >

      {/* DARK OVERLAY */}
      <div className="absolute inset-0 bg-black/70"></div>

      {/* LEAVE BOX */}
      <div className="relative bg-gray-900/90 p-6 rounded-2xl w-full max-w-md shadow-2xl">

        <h1 className="text-2xl font-bold text-white mb-6 text-center">

          Apply Leave

        </h1>

        <form
          onSubmit={applyLeave}
          className="space-y-4"
        >

          {/* START DATE */}
          <div>

            <label className="block text-gray-300 mb-1 text-sm">
              Start Date
            </label>

            <input
              type="date"
              value={startDate}
              onChange={(e) =>
                setStartDate(e.target.value)
              }
              required
              className="w-full p-3 rounded-xl bg-gray-800 text-white outline-none border border-gray-700"
            />

          </div>

          {/* END DATE */}
          <div>

            <label className="block text-gray-300 mb-1 text-sm">
              End Date
            </label>

            <input
              type="date"
              value={endDate}
              onChange={(e) =>
                setEndDate(e.target.value)
              }
              required
              className="w-full p-3 rounded-xl bg-gray-800 text-white outline-none border border-gray-700"
            />

          </div>

          {/* REASON */}
          <div>

            <label className="block text-gray-300 mb-1 text-sm">
              Reason
            </label>

            <textarea
              value={reason}
              onChange={(e) =>
                setReason(e.target.value)
              }
              placeholder="Enter Leave Reason"
              required
              rows="3"
              className="w-full p-3 rounded-xl bg-gray-800 text-white outline-none border border-gray-700"
            />

          </div>

          {/* PAID LEAVE */}
          <div className="flex items-center gap-2">

            <input
              type="checkbox"
              checked={paid}
              onChange={(e) =>
                setPaid(e.target.checked)
              }
              className="w-4 h-4"
            />

            <label className="text-gray-300 text-sm">
              Paid Leave
            </label>

          </div>

          {/* BUTTONS */}
          <div className="flex gap-3 pt-2">

            <button
              type="submit"
              className="flex-1 bg-yellow-500 hover:bg-yellow-600 text-black font-bold py-3 rounded-xl"
            >
              Apply
            </button>

            <button
              type="button"
              onClick={() =>
                navigate("/dashboard")
              }
              className="flex-1 bg-gray-700 hover:bg-gray-800 text-white font-bold py-3 rounded-xl"
            >
              Back
            </button>

          </div>

        </form>

      </div>

    </div>
  );
}