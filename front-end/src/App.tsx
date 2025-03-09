import { BrowserRouter, Route, Routes } from "react-router";
import TodosPage from "./pages/TodosPage";
import "./index.css";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<TodosPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
