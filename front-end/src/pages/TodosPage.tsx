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
  //const [isDone, setIsDone] = useState(false);
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
    //console.log(data);
    const newCategory = await createCategory(data);
    setCategories((prevCategories) => [...prevCategories, newCategory]);
  };

  const onTodoFormSubmit = async (data: any) => {
    //console.log(data);
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
      console.log("todo in isDone: ", todo);
      const categoryTitle: String = todo.category.title;
      const updatedTodo = await updateTodo(id, {
        ...todo,
        isDone,
        categoryTitle,
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

  const onFormSubmitTodoUpdate = async (data: any, id?: number) => {
    console.log("Form Data:", data); // Debugging
    console.log("passed id:", id); // Debugging
    const todo = todos.find((todo) => todo.id === id);
    console.log("Found Todo:", todo); // Debugging

    if (todo) {
      //onst categoryId = todo.category.id;
      const category = categories.find(
        (cat) => cat.title == data.categoryTitle
      );
      if (category) {
        const test = { ...todo, title: data.title, category: category };
        console.log("TEST 1", test);
        const test2 = {
          ...todo,
          title: data.title,
          categoryTitle: category.title,
        };
        console.log("TEST 2: ", test2);
        const updatedTodo = await updateTodo(todo.id, {
          ...todo,
          title: data.title,
          categoryTitle: category.title,
        });
        setTodos((prevTodos) =>
          prevTodos.map((todo) =>
            todo.id === updatedTodo.id ? updatedTodo : todo
          )
        );
      }
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
            onChange={handleIsDone}
            onDuplicate={handleDuplicate}
            //onTodo={handleTodo}
            onSubmit={onFormSubmitTodoUpdate}
            categories={categories}
          />
        </section>
      </section>
    </div>
  );
};

export default TodosPage;
