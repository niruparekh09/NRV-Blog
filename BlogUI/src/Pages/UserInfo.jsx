import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import useAPI from '../Hooks/UseAPI';
import Navbar from '../Components/Navbar';
import BlogBox from '../Components/BlogBox';
import Popup from 'reactjs-popup';
import LoadingSpinner from '../Components/LoadingSpinner';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCircleXmark } from '@fortawesome/free-solid-svg-icons';

const UserInfo = () => {
  const [userData, setUserData] = useState();
  const { userId } = useParams();
  const { getUserById } = useAPI();
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const response = await getUserById(userId, setError);
        setUserData(response);
      } catch (error) {
        console.log(error);
      }
    };

    fetchUser();
  }, [userId]);

  async function handleDeleteUser() {}

  if (error)
    return (
      <div className="mx-40">
        <Navbar />
        <div className="text-red-600 border-2 border-red-600 flex justify-center w-full m-auto bg-red-100 font-semibold rounded-lg mb-3 py-1">
          {error}
        </div>
      </div>
    );

  if (!userData) return <LoadingSpinner />;

  return (
    <div className="mx-40">
      <Navbar />
      <div className=" flex flex-col border rounded-lg border-white px-10 py-5  mt-20 text-2xl font-light ">
        <div className="flex justify-between mb-2 border-b-2 border-opacity-20 border-white">
          <span>Id:</span>
          <span>{userData.userId}</span>
        </div>
        <div className="flex justify-between mb-2 border-b-2 border-opacity-20 border-white">
          <span>Name:</span>
          <span>{userData.userName}</span>
        </div>
        <div className="flex justify-between mb-2 border-b-2 border-opacity-20 border-white">
          <span>Role:</span>
          <span>{userData.role === 'ROLE_ADMIN' ? 'Admin' : 'User'}</span>
        </div>
        <div className="flex justify-between mb-2 border-b-2 border-opacity-20 border-white">
          <span>Account Creation:</span>
          <span>{userData.userCreationDate}</span>
        </div>
      </div>
      {userData.role !== 'ROLE_ADMIN' && (
        <div className="flex m-auto justify-center mt-10">
          <Popup
            trigger={
              <button className="ml-3 px-16 rounded-lg border hover:border-red-500 py-2 font-bold hover:text-red-500 text-lg">
                Delete The User
              </button>
            }
            modal
            nested
            overlayStyle={{
              background: 'rgba(0, 0, 0, 0.5)',
              backdropFilter: 'blur(5px)',
            }} // Add blur effect
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
                    Are you sure you want to Delete This User?
                  </div>
                  <div className="actions w-full p-[10px] px-[5px] text-[20px] text-center flex justify-between">
                    <button
                      className="button mr-2 text-red-500"
                      onClick={handleDeleteUser}
                    >
                      Yes, Delete
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
      <div className="flex flex-col justify-center mt-8">
        <div className="text-3xl m-auto font-light">
          Blogs By {userData.userName}
        </div>
        {!(userData.blogList.length === 0) ? (
          <div className="grid grid-cols-1 gap-4 mt-8">
            {userData.blogList.map((blog) => (
              <BlogBox key={blog.blogId} blog={blog} />
            ))}
          </div>
        ) : (
          <div className="border p-4 rounded shadow hover:shadow-lg transition-shadow duration-200 flex justify-center mt-8">
            <h1 className="text-lg">No Blogs Posted</h1>
          </div>
        )}
      </div>
    </div>
  );
};

export default UserInfo;
