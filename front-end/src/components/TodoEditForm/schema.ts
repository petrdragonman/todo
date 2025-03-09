import { z } from "zod";

export const schema = z.object({
  title: z.string().min(1),
  categoryTitle: z.string().min(1),
  id: z.string().min(1),
});

export type TodoEditFormData = z.infer<typeof schema>;
