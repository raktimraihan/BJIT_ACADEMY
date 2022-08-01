import React, { useEffect } from "react";
import { useState } from "react";
import userService from "../../service/user-service";

const OptionMapperData = ({
  post: {batchName, startTime, endTime, courseName}
}) => {
  
  return (
    <>
      
        <option value={batchName+","+courseName}>{batchName+"-"+courseName+", From:"+startTime+", To="+endTime}</option>
      
    </>
  );
};

export default OptionMapperData;
