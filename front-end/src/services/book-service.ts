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
