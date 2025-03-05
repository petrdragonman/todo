//import { useRef } from "react";
import Button from "../Button/Button";
import classes from "./TodoForm.module.scss";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { schema, TodoFormData } from "./schema";

interface TodoFormProps {
  //onSubmit: (...args: any[]) => any;
  onSubmit: (data: TodoFormData) => unknown;
}
const TodoForm = ({ onSubmit }: TodoFormProps) => {
  const categories = ["study", "work", "hobby"];
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
          {...register("todo")}
        />
        {errors?.todo && (
          <small className={classes.error_text}>{errors?.todo?.message}</small>
        )}
      </div>
      <div className={classes.field}>
        <select id="categoryInput" {...register("category")}>
          {categories.map((cat) => (
            <option key={cat}>{cat}</option>
          ))}
        </select>
        {errors?.category && (
          <small className={classes.error_text}>
            {errors?.category?.message}
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
