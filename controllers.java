import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return accountService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Secured("ROLE_USER")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return new ResponseEntity<>(accountService.save(account), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_USER")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account accountDetails) {
        return accountService.findById(id)
                .map(account -> {
                    account.setOwnerName(accountDetails.getOwnerName());
                    account.setBalance(accountDetails.getBalance());
                    Account updatedAccount = accountService.save(account);
                    return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        return accountService.findById(id)
                .map(account -> {
                    accountService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
