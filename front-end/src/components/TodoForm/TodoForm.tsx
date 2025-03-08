//import { useRef } from "react";
import Button from "../Button/Button";
import classes from "./TodoForm.module.scss";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { schema, TodoFormData } from "./schema";
import { Category } from "../../services/categories-service";

interface TodoFormProps {
  //onSubmit: (...args: any[]) => any;
  onSubmit: (data: TodoFormData) => unknown;
  categories: Category[];
}
const TodoForm = ({ onSubmit, categories }: TodoFormProps) => {
  //const categories = ["study", "work", "hobby"];
  const {
    handleSubmit,
    register,
    reset,
    formState: { isSubmitSuccessful, errors },
  } = useForm<TodoFormData>({ resolver: zodResolver(schema) }); // TodoFormData

  isSubmitSuccessful && reset();

  return (
    <form className={classes.form} onSubmit={handleSubmit(onSubmit)}>
      <div className={classes.field}>
        <input
          type="text"
          id="todoInput"
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
            <option key={cat.id}>{cat.title}</option>
          ))}
        </select>
        {errors?.categoryTitle && (
          <small className={classes.error_text}>
            {errors?.categoryTitle?.message}
          </small>
        )}
      </div>
      <div className={classes.field}>
        <Button variant="PRIMARY" type="submit">
          Create
        </Button>
      </div>
    </form>
  );
};

export default TodoForm;
