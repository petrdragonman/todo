import { Category } from "../services/categories-service";
import { Todo } from "../services/todos-service";
import { TodoFormData } from "./TodoForm/schema";
import TodoRow from "./todoRow/TodoRow";

interface TodosListProps {
  todos: Todo[];
  onDelete: (id: number) => void; // onDelete
  onChange: (id: number, isDone: boolean) => void;
  onDuplicate: (id: number) => void;
  //onTodo: (id: number, data: Todo) => void;
  onSubmit: (data: TodoFormData, id?: number) => unknown;
  categories: Category[];
}

const TodosList = ({
  todos,
  onDelete,
  onChange,
  onDuplicate,
  //onTodo,
  onSubmit,
  categories,
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
          //onTodo={onTodo}
          onSubmit={onSubmit}
          categories={categories}
        />
      ))}
    </>
  );
};

export default TodosList;
