import Button from "../Button/Button";
import classes from "./TodoForm.module.scss";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { schema, TodoFormData } from "./schema";

interface TodoFormProps {
  onSubmit: (data: TodoFormData, id?: number) => unknown;
  categories: { id: number; title: string }[];
  existingData?: TodoFormData & { id?: number };
}
const TodoForm = ({ onSubmit, categories, existingData }: TodoFormProps) => {
  const {
    handleSubmit,
    register,
    formState: { isSubmitSuccessful, errors },
    reset,
  } = useForm<TodoFormData>({
    resolver: zodResolver(schema),
    defaultValues: existingData || {},
  }); // TodoFormData

  if (!existingData) {
    isSubmitSuccessful && reset();
  }

  const handleFormSubmit = (data: TodoFormData) => {
    onSubmit(data, existingData?.id);
  };

  return (
    <form className={classes.form} onSubmit={handleSubmit(handleFormSubmit)}>
      <div className={classes.text_field}>
        <input
          className={classes.input}
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
        <select
          className={classes.input}
          id="categoryInput"
          {...register("categoryId")}
        >
          {categories.map((cat) => (
            <option key={cat.id} value={cat.id}>
              {cat.title}
            </option>
          ))}
        </select>
        {errors?.categoryId && (
          <small className={classes.error_text}>
            {errors?.categoryId?.message}
          </small>
        )}
      </div>
      <div className={classes.field}>
        <Button variant="primary" type="submit">
          {existingData ? "Update" : "Create"}
        </Button>
      </div>
    </form>
  );
};

export default TodoForm;
