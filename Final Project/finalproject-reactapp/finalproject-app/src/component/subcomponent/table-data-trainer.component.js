const PrintRowWithInRow = ({ userData }) => {
  let arrayList = userData.split(",");
  return (
    <div>
      
          {arrayList.map((e) => (
            <tr>
            <td>{e}</td>
            </tr>
          ))}
        
    </div>
  );
};

export default PrintRowWithInRow;
