import { useState } from "react";
import { Category } from "../../services/categories-service";
import { Todo } from "../../services/todos-service";
import { TodoFormData } from "../TodoForm/schema";
import classes from "../todoRow/TodoRow.module.scss";
import TodoEditForm from "../TodoEditForm/TodoEditForm";
import TodoForm from "../TodoForm/TodoForm";

interface TodoRowProps {
  todo: Todo;
  onDelete: (id: number) => void;
  onChange: (id: number, isDone: boolean) => void;
  onClick: (id: number) => void;
  //onTodo: (id: number, data: Todo) => void;
  onSubmit: (data: TodoFormData, id?: number) => unknown;
  categories: Category[];
}

const TodoRow = ({
  todo,
  onDelete,
  onChange,
  onClick,
  //onTodo,
  onSubmit,
  categories,
}: TodoRowProps) => {
  const [isEditing, setIsEditing] = useState(true); // Toggle edit mode
  const [categoryTitle, setCategoryTitle] = useState(todo.category.title);

  const handleClick = () => {
    onDelete(todo.id);
  };
  const handleOnChange = () => {
    onChange(todo.id, !todo.isDone);
  };

  const handleOnClick = () => {
    onClick(todo.id);
  };

  // const handleOnTodo = () => {
  //   onTodo(todo.id, todo);
  // };

  // Handle input change
  // const handleInputChange = (e) => {
  //   setCategoryTitle(e.target.value);
  // };

  // Save changes and exit edit mode
  // const handleSave = () => {
  //   //onSave(todo.id, categoryTitle); // Pass the updated value to the parent component
  //   setIsEditing(false); // Exit edit mode
  // };

  // // Enter edit mode
  // const handleEditClick = () => {
  //   setIsEditing(true);
  // };

  const { category, ...rest } = todo;
  const todoData = { ...rest, categoryTitle: todo.category.title };
  //console.log(todoData);

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
        <TodoForm
          onSubmit={onSubmit}
          categories={categories}
          existingData={{
            id: todo.id,
            title: todo.title,
            categoryTitle: todo.category.title,
          }}
        />
        <img
          src="duplicate.svg"
          alt="duplicate icon"
          width={20}
          onClick={handleOnClick}
        />
        <img src="bin.svg" alt="trash bin" width={16} onClick={handleClick} />
      </section>
    </div>
  );
};

export default TodoRow;
