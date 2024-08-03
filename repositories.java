import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}

public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long> {
}

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {
}
