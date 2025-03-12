import { useEffect, useState } from "react";
import {
  createTodo,
  deleteTodoById,
  duplicateTodo,
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
      const updatedTodo = await updateTodo(id, {
        isDone,
        categoryId: todo.category.id,
      });
      setTodos((prevTodos) =>
        prevTodos.map((todo) => (todo.id === id ? updatedTodo : todo))
      );
    }
  };

  const handleDuplicate = async (id: number) => {
    const todo = todos.find((todo) => todo.id === id);
    if (todo) {
      const newTodo = await duplicateTodo(todo);
      setTodos((prevTodos) => [...prevTodos, newTodo]);
    }
  };

  const onTodoUpdateFormSubmit = async (data: any, id?: number) => {
    const todo = todos.find((todo) => todo.id === id);
    if (todo) {
      const updatedTodo = await updateTodo(todo.id, data);
      setTodos((prevTodos) =>
        prevTodos.map((todo) =>
          todo.id === updatedTodo.id ? updatedTodo : todo
        )
      );
    }
  };

  return (
    <div className={classes.container}>
      <h2 className={classes.title}>Todos App</h2>
      <section className={classes.categories_container}>
        <p className={classes.categories_container__title}>CATEGORIES</p>
        <article className={classes.categories_container__items}>
          <CategoryList categories={categories} />
        </article>
        <section className={classes.forms}>
          <CategoryForm onSubmit={onFormSubmit} />
        </section>
      </section>
      <section className={classes.todos_container}>
        <p className={classes.categories_container__title}>TODOS</p>
        <section className={classes.forms}>
          <TodoForm onSubmit={onTodoFormSubmit} categories={categories} />
        </section>
        <section className={classes.listContainer}>
          <TodosList
            todos={todos}
            onDelete={handleDelete}
            onIsDone={handleIsDone}
            onDuplicate={handleDuplicate}
            onSubmit={onTodoUpdateFormSubmit}
            categories={categories}
          />
        </section>
      </section>
    </div>
  );
};

export default TodosPage;
