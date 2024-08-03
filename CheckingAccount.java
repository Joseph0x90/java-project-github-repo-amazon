import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class CheckingAccount extends Account {
    private Double overdraftLimit;
}
