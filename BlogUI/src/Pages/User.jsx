import React, { useState } from 'react';
import Navbar from './../Components/Navbar';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faAngleDown } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';
import useAPI from '../Hooks/UseAPI';
import { useAuth } from '../Context/AuthContext';
import Popup from 'reactjs-popup';
import { faCircleXmark } from '@fortawesome/free-solid-svg-icons';
//import 'reactjs-popup/style.css';

const User = () => {
  const [newPassword, setNewPassword] = useState('');
  const [confirmNewPassword, setConfirmNewPassword] = useState('');
  const [passwordValidation, setPasswordValidation] = useState('');
  const [passwordDropDown, setPasswordDropDown] = useState(false);
  const [userUpdateError, setUserUpdateError] = useState(false);
  const [userUpdateErrorMessage, setUserUpdateErrorMessage] = useState('');
  const navigate = useNavigate();
  const { userUpdate, deleteUser } = useAPI();
  const { logout } = useAuth();

  function togglePasswordDropDown() {
    setPasswordDropDown(!passwordDropDown);
  }

  async function handlePasswordChange(e) {
    e.preventDefault();
    if (newPassword !== confirmNewPassword) {
      setPasswordValidation('Passwords do not match');
    }

    //Sending PUT Request to server to register the user
    try {
      await userUpdate(
        newPassword,
        setUserUpdateError,
        setUserUpdateErrorMessage
      );

      navigate('/login');
    } catch (error) {
      console.error('Registration Failed', error);
      setPasswordValidation('');
    }
  }

  function handleLogin() {
    logout();
    navigate('/');
  }

  async function handleDeleteUser() {
    try {
      await deleteUser();
      logout(); // To remove all the information from context
      navigate('/');
    } catch (error) {
      console.error('Deletion Failed ' + error);
    }
  }

  return (
    <div className="mx-40">
      <Navbar />
      <div
        className={`border-2 border-opacity-20 ${
          passwordDropDown ? 'rounded-t-lg' : 'rounded-lg'
        } border-white p-5 flex justify-between mt-32 m-auto max-w-screen-sm`}
      >
        <h2 className="text-lg font-semibold">Change Password</h2>
        <button className="py-1 px-2" onClick={togglePasswordDropDown}>
          <FontAwesomeIcon
            icon={faAngleDown}
            className={`transition-transform duration-300 ease-in-out ${
              passwordDropDown ? 'rotate-180' : ''
            }`}
          />
        </button>
      </div>
      <div
        className={`overflow-hidden transition-all duration-300 ease-in-out border border-opacity-5 bg-white bg-opacity-5 rounded-b-lg border-white p-5 m-auto max-w-screen-sm ${
          passwordDropDown ? 'max-h-screen opacity-100' : 'max-h-0 opacity-0'
        }`}
      >
        {passwordDropDown && (
          <form
            onSubmit={handlePasswordChange}
            className=" flex flex-col bg-transparent m-auto w-96"
          >
            {userUpdateError && (
              <div className=" text-red-600 border-2 border-red-600 flex justify-center w-96 m-auto bg-red-100 font-semibold rounded-lg mb-3 py-1">
                {userUpdateErrorMessage}
              </div>
            )}
            {passwordValidation && (
              <div className=" text-red-600 border-2 border-red-600 flex justify-center w-96 m-auto bg-red-100 font-semibold rounded-lg mb-3 py-1">
                {passwordValidation}
              </div>
            )}
            <label
              htmlFor="userId"
              className="focus:text-purple-600  mb-2 bg-transparent"
            >
              New Password
            </label>
            <input
              type="password"
              value={newPassword}
              onChange={(e) => setNewPassword(e.target.value)}
              className="focus:border-purple-600  border-2 outline-none rounded-lg mb-3 p-1 bg-transparent"
            />
            <label htmlFor="userId" className=" mb-2 bg-transparent">
              Confirm New Password
            </label>
            <input
              type="password"
              value={confirmNewPassword}
              onChange={(e) => setConfirmNewPassword(e.target.value)}
              className="focus:border-purple-600  border-2 outline-none rounded-lg mb-3 p-1 bg-transparent"
            />
            <button
              type="submit"
              className="border-2 py-1 rounded-lg my-3 w-96 m-auto hover:border-purple-600 hover:text-purple-600"
            >
              Change Password
            </button>
          </form>
        )}
      </div>
      <div className="border-2 border-opacity-20 rounded-lg border-white p-5 flex justify-between mt-5 m-auto max-w-screen-sm">
        <span>
          Logout The User
          <button
            className="ml-3 px-10 rounded-lg border py-1 bg-gray-800 font-bold text-purple-600 border-none text-lg"
            onClick={handleLogin}
          >
            Logout
          </button>
        </span>
        <span>
          Delete the user
          <Popup
            trigger={
              <button className="ml-3 px-10 rounded-lg border py-1 bg-gray-800 font-bold text-red-500 border-none text-lg">
                Delete user
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
                <div className="modal rounded-lg shadow-lg p-4 w-[300px]">
                  <button
                    className="close cursor-pointer absolute block p-1 leading-5 right-[-10px] top-[-10px] text-[24px] rounded-full bg-none"
                    onClick={close}
                  >
                    <FontAwesomeIcon icon={faCircleXmark} />
                  </button>
                  <div className="header w-full border-b border-gray-400 text-[18px] text-center p-[5px]">
                    Confirm Deletion
                  </div>
                  <div className="content w-full p-[10px] px-[5px] text-center">
                    Are you sure you want to delete your account?
                  </div>
                  <div className="actions w-full p-[10px] px-[5px] text-center">
                    <button
                      className="button mr-2 text-red-500"
                      onClick={handleDeleteUser}
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
        </span>
      </div>
    </div>
  );
};

export default User;
