import React from 'react';
import Navbar from '../Components/NavBar';
import { useState } from 'react';
import { Link } from 'react-router-dom';

const Login = () => {
  const [userName, setUserName] = useState('');
  const [password, setPassword] = useState('');

  function handleLogin(e) {
    e.preventDefault();
  }

  return (
    <div className="mx-40">
      <Navbar />
      <div className="border-2 border-opacity-20 rounded-lg border-white p-10 flex flex-col justify-center mt-32 m-auto max-w-screen-sm">
        <h3 className="text-2xl  m-auto">Login User</h3>
        <form onSubmit={handleLogin} className="flex flex-col m-auto w-96">
          <label
            htmlFor="username"
            className="focus:text-green-300  mb-2"
          >
            User Name
          </label>
          <input
            type="text"
            value={userName}
            onChange={(e) => setUserName(e.target.value)}
            className="focus:border-green-300  border-2 outline-none rounded-lg mb-3 pt-1"
          />
          <label
            htmlFor="username"
            className="focus:text-green-300  mb-2"
          >
            Password
          </label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="focus:border-green-300  border-2  outline-none rounded-lg mb-3 pt-1"
          />
        </form>
        <Link to="/register" className="m-auto hover:text-green-300">
          Don&apos;t have account? Register here
        </Link>
      </div>
    </div>
  );
};

export default Login;
