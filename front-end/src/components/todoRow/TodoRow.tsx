import { Todo } from "../../services/todos-service";
import Button from "../Button/Button";
import classes from "../todoRow/TodoRow.module.scss";

interface TodoRowProps {
  todo: Todo;
}

const TodoRow = ({ todo }: TodoRowProps) => {
  return (
    <div className={classes.row}>
      <section className={classes.group_todo}>
        <span>
          {todo.isDone ? (
            <img src="./box.svg" alt="check box - unchecked" width={14} />
          ) : (
            <img src="checked.svg" alt="check box - checked" width={14} />
          )}
        </span>
        <span className={classes.title}>{todo.title}</span>
      </section>
      <section className={classes.group_priority}>
        <span className={classes.cell}>{todo.priority}</span>
      </section>
      <section className={classes.group_icons}>
        {/* <span className={classes.cell}>{todo.created}</span> */}
        {/* <Button variant={"PRIMARY"}>Duplicate</Button> */}
        <img src="duplicate.svg" alt="duplicate icon" width={20} />
        <img src="bin.svg" alt="trash bin" width={16} />
      </section>
    </div>
  );
};

export default TodoRow;
