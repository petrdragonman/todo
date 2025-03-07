import { z } from "zod";

export const schema = z.object({
  title: z.string().min(1),
  category: z.string().min(1),
});

export type TodoFormData = z.infer<typeof schema>;
