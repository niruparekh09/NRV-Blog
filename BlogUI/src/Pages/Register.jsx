import React from "react";
import Navbar from "./../Components/Navbar";
import { useState } from "react";
import useAPI from "../Hooks/UseAPI";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faArrowLeftLong } from "@fortawesome/free-solid-svg-icons";
import { useNavigate } from "react-router-dom";


const Register = () => {
  const [userId, setUserId] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [validationError, setValidationError] = useState("");
  const [registerError, setRegisterError] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");
  const { register } = useAPI();
  const navigate = useNavigate();

  async function handleRegistration(e) {
    e.preventDefault();

    //Validations for fields
    if (!userId || !username || !password || !confirmPassword) {
      setValidationError("Enter all the fields");
      return;
    }

    if (password !== confirmPassword) {
      setValidationError("Passwords do not match");
      return;
    }

    //Sending POST Request to server to register the user
    try {
      await register(
        userId,
        username,
        password,
        setRegisterError,
        setErrorMessage
      ); // Sending Request

      navigate("/login");
    } catch (error) {
      console.error("Registration Failed", error);
      setValidationError("");
    }
  }

  return (
    <div className="mx-40 relative">
      <Navbar />
      <button
        className="absolute border-2 p-3 opacity-80 rounded-lg hover:opacity-100 hover:border-purple-600"
        onClick={() => navigate(-1)}
      >
        <FontAwesomeIcon
          icon={faArrowLeftLong}
          className="fa-1x"
        />
      </button>
      <div className="border-2 border-opacity-20 rounded-lg border-white p-10 flex flex-col justify-center mt-32 m-auto max-w-screen-sm">
        {validationError && (
          <div className="text-red-600 border-2 border-red-600 flex justify-center w-96 m-auto bg-red-100 font-semibold rounded-lg mb-3 py-1">
            {validationError}
          </div>
        )}
        {registerError && (
          <div className="text-red-600 border-2 border-red-600 flex justify-center w-96 m-auto bg-red-100 font-semibold rounded-lg mb-3 py-1">
            {errorMessage}
          </div>
        )}
        <h3 className="text-2xl  m-auto">Register User</h3>
        <form
          onSubmit={handleRegistration}
          className="flex flex-col m-auto w-96"
        >
          <label htmlFor="userId" className="focus:text-purple-600  mb-2">
            User ID
          </label>
          <input
            type="text"
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
            className="focus:border-purple-600  border-2 outline-none rounded-lg mb-3 p-1"
          />
          <label htmlFor="userId" className="focus:text-purple-600  mb-2">
            User Name
          </label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            className="focus:border-purple-600  border-2 outline-none rounded-lg mb-3 p-1"
          />
          <label htmlFor="userId" className="  mb-2">
            Password
          </label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="focus:border-purple-600  border-2  outline-none rounded-lg mb-3 p-1"
          />
          <label htmlFor="userId" className="  mb-2">
            New Password
          </label>
          <input
            type="password"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
            className="focus:border-purple-600  border-2  outline-none rounded-lg mb-3 p-1"
          />
          <button
            type="submit"
            className="border-2 py-1 rounded-lg my-3 w-96 m-auto hover:border-purple-600 hover:text-purple-600"
          >
            Register
          </button>
        </form>
      </div>
    </div>
  );
};

export default Register;
