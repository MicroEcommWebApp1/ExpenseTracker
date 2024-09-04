export interface ExpenseResponseDTO {
    expenseId: number;  
    amount: number;
    description: string;  
    category: string;  
    createdDate: string;  
    userId: number;  
    receipt: Uint8Array;  
}
