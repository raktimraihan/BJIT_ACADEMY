import React, { Component, useEffect, useState } from "react";

import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import ObjectRow from "../response-mapping/tableheader";
import ResponsePost from "../response-mapping/table-response";

const BoardAdmin = () => {
  const [adminPosts, setAdminPosts] = useState();
  const [keyList, setKeyList] = useState();

  useEffect(() => {
    UserService.getAllAdminData().then((result) => setAdminPosts(result.data));
  }, []);

  useEffect(() => {
    if (adminPosts != null || adminPosts != undefined) {
      setKeyList(Object.keys(adminPosts[0]));
    }
  }, [adminPosts]);

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
          {adminPosts?.map((post, index) => (
            <ResponsePost index={index} post={post} />
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default BoardAdmin;
