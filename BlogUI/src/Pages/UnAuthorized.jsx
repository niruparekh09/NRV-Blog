import React from "react";
import Navbar from "../Components/Navbar";

const UnAuthorized = () => {
  return (
    <div className="mx-40">
      <Navbar />
      <div className="flex justify-center mt-20 ">
        <img src="./UnAuth.png" alt="Logo" width="500" />
      </div>
    </div>
  );
};

export default UnAuthorized;
