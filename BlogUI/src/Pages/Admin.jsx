import React, { useEffect, useState } from 'react';
import Navbar from '../Components/Navbar';
import useAPI from '../Hooks/UseAPI';
import UserBox from '../Components/UserBox';

const Admin = () => {
  const [data, setData] = useState([]);
  const { getUsers } = useAPI();
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchAllUsers = async () => {
      try {
        const response = await getUsers(setError);
        setData(response);
      } catch (error) {
        console.log(error);
      }
    };

    fetchAllUsers();
  }, []);

  if (error)
    return (
      <div className="mx-40">
        <Navbar />
        <div className="text-red-600 border-2 border-red-600 flex justify-center w-full m-auto bg-red-100 font-semibold rounded-lg mb-3 py-1">
          {error}
        </div>
      </div>
    );

  return (
    <div className="mx-40">
      <Navbar />
      <div className='flex flex-col'>
      <h1 className='text-3xl font-light m-auto mt-8'>Admin Dashboard</h1>
      <div className='mt-8 text-xl font-light'>Number of Users: {data.length}</div>
      <div className="grid grid-cols-1 gap-4 mt-4">
        {data.map((data) => (
          <UserBox key={data.userId} user={data} />
        ))}
      </div>
      </div>
    </div>
  );
};

export default Admin;
