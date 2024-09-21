import React from "react";
import { Link } from "react-router-dom";

const UserBox = ({ user }) => {
  return (
<div className="border p-4 rounded shadow hover:shadow-lg transition-shadow duration-200 grid grid-cols-3 gap-4 items-center">
  <h1 className="text-lg truncate">{user.userName}</h1>
  <h1 className="text-lg text-center">{user.role === "ROLE_ADMIN" ? "Admin" : "User"}</h1>
  <Link to={`/user/${user.userId}`} className="text-lg text-right">
    <button className="border border-white border-opacity-80 px-2 rounded-lg hover:border-purple-600 hover:text-purple-600">
      Info.
    </button>
  </Link>
</div>

  );
};

export default UserBox;
