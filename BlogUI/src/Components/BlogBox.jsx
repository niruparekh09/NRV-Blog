import React from "react";
import { Link } from "react-router-dom";

const BlogBox = ({ blog }) => {
  return (
    <div className="border p-4 rounded shadow hover:shadow-lg transition-shadow duration-200 flex justify-between">
      <h1 className="text-lg">{blog.blogTitle}</h1>
      <Link to={`/blog/${blog.blogId}`} className="text-lg">
      <button className="border border-white border-opacity-80 px-2 rounded-lg hover:border-purple-600 hover:text-purple-600">Read</button>
      </Link>
    </div>
  );
};

export default BlogBox;
