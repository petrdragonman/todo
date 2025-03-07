import { Todo } from "../../services/todos-service";
import classes from "../todoRow/TodoRow.module.scss";

interface TodoRowProps {
  todo: Todo;
  onDelete: (id: number) => void;
  onChange: (id: number, isDone: boolean) => void;
}

const TodoRow = ({ todo, onDelete, onChange }: TodoRowProps) => {
  const handleClick = () => {
    onDelete(todo.id);
  };
  const handleOnChange = () => {
    onChange(todo.id, !todo.isDone);
  };

  return (
    <div className={classes.row}>
      <section className={classes.group_todo}>
        <span onClick={handleOnChange}>
          {todo.isDone ? (
            <img src="checked.svg" alt="check box - checked" width={14} />
          ) : (
            <img src="box.svg" alt="check box - unchecked" width={14} />
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
        <img src="bin.svg" alt="trash bin" width={16} onClick={handleClick} />
      </section>
    </div>
  );
};

export default TodoRow;
