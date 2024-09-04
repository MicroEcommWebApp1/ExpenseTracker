export interface ExpenseRequestDTO {
    amount: number;
    description: string;
    category: string;
    createdDate: Date | null;  
    userId: number;
    receipt?: File;
}
