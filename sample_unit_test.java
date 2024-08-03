import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    public AccountServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Account account = new CheckingAccount();
        account.setId(1L);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        Optional<Account> foundAccount = accountService.findById(1L);

        assertTrue(foundAccount.isPresent());
        assertEquals(1L, foundAccount.get().getId());
    }
}
