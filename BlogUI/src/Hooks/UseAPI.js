const BASE_BLOG_URL = 'http://localhost:8080/api/v1/blog';
const BASE_USER_URL = 'http://localhost:8080/api/v1/user';

// Helper function to handle responses
function handleResponse(response) {
  return response.ok
    ? response.json()
    : response.json().then((errorData) => {
        throw new Error(errorData.message || response.statusText);
      });
}

// GET request for all blogs (no authentication needed)
function getAllBlogs() {
  return fetch(`${BASE_BLOG_URL}`, {
    method: 'GET',
  })
    .then(handleResponse)
    .catch(function (error) {
      throw new Error(error.message);
    });
}

function getABlogWithId(id) {
  return fetch(`${BASE_BLOG_URL}/${id}`, {
    method: 'GET',
  })
    .then(handleResponse)
    .catch(function (error) {
      throw new Error(error.message);
    });
}

export { getAllBlogs, getABlogWithId };
