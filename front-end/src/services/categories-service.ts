export interface Category {
  id: number;
  createdAt: string;
  updatedAt: string;
  title: string;
}

export const getAllCategories = async () => {
  const response = await fetch("http://localhost:8080/categories");
  if (!response.ok) {
    throw new Error("Failed to fetch categories.");
  }
  return (await response.json()) as Category[];
};

export const createCategory = async (data: Category) => {
  const response = await fetch("http://localhost:8080/categories", {
    method: "POST",
    body: JSON.stringify(data),
    headers: {
      "Content-Type": "application/json",
    },
  });
  if (!response.ok) {
    throw new Error("Failed to post");
  }
  return (await response.json()) as Category;
};
