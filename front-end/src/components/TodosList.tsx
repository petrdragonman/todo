import { Todo } from "../services/todos-service";
import TodoRow from "./todoRow/TodoRow";

interface TodosListProps {
  todos: Todo[];
}

const TodosList = ({ todos }: TodosListProps) => {
  if (todos.length === 0) {
    return null;
  }

  return (
    <>
      {todos.map((todo) => (
        <TodoRow key={todo.id} todo={todo} />
      ))}
    </>
  );
};

export default TodosList;
