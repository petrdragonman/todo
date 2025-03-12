import { Category } from "./categories-service";

export interface Todo {
  id: number;
  createdAt: string;
  updatedAt: string;
  title: string;
  isDone: boolean;
  category: Category;
}

export const getAllTodos = async () => {
  const response = await fetch("http://localhost:8080/todos");
  if (!response.ok) {
    throw new Error("Failed to fetch");
  }
  return (await response.json()) as Todo[];
};

export const deleteTodoById = async (id: number) => {
  try {
    const response = await fetch("http://localhost:8080/todos/" + id, {
      method: "DELETE",
    });
    if (!response.ok) {
      throw new Error("Failed to delete item");
    }
  } catch (error) {
    console.error("Error deleting item: ", error);
  }
};

export const createTodo = async (data: Todo) => {
  const response = await fetch("http://localhost:8080/todos", {
    method: "POST",
    body: JSON.stringify({ ...data, isDone: false }),
    headers: {
      "Content-Type": "application/json",
    },
  });
  if (!response.ok) {
    throw new Error("Failed to post");
  }
  return (await response.json()) as Todo;
};

export const updateTodo = async (id: number, data: any) => {
  const response = await fetch("http://localhost:8080/todos/" + id, {
    method: "PATCH",
    body: JSON.stringify(data),
    headers: {
      "Content-Type": "application/json",
    },
  });
  if (!response.ok) {
    throw new Error("Failed to update");
  }
  return (await response.json()) as Todo;
};

export const duplicateTodo = async (data: Todo) => {
  const {
    category: { id: categoryId },
    title,
    //isDone,
  } = data;

  const response = await fetch("http://localhost:8080/todos", {
    method: "POST",
    body: JSON.stringify({
      title,
      categoryId,
      isDone: false,
    }),
    headers: {
      "Content-Type": "application/json",
    },
  });
  if (!response.ok) {
    throw new Error("Failed to post");
  }
  return (await response.json()) as Todo;
};
