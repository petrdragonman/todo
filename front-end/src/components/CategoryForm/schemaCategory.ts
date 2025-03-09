import { z } from "zod";

export const schemaCategory = z.object({
  title: z.string().min(1),
});

export type CategoryFormData = z.infer<typeof schemaCategory>;
