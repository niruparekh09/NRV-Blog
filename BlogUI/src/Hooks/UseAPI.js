import axios from 'axios';
import { useAuth } from '../Context/AuthContext';

const BASE_BLOG_URL = 'http://localhost:8080/api/v1/blog';
const BASE_USER_URL = 'http://localhost:8080/api/v1/user';

const useAPI = () => {
  const { token, userId } = useAuth();

  // User Operations

  // User Login
  const login = async (userId, password, setLoginError) => {
    try {
      const response = await axios.post(`${BASE_USER_URL}/login`, {
        userId,
        password,
      });

      const token = response.data.jwtToken;
      const role = response.data.role;
      //console.log([token, role]);

      setLoginError(false); // Clear any previous errors
      return [token, role]; // Returning the token and role so that we can set context in context
    } catch (err) {
      console.log(err);
      setLoginError(true);
      throw err; // Optionally re-throw the error for further handling
    }
  };

  // User Registration
  const register = async (
    userId,
    username,
    password,
    setRegisterError,
    setErrorMessage
  ) => {
    try {
      await axios.post(`${BASE_USER_URL}/register`, {
        userId,
        userName: username,
        password,
      });

      setRegisterError(false);
    } catch (error) {
      console.log(error);
      setRegisterError(true);

      console.log(error);
      const message = error.response?.data?.message || 'Registration failed'; // Extracting the error message
      setErrorMessage(message);
      throw error;
    }
  };

  // Get All Users
  const getUsers = async (setError) => {
    try {
      const response = await axios.get(BASE_USER_URL);
      console.log(response.data);
      return response.data;
    } catch (error) {
      console.log(error);
      setError(error.response?.data?.message || error.message); // Check if setError exists
      return error.response?.data?.message || 'User List Fetching Failed';
    }
  };

  // Get A User By Id
  const getUserById = async (userId, setError) => {
    try {
      const response = await axios.get(`${BASE_USER_URL}/${userId}`);
      console.log(response.data);
      return response.data;
    } catch (error) {
      console.log(error);
      setError(
        error.reponse?.data?.message || `User with ${userId} not Fetched`
      );
      return error.reponse?.data?.message || `User with ${userId} not Fetched`;
    }
  };

  // User Update (only password)
  const userUpdate = async (
    newPassword,
    setUserUpdateError,
    setUserUpdateErrorMessage
  ) => {
    try {
      axios.put(
        `${BASE_USER_URL}/${userId}`,
        {
          userId, // <-- From Context Which will give us the current logged in user
          password: newPassword,
        },
        {
          headers: {
            // Setting Header
            Authorization: `Bearer ${token}`, // Include the JWT token in the Authorization header
          },
        }
      );
    } catch (error) {
      console.log(error);
      setUserUpdateError(true);

      const message = error.response?.data?.message || 'User Update failed'; // Extracting the error message
      setUserUpdateErrorMessage(message);
    }
  };

  // Delete a User
  const deleteUser = async () => {
    try {
      const reponse = await axios.delete(`${BASE_USER_URL}/${userId}`, {
        headers: {
          // Setting Header
          Authorization: `Bearer ${token}`, // Include the JWT token in the Authorization header
        },
      });

      return reponse;
    } catch (error) {
      console.log(error.response?.data?.message);

      return error.response?.data?.message || 'User Deletion failed';
    }
  };

  // Get All Blogs
  const getAllBlogs = async (setError) => {
    try {
      const response = await axios.get(BASE_BLOG_URL);
      return response.data; // Return the blog data
    } catch (err) {
      console.error(err);
      setError(err.response?.data?.message || err.message);
      return err.response?.data?.message || err.message; // Re-throw for handling in the component
    }
  };

  // Get a Blog by ID
  const getBlogById = async (id) => {
    try {
      const response = await axios.get(`${BASE_BLOG_URL}/${id}`);
      return response.data; // Return the specific blog data
    } catch (err) {
      console.error(err);
      throw err; // Re-throw for handling in the component
    }
  };

  // Add a Blog
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
      return response.data;
    } catch (error) {
      console.error(error);
      setAddBlogError(true);
      return error.response?.data?.message || 'Blog Addition failed';
    }
  };

  // Delete A Blog

  const deleteBlog = async (blogId) => {
    try {
      const reponse = await axios.delete(`${BASE_BLOG_URL}/${blogId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      return reponse;
    } catch (error) {
      console.log(error);
      return error.response?.data?.message || 'Blog Deletion failed';
    }
  };

  // You can add more functions here as needed

  return {
    login,
    register,
    getUsers,
    getUserById,
    userUpdate,
    deleteUser,
    getAllBlogs,
    getBlogById,
    addBlog,
    deleteBlog,
  };
};

export default useAPI;
