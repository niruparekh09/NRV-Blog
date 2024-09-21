import React, { createContext, useContext, useState } from 'react';

// Create AuthContext
const AuthContext = createContext();

// Custom hook to use the AuthContext
export const useAuth = () => {
  return useContext(AuthContext);
};

// AuthProvider Component
export const AuthProvider = ({ children }) => {
  // Initialize the token, userId, and role states from localStorage, if present, or set to null if not.
  const [token, setToken] = useState(localStorage.getItem('token') || null);
  const [userId, setUserId] = useState(localStorage.getItem('userId') || null);
  const [role, setRole] = useState(localStorage.getItem('role') || null);

  // Function to handle login. It accepts the user's token, ID, and role and updates both the state and localStorage.
  const login = (userId, token, role) => {
    setToken(token); // Update the token state
    setUserId(userId); // Update the userId state
    setRole(role); // Update the role state

    // Store the token, userId, and role in localStorage to persist the session across page reloads
    localStorage.setItem('token', token);
    localStorage.setItem('userId', userId);
    localStorage.setItem('role', role);
  };

  // Function to handle logout. It clears both the state and localStorage.
  const logout = () => {
    setToken(null); // Clear the token state
    setUserId(null); // Clear the userId state
    setRole(null); // Clear the role state

    // Remove the token, userId, and role from localStorage to ensure the user is logged out across refreshes.
    localStorage.removeItem('token');
    localStorage.removeItem('userId');
    localStorage.removeItem('role');
  };

  return (
    <AuthContext.Provider value={{ token, userId, role, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
