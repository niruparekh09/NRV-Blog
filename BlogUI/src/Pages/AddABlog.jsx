import React, { useState } from "react";
import Navbar from "../Components/Navbar";
import { useNavigate } from "react-router-dom";
import useAPI from "../Hooks/UseAPI";

const AddABlog = () => {
  const [addBlogError, setAddBlogError] = useState(false);
  const [blogTitle, setBlogTitle] = useState("");
  const [blogContent, setBlogContent] = useState("");
  const [validationError, setValidationError] = useState("");
  const [addError,setAddError] = useState("")
  const navigate = useNavigate();
  const {addBlog} = useAPI();

  async function handleAddingABlog(e) {
    e.preventDefault();
    try {
        const reponse = await addBlog(blogTitle, blogContent, setAddBlogError)
        console.log(reponse)
        navigate(`/blog/${reponse.blog.blogId}`)
     } catch (error) {
        console.log("Addition of Blog failed", error);

     }
  }

  return (
    <div className="mx-40">
      <Navbar />
      <div className="border-2 border-opacity-20 rounded-lg border-white p-10 flex flex-col justify-center mt-20 m-auto max-w-screen-sm">
        {addBlogError && (
          <div className="text-red-600 border-2 border-red-600 flex justify-center w-96 m-auto bg-red-100 font-semibold rounded-lg mb-3 py-1">
            UserId or Password is wrong
          </div>
        )}
        <h3 className="text-2xl  m-auto">Add A Blog</h3>
        <form
          onSubmit={handleAddingABlog}
          className="flex flex-col m-auto w-full"
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
            Content (Markdown only)
          </label>
          <textarea
            rows="5"
            cols="25"
            value={blogContent}
            onChange={(e) => setBlogContent(e.target.value)}
            className="focus:border-green-300  border-2  outline-none rounded-lg mb-3 p-1"
          />
          <button
            type="submit"
            className="border-2 py-1 rounded-lg my-3 w-full  m-auto hover:border-purple-600 hover:text-purple-600"
          >
            Add
          </button>
        </form>
      </div>
    </div>
  );
};

export default AddABlog;
