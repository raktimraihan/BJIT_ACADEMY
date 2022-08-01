import React, { Component, useEffect, useState } from "react";

import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import ObjectRow from "../response-mapping/tableheader";
import AdminPost from "../response-mapping/table-response";

const BoardModerator = () => {
  const [userPosts, setUserPosts] = useState();
  const [keyList, setKeyList] = useState();

  useEffect(() => {
    UserService.getAllUserData().then((result) => setUserPosts(result.data));
  }, []);

  useEffect(() => {
    if (userPosts != null || userPosts != undefined) {
      setKeyList(Object.keys(userPosts[0]));
    }
  }, [userPosts]);

  console.log(keyList);

  for (var key in keyList) {
    console.log(keyList[key]);
  }

  return (
    <div>
      <table class="table table-dark">
        <thead>
          {keyList?.map((post, index) => (
            <ObjectRow post={post} index={index} />
          ))}
        </thead>
        <tbody>
          {userPosts?.map((post, index) => (
            <AdminPost index={index} post={post} />
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default BoardModerator;
