interface AccountInfo {
  currentBalance: MonetaryAmount
  fullName: string
  transactions: Transaction[]
}

interface MonetaryAmount {
  amount: number
  currency: string
}

interface Transaction {
  amount: MonetaryAmount
  receiverFullName: string
}
