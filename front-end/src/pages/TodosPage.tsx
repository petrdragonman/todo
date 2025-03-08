import { useEffect, useState } from "react";
import {
  createTodo,
  deleteTodoById,
  getAllTodos,
  Todo,
  updateTodo,
} from "../services/todos-service";
import TodosList from "../components/TodosList";
import classes from "../pages/TodosPage.module.scss";
import TodoForm from "../components/TodoForm/TodoForm";
import CategoryForm from "../components/CategoryForm/CategoryForm";
import {
  Category,
  createCategory,
  getAllCategories,
} from "../services/categories-service";
import CategoryList from "../components/CategoryList";

const TodosPage = () => {
  const [isDone, setIsDone] = useState(false);
  const [todos, setTodos] = useState<Todo[]>([]);
  const [categories, setCategories] = useState<Category[]>([]);
  useEffect(() => {
    getAllTodos()
      .then((todos) => setTodos(todos))
      .catch((e) => console.log(e));
  }, []);

  useEffect(() => {
    getAllCategories()
      .then((categories) => setCategories(categories))
      .catch((e) => console.log(e));
  }, []);

  const onFormSubmit = async (data: any) => {
    const newCategory = await createCategory(data);
    setCategories((prevCategories) => [...prevCategories, newCategory]);
  };

  const onTodoFormSubmit = async (data: any) => {
    const newTodo = await createTodo(data);
    setTodos((prevTodos) => [...prevTodos, newTodo]);
  };

  const handleDelete = async (id: number) => {
    await deleteTodoById(id);
    setTodos(todos.filter((todo) => todo.id !== id));
  };

  const handleIsDone = async (id: number, isDone: boolean) => {
    const todo = todos.find((todo) => todo.id === id);
    if (todo) {
      const updatedTodo = await updateTodo(id, { ...todo, isDone });
      setTodos((prevTodos) =>
        prevTodos.map((todo) => (todo.id === id ? updatedTodo : todo))
      );
    }
  };

  const handleDuplicate = async (id: number) => {
    const todo = todos.find((todo) => todo.id === id);
    if (todo) {
      const newTodo = await createTodo(todo);
      setTodos((prevTodos) => [...prevTodos, newTodo]);
    }
  };

  return (
    <div className={classes.container}>
      <h2 className={classes.title}>Todos App</h2>
      <section className={classes.categories_container}>
        <p className={classes.categories_container__title}>Categories</p>
        <article className={classes.categories_container__items}>
          <CategoryList categories={categories} />
        </article>
      </section>
      <section className={classes.forms}>
        <CategoryForm onSubmit={onFormSubmit} />
      </section>
      <section className={classes.forms}>
        <TodoForm onSubmit={onTodoFormSubmit} categories={categories} />
      </section>
      <section className={classes.forms}></section>
      <section className={classes.listContainer}>
        <TodosList
          todos={todos}
          onDelete={handleDelete}
          onChange={handleIsDone}
          onDuplicate={handleDuplicate}
        />
      </section>
    </div>
  );
};

export default TodosPage;
