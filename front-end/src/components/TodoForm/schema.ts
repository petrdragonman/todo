import { z } from "zod";

export const schema = z.object({
  title: z.string().min(1),
  categoryId: z.coerce.number(),
  //id: z.number().min(1),
});

export type TodoFormData = z.infer<typeof schema>;
