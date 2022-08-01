import React from "react";

const ResponsePost = ({
  post: { id, name, userName, address, role, age },
  index,
}) => {
  return (
    <tr>
      <td>{id}</td>
      <td>{userName}</td>
      <td>{name}</td>
      <td>{address}</td>
      <td>{age}</td>
      <td>{role}</td>
    </tr>
  );
};

export default ResponsePost;
