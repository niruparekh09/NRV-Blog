// src/components/Navbar.js
import React, { useState } from "react";
import { Link } from "react-router-dom";

const Navbar = () => {
  const [isOpen, setIsOpen] = useState(false);

  const toggleMenu = () => {
    setIsOpen(!isOpen);
  };

  return (
    <nav className="p-4">
      <div className=" mx-auto flex justify-between items-center">
        {/* Logo */}
        <div className="text-white text-xl font-bold">
          <Link to="/">NRV BLOG</Link>
        </div>

        {/* Desktop Menu */}
        <div className="hidden md:flex space-x-8">
          <Link to="/" className="text-white hover:text-gray-300">
            Home
          </Link>
          <Link to="/blog" className="text-white hover:text-gray-300">
            Blog
          </Link>
          <Link to="/contact" className="text-white hover:text-gray-300">
            Contact
          </Link>
          <Link to="/login" className="text-white hover:text-gray-300">
            Login
          </Link>
          <Link to="/aboutme" className="text-white hover:text-gray-300">
            About Me
          </Link>
        </div>

        {/* Mobile Menu Button */}
        <div className="md:hidden flex items-center">
          <button
            onClick={toggleMenu}
            className="text-white focus:outline-none"
          >
            {isOpen ? (
              <svg
                className="w-6 h-6"
                fill="none"
                stroke="currentColor"
                viewBox="0 0 24 24"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth="2"
                  d="M6 18L18 6M6 6l12 12"
                ></path>
              </svg>
            ) : (
              <svg
                className="w-6 h-6"
                fill="none"
                stroke="currentColor"
                viewBox="0 0 24 24"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth="2"
                  d="M4 6h16M4 12h16M4 18h16"
                ></path>
              </svg>
            )}
          </button>
        </div>
      </div>

      {/* Mobile Menu */}
      <div className={`md:hidden ${isOpen ? "block" : "hidden"}`}>
        <div className="flex flex-col space-y-2 mt-2">
          <Link
            to="/"
            className="text-white hover:text-gray-300 block px-4 py-2"
          >
            Home
          </Link>
          <Link
            to="/blog"
            className="text-white hover:text-gray-300 block px-4 py-2"
          >
            Blog
          </Link>
          <Link
            to="/contact"
            className="text-white hover:text-gray-300 block px-4 py-2"
          >
            Contact
          </Link>
          <Link
            to="/login"
            className="text-white hover:text-gray-300 block px-4 py-2"
          >
            Login
          </Link>
          <Link
            to="/aboutme"
            className="text-white hover:text-gray-300 block px-4 py-2"
          >
            About Me
          </Link>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
