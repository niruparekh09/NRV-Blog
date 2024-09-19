import React, { createContext, useContext, useState } from "react";

// Create AuthContext
const AuthContext = createContext();

// Custom hook to use the AuthContext
export const useAuth = () => {
  return useContext(AuthContext);
};

// AuthProvider Component
export const AuthProvider = ({ children }) => {
  const [token, setToken] = useState(null);
  const [userId, setUserId] = useState(null);
  const [role, setRole] = useState(null);

  const login = (userId, token, role) => {
    setToken(token);
    setUserId(userId);
    setRole(role);
  };

  const logout = () => {
    setToken(null);
    setUserId(null);
    setRole(null);
    console.log("Inside Logout");
  };

  return (
    <AuthContext.Provider value={{ token, userId, role, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
