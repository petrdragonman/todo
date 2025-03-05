import { useEffect, useState } from "react";
import { getAllTodos, Todo } from "../services/book-service";
import TodosList from "../components/TodosList";
import classes from "../pages/TodosPage.module.scss";
import TodoForm from "../components/TodoForm/TodoForm";

const TodosPage = () => {
  const [todos, setTodos] = useState<Todo[]>([]);
  useEffect(() => {
    getAllTodos()
      .then((todos) => setTodos(todos))
      .catch((e) => console.log(e));
  }, []);

  const onFormSubmit = (data: any) => {
    console.log("Form submitted", data);
  };

  return (
    <div className={classes.container}>
      <h2 className={classes.title}>Todos App</h2>
      <section className={classes.forms}>
        {/* <TodoForm onSubmit={async () => console.log("submitted form")} /> */}
        <TodoForm onSubmit={onFormSubmit} />
      </section>
      <section className={classes.forms}></section>
      <section className={classes.listContainer}>
        <TodosList todos={todos} />
      </section>
      {/* <TodosList todos={todos} /> */}
    </div>
  );
};

export default TodosPage;
