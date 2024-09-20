import React from "react";
import { useAuth } from "../Context/AuthContext";
import { Navigate, Route } from "react-router-dom";

const ProtectedRoute = ({ element, requiredRole, ...rest }) => {
  const { token, role } = useAuth(); // Access token and role from context

  const isAuthorized = () => {
    // Check if user is logged in
    if (!token) return false;

    //If Role is present as a Prop then the Route should be only Accessible to Admin
    // If Required Role is ADMIN. Getting Role from App.jsx and confirming it with context.
    if (requiredRole && role !== requiredRole) return false;

    return true;
  };

  return isAuthorized() ? (
    element
  ) : requiredRole ? (
    <Navigate to="/not-authorized" replace />
  ) : (
    <Navigate to="/login" replace />
  );
};

export default ProtectedRoute;
