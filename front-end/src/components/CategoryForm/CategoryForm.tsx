import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { schemaCategory, CategoryFormData } from "./schemaCategory";
import classes from "./CategoryForm.module.scss";
import Button from "../Button/Button";

interface CategoryFormProps {
  onSubmit: (data: CategoryFormData) => unknown;
}

const CategoryForm = ({ onSubmit }: CategoryFormProps) => {
  const {
    handleSubmit,
    register,
    reset,
    formState: { isSubmitSuccessful, errors },
  } = useForm<CategoryFormData>({ resolver: zodResolver(schemaCategory) });

  isSubmitSuccessful && reset();

  return (
    <form className={classes.form} onSubmit={handleSubmit(onSubmit)}>
      <div className={classes.field}>
        <input
          className={classes.input}
          type="text"
          id="catregoryInput"
          placeholder="type new category..."
          {...register("title")}
        />
        {errors.title && (
          <small className={classes.error_text}>{errors.title.message}</small>
        )}
      </div>
      <div>
        <Button variant="PRIMARY" type="submit" className={classes.button}>
          Create
        </Button>
      </div>
    </form>
  );
};

export default CategoryForm;
