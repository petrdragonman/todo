import { useEffect, useState } from "react";
import { getAllTodos, Todo } from "../services/book-service";
import TodosList from "../components/TodosList";
import classes from "../pages/TodosPage.module.scss";

const TodosPage = () => {
  const [todos, setTodos] = useState<Todo[]>([]);
  useEffect(() => {
    getAllTodos()
      .then((todos) => setTodos(todos))
      .catch((e) => console.log(e));
  }, []);

  return (
    <div className={classes.container}>
      <TodosList todos={todos} />
    </div>
  );
};

export default TodosPage;
