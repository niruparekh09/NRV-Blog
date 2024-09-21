import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from './Pages/Home';
import Aboutme from './Pages/Aboutme';
import Login from './Pages/Login';
import Register from './Pages/Register';
import AddABlog from './Pages/AddABlog';
import User from './Pages/User';
import { AuthProvider } from './Context/AuthContext';
import Admin from './Pages/Admin';
import NotFound from './Pages/NotFound';
import UnAuthorized from './Pages/UnAuthorized';
import ProtectedRoute from './Components/ProtectedRoute';
import Blog from './Pages/Blog';
import UserInfo from './Pages/UserInfo';

const App = () => {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/aboutme" element={<Aboutme />} />
          <Route path="/register" element={<Register />} />
          <Route path="/blog/:blogId" element={<Blog />} />
          <Route path="/not-authorized" element={<UnAuthorized />} />
          <Route path="*" element={<NotFound />} />

          {/* Protected Routes */}
          <Route
            path="/addBlog"
            element={<ProtectedRoute element={<AddABlog />} />}
          />
          <Route path="/user" element={<ProtectedRoute element={<User />} />} />
          <Route
            path="/admin"
            element={
              <ProtectedRoute element={<Admin />} requiredRole="ROLE_ADMIN" />
            }
          />
          <Route
            path="/user/:userId"
            element={
              <ProtectedRoute
                element={<UserInfo />}
                requiredRole="ROLE_ADMIN"
              />
            }
          />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
};

export default App;
