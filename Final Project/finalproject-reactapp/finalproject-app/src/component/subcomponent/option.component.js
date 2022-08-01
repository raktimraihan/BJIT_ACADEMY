import React, { useEffect } from "react";
import { useState } from "react";
import userService from "../../service/user-service";

const OptionMapper = ({axiosMethod}) => {
  const [dataFinal, setData] = useState([]);
  

  useEffect(() => {
    axiosMethod?.then((result) => setData(result.data));
  },[]);

  return (
    <>
      {dataFinal.map((valueAc) => (
        <option value={valueAc}>{valueAc}</option>
      ))}
      
    </>

    
  );
};

export default OptionMapper;
