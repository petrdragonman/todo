import { Todo } from "../services/todos-service";
import TodoRow from "./todoRow/TodoRow";

interface TodosListProps {
  todos: Todo[];
  onDelete: (id: number) => void; // onDelete
  onChange: (id: number, isDone: boolean) => void;
  onDuplicate: (id: number) => void;
}

const TodosList = ({
  todos,
  onDelete,
  onChange,
  onDuplicate,
}: TodosListProps) => {
  if (todos.length === 0) {
    return null;
  }

  return (
    <>
      {todos.map((todo) => (
        <TodoRow
          key={todo.id}
          todo={todo}
          onDelete={onDelete}
          onChange={onChange}
          onClick={onDuplicate}
        />
      ))}
    </>
  );
};

export default TodosList;
