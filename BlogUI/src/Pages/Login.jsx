import React, { useState } from "react";
import Navbar from "../Components/Navbar";
import { Link, useNavigate } from "react-router-dom";
import useAPI from "../Hooks/UseAPI";
import { useAuth } from "../Context/AuthContext";

const Login = () => {
  const [userId, setUserId] = useState("");
  const [password, setPassword] = useState("");
  const [loginError, setLoginError] = useState(false);
  const [validationError, setValidationError] = useState("");
  const { login } = useAPI();
  const navigate = useNavigate();
  const { login: authLogin } = useAuth();

  async function handleLogin(e) {
    e.preventDefault();
    setLoginError(false);

    // Validations for the Fields
    if (!userId && !password) {
      setValidationError("userId & Password can't be empty");
      return;
    }
    if (!userId) {
      setValidationError("User ID is required");
      return;
    }
    if (!password) {
      setValidationError("Password is required");
      return;
    }

    // Sending Post request to server to login the user with id & password
    try {
      const [token,role] = await login(userId, password, setLoginError);

      //Adding userId,token,role in Local Storage 
      //Encrypting all 3 things

      //----

      //Retrieving userId,token,role and decrytping it 
      //and adding it in context everytime application is re-rendered
      authLogin(userId, token,role);

      navigate("/"); // After successfull login, user is redirected to Home
    } catch (error) {
      console.error("Login Failed", error);
      setValidationError("");
    }
  }

  return (
    <div className="mx-40">
      <Navbar />
      <div className="border-2 border-opacity-20 rounded-lg border-white p-10 flex flex-col justify-center mt-20 m-auto max-w-screen-sm">
        {validationError && (
          <div className="text-red-600 border-2 border-red-600 flex justify-center w-96 m-auto bg-red-100 font-semibold rounded-lg mb-3 py-1">
            {validationError}
          </div>
        )}
        {loginError && (
          <div className="text-red-600 border-2 border-red-600 flex justify-center w-96 m-auto bg-red-100 font-semibold rounded-lg mb-3 py-1">
            userId or Password is wrong
          </div>
        )}
        <h3 className="text-2xl  m-auto">Login User</h3>
        <form onSubmit={handleLogin} className="flex flex-col m-auto w-96">
          <label htmlFor="userId" className="focus:text-purple-600  mb-2">
            User ID
          </label>
          <input
            type="text"
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
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
          <button
            type="submit"
            className="border-2 py-1 rounded-lg my-3 w-96 m-auto hover:border-purple-600 hover:text-purple-600"
          >
            Log-In
          </button>
        </form>

        <Link to="/register" className="m-auto hover:text-purple-600">
          Don&apos;t have account? Register here
        </Link>
      </div>
    </div>
  );
};

export default Login;
