import { CategoryByMonth } from "@/api/types";

export type PieChartComponentProps = {
  data: CategoryByMonth[];
  handleSelected: (id: number | null) => void;
};
