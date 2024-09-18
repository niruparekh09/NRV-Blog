import React, { useState } from "react";
import Navbar from "../Components/NavBar";
import { Link, useNavigate } from "react-router-dom";
import useAPI from "../Hooks/UseAPI";
import { useAuth } from "../Context/AuthContext";

const Login = () => {
  const [userID, setUserID] = useState("");
  const [password, setPassword] = useState("");
  const [loginError, setLoginError] = useState(false);
  const { login } = useAPI();
  const navigate = useNavigate();
  const { login: authLogin } = useAuth();

  async function handleLogin(e) {
    e.preventDefault();
    try {
      const token = await login(userID, password, setLoginError);
      authLogin(userID, token);
      navigate("/");
    } catch (error) {
      console.error("Login Failed", error);
    }
  }

  return (
    <div className="mx-40">
      <Navbar />
      <div className="border-2 border-opacity-20 rounded-lg border-white p-10 flex flex-col justify-center mt-32 m-auto max-w-screen-sm">
        {loginError && (
          <div className="text-red-600 border-2 border-red-600 flex justify-center w-96 m-auto bg-red-100 font-semibold rounded-lg mb-3 py-1">
            UserId or Password is wrong
          </div>
        )}
        <h3 className="text-2xl  m-auto">Login User</h3>
        <form onSubmit={handleLogin} className="flex flex-col m-auto w-96">
          <label htmlFor="userID" className="focus:text-green-300  mb-2">
            User ID
          </label>
          <input
            type="text"
            value={userID}
            onChange={(e) => setUserID(e.target.value)}
            className="focus:border-green-300  border-2 outline-none rounded-lg mb-3 p-1"
          />
          <label htmlFor="userID" className="focus:text-green-300  mb-2">
            Password
          </label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="focus:border-green-300  border-2  outline-none rounded-lg mb-3 p-1"
          />
          <button
            type="submit"
            className="border-2 py-1 rounded-lg my-3 w-96 m-auto hover:bg-green-500"
          >
            Log-In
          </button>
        </form>

        <Link to="/register" className="m-auto hover:text-green-300">
          Don&apos;t have account? Register here
        </Link>
      </div>
    </div>
  );
};

export default Login;
