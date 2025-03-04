import { Todo } from "../../services/book-service";
import classes from "../todoRow/TodoRow.module.scss";

interface TodoRowProps {
  todo: Todo;
}

const TodoRow = ({ todo }: TodoRowProps) => {
  return (
    <div className={classes.row}>
      <section className={classes.cell}>
        <span>{todo.isDone}</span>
        <span>{todo.title}</span>
      </section>
      <section className={classes.group}>
        <span>{todo.priority}</span>
        <span>{todo.created}</span>
      </section>
    </div>
  );
};

export default TodoRow;
