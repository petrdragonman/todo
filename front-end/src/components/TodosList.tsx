import { useEffect, useState } from "react";
import { deleteTodoById, Todo } from "../services/todos-service";
import TodoRow from "./todoRow/TodoRow";

interface TodosListProps {
  todos: Todo[];
  onDelete: (id: number) => void; // onDelete
}

const TodosList = ({ todos, onDelete }: TodosListProps) => {
  // onDelete
  //const [items, setItems] = useState<Todo[]>(todos);
  console.log("todos: ", todos.length);
  if (todos.length === 0) {
    return null;
  }

  // useEffect(() => {
  //   setItems(todos);
  // }, [todos]);

  // const handleDelete = async (id: number) => {
  //   await deleteTodoById(id);
  //   setItems(items.filter((item) => item.id !== id));
  // };

  //todos = items;
  // todos -> items
  return (
    <>
      {todos.map((todo) => (
        <TodoRow key={todo.id} todo={todo} onDelete={onDelete} />
      ))}
    </>
  );
};

export default TodosList;
