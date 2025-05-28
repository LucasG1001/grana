import { useState } from "react";
import Compras from "./pages/Compras";

function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <Compras />
    </>
  );
}

export default App;
