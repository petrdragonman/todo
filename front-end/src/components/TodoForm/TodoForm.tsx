import { useRef } from "react";
import Button from "../Button/Button";
import classes from "./TodoForm.module.scss";

interface TodoFormProps {
  onSubmit: (...args: any[]) => any;
}
const TodoForm = ({ onSubmit }: TodoFormProps) => {
  const categories = ["study", "work", "hobby"];
  const formRef = useRef<HTMLFormElement>(null);

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const form = formRef.current;
    if (!form) return;
    const formData = new FormData(form);
    const formValues = Object.fromEntries(formData.entries());
    onSubmit(formValues);
  };
  return (
    <form className={classes.form} onSubmit={handleSubmit} ref={formRef}>
      <div className={classes.field}>
        {/* <label htmlFor="todoInput">TODO</label> */}
        <input
          type="text"
          id="todoInput"
          name="todo"
          placeholder="type new todo..."
        />
      </div>
      <div className={classes.field}>
        {/* <label htmlFor="categoryInput">CATEGORY</label> */}
        <select id="categoryInput" name="category">
          {categories.map((cat) => (
            <option key={cat}>{cat}</option>
          ))}
        </select>
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
