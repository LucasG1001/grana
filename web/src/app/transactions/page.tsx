import PieChartComponent from "@/components/graphics/pie/PieChartComponent";
import styles from "./page.module.css";
import OrdenableTable from "@/components/table/OrdenableTable";

type OrdenableTableProps = {
  title: string;
  data: Array<Record<string, any>>;
  columns: Array<{
    id: string;
    label: string;
    sortDirection: "asc" | "desc";
  }>;
};

const data = [
  { name: "Eletrônicos", value: 4000 },
  { name: "Moda", value: 3000 },
];

const columns = [
  { id: "name", label: "Nome", sortDirection: "asc" },
  { id: "value", label: "Valor", sortDirection: "asc" },
];

const TransactionPage = () => {
  return (
    <div>
      <div className={styles.graphics}>
        <PieChartComponent />
        <div className={styles.test}>Teste</div>
      </div>
      <div className={styles.transactions}>
        <OrdenableTable title="Transactions" data={data} columns={columns} />
      </div>
    </div>
  );
};

export default TransactionPage;
