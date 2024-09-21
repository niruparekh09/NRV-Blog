import React from "react";
import Navbar from "../Components/Navbar";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faXTwitter,
  faLinkedin,
  faGithub,
  faReddit,
  faInstagram,
} from "@fortawesome/free-brands-svg-icons";
import { faEnvelope } from "@fortawesome/free-solid-svg-icons";

const Aboutme = () => {
  return (
    <div className="mx-40">
      <Navbar />
      <div className="border-2 border-opacity-20 rounded-lg border-white flex justify-between mt-16 m-auto max-w-screen-md">
        <div className="w-4/6 flex flex-col space-y-4 p-6">
          <h1 className="text-2xl font-semibold ">Hola! This Is NRV</h1>
          <p className="text-gray-200">
            This website has been created so that I can post my blogs and share
            my ideas with you.
          </p>

          <h1 className="text-2xl font-semibold ">Who Am I?</h1>
          <p className="text-gray-200">
            I am a Full Stack Developer working with React.js, Next.js, Spring
            Boot, and Spring Security. I am a Big TECH LOVER.
          </p>

          <h1 className="text-2xl font-semibold ">What Can You See Here?</h1>
          <p className="text-gray-200">
            Here it’s all about Tech, MotoGP, F1, and {"<Code/>"}
          </p>
        </div>

        <img
          src="./About.png"
          alt="About"
          className="h-auto w-1/4  mx-6 mb-6"
        />
      </div>
      <div className="flex flex-col justify-center mt-8">
        <h1 className="text-3xl m-auto font-light">Connect With Me</h1>
        <div className="flex justify-between mt-8 m-auto max-w-screen-md w-[768px]">
          <div>
            <a
              href="http://linkedin.com/in/nirav-parekh-2b3310197"
              target="_blank"
              rel="noopener noreferrer"
            >
              <FontAwesomeIcon icon={faLinkedin} className="fa-2x" />
            </a>
          </div>
          <div>
            <a
              href="http://github.com/niruparekh09"
              target="_blank"
              rel="noopener noreferrer"
            >
              <FontAwesomeIcon icon={faGithub} className="fa-2x" />
            </a>
          </div>
          <div>
            <a
              href="http://x.com/niruparekh09"
              target="_blank"
              rel="noopener noreferrer"
            >
              <FontAwesomeIcon icon={faXTwitter} className="fa-2x" />
            </a>
          </div>
          <div>
            <a
              href="http://reddit.com/u/NRV__"
              target="_blank"
              rel="noopener noreferrer"
            >
              <FontAwesomeIcon icon={faReddit} className="fa-2x" />
            </a>
          </div>
          <div>
            <a
              href="http://instagram.com/nrv__93"
              target="_blank"
              rel="noopener noreferrer"
            >
              <FontAwesomeIcon icon={faInstagram} className="fa-2x" />
            </a>
          </div>
          <div>
            <a href="mailto:niravcdac@outlook.com">
              <FontAwesomeIcon icon={faEnvelope} className="fa-2x" />
            </a>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Aboutme;
