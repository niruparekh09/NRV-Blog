import React, { useEffect, useState } from "react";
import Navbar from "./../Components/Navbar";
import { useParams, useNavigate } from "react-router-dom";
import useAPI from "../Hooks/UseAPI";
import LoadingSpinner from "../Components/LoadingSpinner";
import ReactMarkdown from "react-markdown";
import Popup from "reactjs-popup";
import { useAuth } from "../Context/AuthContext";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCircleXmark } from "@fortawesome/free-solid-svg-icons";

const Blog = () => {
  const { blogId } = useParams(); // To fetch blogId from url /blog/:blogId
  const [blog, setBlog] = useState();
  const { getBlogById,deleteBlog } = useAPI();
  const {userId,role} = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    const fetchBlog = async () => {
      console.log("Fetched blogId:", blogId); // Debugging line

      if (!blogId) {
        console.error("blogId is undefined");
        return; // Exit if blogId is not defined
      }

      try {
        const fetchedBlog = await getBlogById(blogId); // Implement this in your API hooks
        setBlog(fetchedBlog);
        console.log(blog);
      } catch (error) {
        console.error("Failed to fetch blog:", error);
      }
    };

    fetchBlog();
  }, [blogId]);

  async function handleDeleteBlog(){
    try {
      const reponse = await deleteBlog(blogId)
      console.log(reponse)
      navigate("/")
    } catch (error) {
      console.log("Deletion Failed", error)
    }
  }
  

  if (!blog) return <LoadingSpinner />;

  return (
    <div className="mx-40">
      <Navbar />
      <div key={blog.blogId} className="blog-post mb-12">
        <h2 className="text-5xl text-white font-mono text-center mb-4">
          {blog.blogTitle}
        </h2>
        {/* Centering content */}
        <ReactMarkdown className=" markdown-body">
          {blog.blogContent.replace(/\/n\/n/g, "\n")}
        </ReactMarkdown>
        <h3 className="text-3xl text-white font-mono text-center mt-4">
          Author: {blog.user.userName}
        </h3>
        {(userId === blog.user.userId) || (role === "ROLE_ADMIN")  && (
          <div className="flex m-auto justify-center mt-10">
            <Popup
              trigger={
                <button className="ml-3 px-16 rounded-lg border hover:border-red-500 py-2 font-bold hover:text-red-500 text-lg">
                  Delete The Blog
                </button>
              }
              modal
              nested
              overlayStyle={{
                background: "rgba(0, 0, 0, 0.5)",
                backdropFilter: "blur(5px)",
              }}
            >
              {(close) => (
                <div className="flex items-center justify-center h-full">
                  <div className="modal rounded-lg shadow-lg p-4 w-[400px]">
                    <button
                      className="close cursor-pointer absolute block p-1 leading-5 right-[-10px] top-[-10px] text-[24px] rounded-full bg-none"
                      onClick={close}
                    >
                      <FontAwesomeIcon icon={faCircleXmark} />
                    </button>
                    <div className="header w-full border-b border-gray-400 text-[24px] text-center p-[5px]">
                      Confirm Deletion
                    </div>
                    <div className="content w-full p-[10px] px-[5px] text-[20px] text-center">
                      Are you sure you want to delete your Blog?
                    </div>
                    <div className="actions w-full p-[10px] px-[5px] text-[20px] text-center flex justify-between">
                      <button
                        className="button mr-2 text-red-500"
                        onClick={handleDeleteBlog}
                      >
                        Yes, delete
                      </button>
                      <button className="button" onClick={close}>
                        Cancel
                      </button>
                    </div>
                  </div>
                </div>
              )}
            </Popup>
          </div>
        )}
      </div>
    </div>
  );
};

export default Blog;
