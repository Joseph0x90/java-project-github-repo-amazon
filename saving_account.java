import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class SavingsAccount extends Account {
    private Double interestRate;
}
