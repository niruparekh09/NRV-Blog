import React, { useState, useEffect } from "react";
import { getAllBlogs } from "./Hooks/UseAPI"; // Ensure this path is correct
import ReactMarkdown from "react-markdown"; // Correct import

const App = () => {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true); // Track loading state
  const [error, setError] = useState(null); // Track error state

  useEffect(() => {
    const fetchData = async () => {
      try {
        const result = await getAllBlogs(); // Await the promise to get data
        setData(result); // Set state with the resolved data
      } catch (err) {
        console.error("Failed to fetch blogs:", err.message); // Handle errors
        setError(err.message); // Set error state
      } finally {
        setLoading(false); // Update loading state
      }
    };

    fetchData(); // Call the async function
  }, []);

  if (loading) return <p className="text-white">Loading...</p>; // Show loading indicator
  if (error) return <p className="text-white">Error: {error}</p>; // Show error message

  return (
    <div className="flex flex-col items-center p-4 bg-[#242535] min-h-screen">
      {/* Centering the content horizontally */}
      <div className="max-w-3xl w-full">
        {/* Limit the maximum width of the content */}
        {data.length === 0 ? (
          <p className="text-white text-center">No blogs available.</p> // Handle empty data
        ) : (
          data.map((blog) => (
            <div key={blog.blogId} className="blog-post mb-12">
              <h2 className="text-5xl text-white font-mono text-center mb-4">
                {blog.blogTitle}
              </h2>
              {/* Centering content */}
              <ReactMarkdown className=" markdown-body text-center">
                {blog.blogContent}
              </ReactMarkdown>
              <h3 className="text-3xl text-white font-mono text-center mt-4">
                Author: {blog.user.userName}
              </h3>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default App;
