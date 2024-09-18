import axios from "axios";
import { useAuth } from "../Context/AuthContext";

const BASE_BLOG_URL = "http://localhost:8080/api/v1/blog";
const BASE_USER_URL = "http://localhost:8080/api/v1/user";


const useAPI = () => {

  const {token,userId} = useAuth();

  const login = async (userId, password, setLoginError) => {
    try {
      const response = await axios.post(`${BASE_USER_URL}/login`, {
        userId,
        password,
      });
      const token = response.data.jwtToken; 
      console.log(token);
      setLoginError(false); // Clear any previous errors
      return token; // Optionally return the token
    } catch (err) {
      console.log(err);
      setLoginError(true);
      throw err; // Optionally re-throw the error for further handling
    }
  };

  const getAllBlogs = async () => {
    try {
      const response = await axios.get(BASE_BLOG_URL);
      return response.data; // Return the blog data
    } catch (err) {
      console.error(err);
      throw err; // Re-throw for handling in the component
    }
  };

  const getABlogWithId = async (id) => {
    try {
      const response = await axios.get(`${BASE_BLOG_URL}/${id}`);
      return response.data; // Return the specific blog data
    } catch (err) {
      console.error(err);
      throw err; // Re-throw for handling in the component
    }
  };

  const addBlog = async (blogTitle, blogContent, setAddBlogError) => {
    try {
      const response = await axios.post(
        `${BASE_BLOG_URL}/post`,
        {
          userId, // <--- Added via context
          blogTitle,
          blogContent,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setAddBlogError(false);
      return response;
    } catch (error) {
      console.error(error);
      setAddBlogError(true);
      throw error;
    }
  };

  // You can add more functions here as needed

  return { login, getAllBlogs, getABlogWithId, addBlog };
};

export default useAPI;
