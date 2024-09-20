import React, { useEffect, useState } from "react";
import Navbar from "../Components/Navbar";
import useAPI from "../Hooks/UseAPI";
import BlogBox from "../Components/BlogBox";

const Home = () => {
  const [blogs, setBlogs] = useState([]);
  const { getAllBlogs } = useAPI();

  const selectRandomBlogs = (allBlogs, numToSelect) => {
    if (allBlogs.length > 0) {
      const blogsCopy = [...allBlogs];
      const selected = [];
      const size = Math.min(numToSelect, blogsCopy.length);
      
      for (let i = 0; i < size; i++) {
        const randomIndex = Math.floor(Math.random() * blogsCopy.length);
        selected.push(blogsCopy.splice(randomIndex, 1)[0]);
      }
      return selected;
    }
    return [];
  };
  

  useEffect(() => {
    const fetchBlogs = async () => {
      try {
        const allBlogs = await getAllBlogs();
        const randomBlogs = selectRandomBlogs(allBlogs, 10); // Randomly select 10 blogs
        setBlogs(randomBlogs);
      } catch (error) {
        console.error("Failed to fetch blogs:", error);
      }
    };
  
    fetchBlogs();
  }, []);

  return (
    <div className="mx-40">
      <Navbar />
      <div className="grid grid-cols-1 gap-4 mt-4">
        {blogs.map((blog) => (
          <BlogBox key={blog.blogId} blog={blog} />
        ))}
      </div>
    </div>
  );
};

export default Home;
