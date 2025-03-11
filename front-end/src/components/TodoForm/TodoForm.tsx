import Button from "../Button/Button";
import classes from "./TodoForm.module.scss";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { schema, TodoFormData } from "./schema";

interface TodoFormProps {
  //onSubmit: (...args: any[]) => any;
  onSubmit: (data: TodoFormData, id?: number) => unknown;
  //categories: Category[];
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

  //isSubmitSuccessful && reset();
  if (!existingData) {
    isSubmitSuccessful && reset();
  }

  //console.log("Default values :", existingData);
  const handleFormSubmit = (data: TodoFormData) => {
    onSubmit(data, existingData?.id);
  };

  return (
    <form className={classes.form} onSubmit={handleSubmit(handleFormSubmit)}>
      <div className={classes.field_title}>
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
            <option key={cat.id} value={cat.title}>
              {cat.title}
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
        <Button variant="PRIMARY" type="submit">
          {existingData ? "Update" : "Create"}
        </Button>
      </div>
    </form>
  );
};

export default TodoForm;
