import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./Pages/Home";
import Aboutme from "./Pages/Aboutme";
import Login from "./Pages/Login";
import Register from "./Pages/Register";
import AddABlog from "./Pages/AddABlog";
import User from "./Pages/User";
import { AuthProvider } from "./Context/AuthContext";
import Admin from "./Pages/Admin";
import NotFound from "./Pages/NotFound";

const App = () => {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/addBlog" element={<AddABlog />} />
          <Route path="/login" element={<Login />} />
          <Route path="/user" element={<User />} />
          <Route path="/admin" element={<Admin />} />
          <Route path="/aboutme" element={<Aboutme />} />
          <Route path="/register" element={<Register />} />

          <Route path="*" element={<NotFound />} />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
};

export default App;
