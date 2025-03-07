import { Category } from "../services/categories-service";
import CategoryCard from "./CategoryCard/CategoryCard";

interface CategoryListProps {
  categories: Category[];
}

const CategoryList = ({ categories }: CategoryListProps) => {
  if (categories.length === 0) {
    return null;
  }
  return (
    <>
      {categories.map((category) => (
        <CategoryCard key={category.id} category={category} />
      ))}
    </>
  );
};

export default CategoryList;
