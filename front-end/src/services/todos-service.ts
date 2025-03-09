import { string } from "zod";
import { Category } from "./categories-service";

//type Priority = "HIGH" | "MEDIUM" | "LOW";

export interface Todo {
  id: number;
  createdAt: string;
  updatedAt: string;
  title: string;
  //priority: Priority;
  isDone: boolean;
  category: Category;
  //created: string;
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
    //console.log(`this is id: ${id}`);
    //console.log("this is id: ", id);
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
  //console.log({ ...data, isDone: false });

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

export const duplicateTodo = async (data: Todo) => {
  const { category, ...rest } = data;
  //const updatedData = { ...rest, isDone: false, categoryTitle: category.title };
  //console.log({ ...rest, isDone: false, categoryTitle: category.title });
  const response = await fetch("http://localhost:8080/todos", {
    method: "POST",
    body: JSON.stringify({
      ...rest,
      isDone: false,
      categoryTitle: category.title,
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

export const updateTodo = async (id: number, data: Todo) => {
  console.log("passed object to update: ", data);
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
