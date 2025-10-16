export type Data = {
  id: number;
  value: number;
  name: string;
};

export type PieChartComponentProps = {
  data: Data[];
  handleSelected: (id: number | null) => void;
};
