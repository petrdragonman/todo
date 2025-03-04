import { BrowserRouter, Route, Routes } from "react-router";
import TodosPage from "./pages/TodosPage";
import "./index.css";

function App() {
  return (
    <BrowserRouter>
      <h2>My Todo App</h2>
      <Routes>
        <Route path="/" element={<TodosPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
