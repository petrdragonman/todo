import { Category } from "../../services/categories-service";
import classes from "./CategoryCard.module.scss";

interface CategoryCardProps {
  category: Category;
}

const CategoryCard = ({ category }: CategoryCardProps) => {
  return <div className={classes.card}>{category.title}</div>;
};

export default CategoryCard;
