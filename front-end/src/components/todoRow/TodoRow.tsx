import { Category } from "../../services/categories-service";
import { Todo } from "../../services/todos-service";
import { TodoFormData } from "../TodoForm/schema";
import classes from "../todoRow/TodoRow.module.scss";
import TodoForm from "../TodoForm/TodoForm";

interface TodoRowProps {
  todo: Todo;
  onDelete: (id: number) => void;
  onIsDone: (id: number, isDone: boolean) => void;
  onDuplicate: (id: number) => void;
  onSubmit: (data: TodoFormData, id?: number) => unknown;
  categories: Category[];
}

const TodoRow = ({
  todo,
  onDelete,
  onIsDone,
  onDuplicate,
  onSubmit,
  categories,
}: TodoRowProps) => {
  const handleClick = () => {
    onDelete(todo.id);
  };
  const handleIsDone = () => {
    onIsDone(todo.id, !todo.isDone);
  };

  const handleDuplicate = () => {
    onDuplicate(todo.id);
  };

  return (
    <div className={classes.row}>
      <section className={classes.group_todo}>
        <span onClick={handleIsDone} className={classes.completed_icon}>
          {todo.isDone ? (
            <img src="checked.svg" alt="check box - checked" width={22} />
          ) : (
            <img src="box.svg" alt="check box - unchecked" width={22} />
          )}
        </span>
        <TodoForm
          onSubmit={onSubmit}
          categories={categories}
          existingData={{
            id: todo.id,
            title: todo.title,
            categoryId: todo.category.id,
            //categoryTitle: todo.category.title,
          }}
        />
        <img
          src="duplicate.svg"
          alt="duplicate icon"
          width={24}
          onClick={handleDuplicate}
        />
        <img src="bin.svg" alt="trash bin" width={20} onClick={handleClick} />
      </section>
    </div>
  );
};

export default TodoRow;
