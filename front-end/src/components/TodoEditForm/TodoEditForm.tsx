import { useForm } from "react-hook-form";
import classes from "./TodoEditForm.module.scss";
import { zodResolver } from "@hookform/resolvers/zod";
import { schema, TodoEditFormData } from "./schema";
import { Category } from "../../services/categories-service";
import { Todo } from "../../services/todos-service";
import Button from "../Button/Button";

interface TodoEditFormProps {
  onSubmit: (data: TodoEditFormData) => unknown;
  todo: Todo;
  categories: Category[];
  todoId: number;
}
const TodoForm = ({
  onSubmit,
  todo,
  categories,
  todoId,
}: TodoEditFormProps) => {
  const {
    handleSubmit,
    register,
    reset,
    formState: { isSubmitSuccessful, errors },
  } = useForm<TodoEditFormData>({ resolver: zodResolver(schema) }); // TodoFormData

  //isSubmitSuccessful && reset();

  return (
    <form className={classes.form} onSubmit={handleSubmit(onSubmit)}>
      <div className={classes.field}>
        <input
          type="text"
          id="todoInput"
          value={todo.title}
          placeholder="type new todo..."
          {...register("title")}
        />
        {errors?.title && (
          <small className={classes.error_text}>{errors?.title?.message}</small>
        )}
      </div>
      <div className={classes.field}>
        <select id="categoryInput" {...register("categoryTitle")}>
          {categories.map((cat) => (
            <option key={cat.id} value={cat.title}>
              {todo.category.title}
            </option>
          ))}
        </select>
        {errors?.categoryTitle && (
          <small className={classes.error_text}>
            {errors?.categoryTitle?.message}
          </small>
        )}
      </div>
      <div className={classes.field}>
        {/* <button type="submit">
          <img
            src="duplicate.svg"
            alt="duplicate icon"
            width={20}
            //onClick={handleOnClick}
            //type="submit"
          />
        </button> */}

        <Button variant="PRIMARY" type="submit">
          Create
        </Button>
      </div>
    </form>
  );
};

export default TodoForm;
