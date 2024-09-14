import { useEffect, useState } from "react";
import axios from "axios";
import ReactMarkdown from "react-markdown";

function App() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const tempData = `
  # **Marc Márquez: The Maverick of MotoGP** 

In the world of MotoGP, few names resonate with the same intensity and excitement as Marc Márquez. As of September 2024, Márquez remains one of the most dominant and electrifying figures in motorcycle racing. This blog delves into his journey, achievements, and what makes him a true legend of the sport.

![Alt text](https://static.autox.com/uploads/2024/09/MotoGP-Marc-Marquez-2-.jpg)\

### **The Rise of a Racing Prodigy**

Marc Márquez's story is one of sheer determination and extraordinary talent. Born on February 17, 1993, in Cervera, Spain, Márquez's passion for motorcycles was evident from a young age. His rise through the ranks was nothing short of meteoric. By the time he made his MotoGP debut in 2013 with the Repsol Honda Team, he was already a force to be reckoned with.\

In his rookie season, Márquez shocked the racing world by winning the MotoGP World Championship. His aggressive riding style, combined with an uncanny ability to push the limits of both himself and his bike, set him apart. This victory was the first of many, marking the beginning of a career filled with remarkable achievements.\

### **The Era of Dominance**

Márquez's dominance in MotoGP was nothing short of spectacular. His ability to adapt, innovate, and consistently deliver top performances allowed him to secure six World Championships (2013, 2014, 2016, 2017, 2018, and 2019) by the end of the 2019 season. His fearless riding and technical brilliance earned him the nickname "The Marquez Effect," signifying the profound impact he had on the sport.\

However, the 2020 season brought unexpected challenges. Márquez suffered a serious shoulder injury during the Spanish Grand Prix, which led to a series of complications and surgeries. This injury not only sidelined him for much of the 2020 season but also significantly impacted his performance in 2021 and 2022. Despite these setbacks, Márquez's resilience and determination remained unwavering.\

### **The Comeback and Current Status**

As of late 2023 and early 2024, Márquez has been on a path of recovery and resurgence. After a challenging period of rehabilitation and adaptation, he returned to competitive racing with a renewed focus and determination. His performances in the 2023 season were promising, with several podium finishes and strong showings that hinted at his return to top form.\

In the 2024 season, Márquez continues to be a formidable competitor. His experience, skill, and relentless drive are evident as he battles for podiums and race wins. Fans and analysts alike are watching with bated breath to see if he can recapture the glory of his earlier years and add to his already impressive tally of wins and championships.\

### **The Impact and Legacy**

Marc Márquez's influence on MotoGP extends beyond his impressive list of achievements. His innovative approach to racing, including his distinctive riding style and strategic racecraft, has inspired a new generation of riders. His relentless pursuit of excellence and his ability to push the boundaries of motorcycle racing have made him a role model for aspiring racers.\

Off the track, Márquez's charisma and sportsmanship have earned him a special place in the hearts of fans worldwide. His engaging personality, combined with his commitment to the sport, has made him a beloved figure in the MotoGP community.\

### **Looking Ahead**

As we look to the future, Marc Márquez's career remains one of the most exciting narratives in MotoGP. His quest for additional championships, coupled with his ongoing battle to overcome injuries and challenges, ensures that his journey will continue to captivate fans around the globe.\

With each race, Márquez reaffirms why he is considered one of the greatest riders in the history of the sport. Whether he’s clinching victories or fighting through adversity, Marc Márquez embodies the spirit of MotoGP: passion, resilience, and the relentless pursuit of greatness.
`;

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get("https://retoolapi.dev/buquqx/data");
        setData(response.data);
        setLoading(false);
      } catch (error) {
        setError(error);
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  function addLineBreaksAfterPeriods(text) {
    // Replace '. ' (a period followed by a space) with '.\n' (a period followed by a newline)
    return text.replace(/\. /g, ".\n");
  }

  // Log data after it has been set
  useEffect(() => {
    console.log(data);
  }, [data]);

  if (loading) return <p>Loading...</p>;

  if (error) return <p>Error: {error.message}</p>;

  return (
    <>
      {data.map((item) => (
        <div key={item.id} style={{ marginBottom: "20px" }}>
          <h2 className="text-4xl">{item["Column 1"]}</h2>
          <ReactMarkdown className="prose">
            {addLineBreaksAfterPeriods(item["Column 2"])}
          </ReactMarkdown>
          <p>Date: {item["Column 3"]}</p>
          <p>Author: {item["Column 4"]}</p>
        </div>
      ))}

      <ReactMarkdown className="prose">{tempData}</ReactMarkdown>
    </>
  );
}

export default App;
