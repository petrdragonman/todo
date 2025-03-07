import { string } from "zod";

type Priority = "HIGH" | "MEDIUM" | "LOW";

export interface Todo {
  id: number;
  createdAt: string;
  updatedAt: string;
  title: string;
  priority: Priority;
  isDone: boolean;
  created: string;
}

export const getAllTodos = async () => {
  const response = await fetch("http://localhost:8080/todos");
  if (!response.ok) {
    throw new Error("Failed to fetch");
  }
  return (await response.json()) as Todo[];
};

// export const deleteTodoById = async (id: number): Promise<void> => {
export const deleteTodoById = async (id: number) => {
  try {
    console.log(`this is id: ${id}`);
    console.log("this is id: ", id);
    const response = await fetch("http://localhost:8080/todos/" + id, {
      method: "DELETE",
    });
    if (!response.ok) {
      throw new Error("Failed to delete item");
    }
    console.log("Item deleted successfully");
  } catch (error) {
    console.error("Error deleting item: ", error);
  }
};

export const createTodo = async (data: Todo) => {
  console.log({ ...data, isDone: false });

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

export const updateTodo = async (id: number, data: Todo) => {
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
