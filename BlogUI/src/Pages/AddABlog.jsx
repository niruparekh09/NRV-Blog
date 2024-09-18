import React, { useState } from "react";
import Navbar from "../Components/NavBar";
import {useNavigate} from "react-router-dom";

const AddABlog = () => {
  const [ addBlogError, setAddBlogError ] = useState(false);
  const [ blogTitle, setBlogTitle ] = useState("");
  const [ blogContent, setBlogContent ] = useState("");
  const navigate = useNavigate();

  async function handleAddingABlog(e) {
    e.preventDefault();
    // try {

    // } catch (error) {

    // }
  }

  return (
    <div className="mx-40">
      <Navbar />
      <div className="border-2 border-opacity-20 rounded-lg border-white p-10 flex flex-col justify-center mt-32 m-auto max-w-screen-sm">
        {addBlogError && (
          <div className="text-red-600 border-2 border-red-600 flex justify-center w-96 m-auto bg-red-100 font-semibold rounded-lg mb-3 py-1">
            UserId or Password is wrong
          </div>
        )}
        <h3 className="text-2xl  m-auto">Add A Blog</h3>
        <form
          onSubmit={handleAddingABlog}
          className="flex flex-col m-auto w-96"
        >
          <label htmlFor="userID" className="focus:text-green-300  mb-2">
            Title
          </label>
          <input
            type="text"
            value={blogTitle}
            onChange={(e) => setBlogTitle(e.target.value)}
            className="focus:border-green-300  border-2 outline-none rounded-lg mb-3 p-1"
          />
          <label htmlFor="userID" className="focus:text-green-300  mb-2">
            Content
          </label>
          <input
            type="text"
            value={blogContent}
            onChange={(e) => setBlogContent(e.target.value)}
            className="focus:border-green-300  border-2  outline-none rounded-lg mb-3 p-1"
          />
          <button
            type="submit"
            className="border-2 py-1 rounded-lg my-3 w-96 m-auto hover:bg-green-500"
          >
            Add
          </button>
        </form>
      </div>
    </div>
  );
};

export default AddABlog;
