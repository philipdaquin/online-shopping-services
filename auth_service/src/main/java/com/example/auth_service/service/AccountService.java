package com.example.auth_service.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.auth_service.domain.Address;
import com.example.auth_service.domain.actors.Account;
import com.example.auth_service.domain.actors.CustomerDetails;
import com.example.auth_service.errors.BadRequestException;
import com.example.auth_service.errors.EmailAlreadyExistsException;
import com.example.auth_service.repository.AccountRepository;
import com.example.auth_service.service.dto.RegisterDTO;

/*  
 *  Refer back to the Use Case diagram 
 * 
*/

@Service
@Transactional
public class AccountService {
    private final Logger log = LoggerFactory.getLogger(AccountService.class);
    
    private final AccountRepository accountRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    private final CustomerDetailService customerDetailService;

    
    public AccountService(
        AccountRepository accountRepository,
        PasswordEncoder passwordEncoder,
        CustomerDetailService customerDetailService
    ) { 
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.customerDetailService = customerDetailService;
    }

    /*
     * Register new Account 
     * Create Customer Details Account
     */
    public Account registerAccount(RegisterDTO registerDTO,  String password) {
        // Check if the user already exist 
        if (accountRepository.findByEmailIgnoreCase(registerDTO.getEmail()).isPresent()) { 
            throw new EmailAlreadyExistsException();
        } 

        if( password == null) throw new BadRequestException("Missing password");

        // Create new account
        Account account = new Account();
        
        account.setFirstName(registerDTO.getFirstName());
        account.setLastName(registerDTO.getLastName());
        account.setEmail(registerDTO.getEmail());
        account.setMobile(registerDTO.getMobile());
        account.setImageUrl(registerDTO.getImageUrl());

        String encryptedPassword = passwordEncoder.encode(password);
        account.setPassword(encryptedPassword);

        // Create a customer details 
        CustomerDetails customer = new CustomerDetails();
        customer.setAccount(account);
        
        Address address = new Address();
        address.setAddressLineOne(registerDTO.getAddressLineOne());
        address.setAddressLineTwo(registerDTO.getAddressLineTwo());
        address.setCity(registerDTO.getCity());
        address.setState(registerDTO.getState());
        address.setZipCode(registerDTO.getZipCode());
        address.setCountry(registerDTO.getCountry());
        
        accountRepository.save(account);
        customerDetailService.save(customer);

        return account;
    }
    
    /**
     * 
     * 
     * @param accountId
     */
    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }
    

    public Optional<Account> updateFullAccount(RegisterDTO updateDTO) {
        return accountRepository
            .findById(updateDTO.getId())
            .map(current -> { 

                if (updateDTO.getEmail() != null ) { 
                    current.setEmail(updateDTO.getEmail());
                }
                if (updateDTO.getFirstName() != null ) { 
                    current.setFirstName(updateDTO.getFirstName());
                }
                if (updateDTO.getLastName() != null ) { 
                    current.setLastName(updateDTO.getLastName());
                }
                if (updateDTO.getMobile() != null ) { 
                    current.setMobile(updateDTO.getMobile());
                }
                if (updateDTO.getImageUrl() != null ) { 
                    current.setImageUrl(updateDTO.getImageUrl());
                }
                if (updateDTO.getCreatedBy() != null ) { 
                    current.setCreatedBy(updateDTO.getCreatedBy());
                }
                if (updateDTO.getCreatedDate() != null ) { 
                    current.setCreatedDate(updateDTO.getCreatedDate());
                }
                if (updateDTO.getLastModifiedBy() != null ) { 
                    current.setLastModifiedBy(updateDTO.getLastModifiedBy());
                }
                if (updateDTO.getLastModifiedDate() != null ) { 
                    current.setLastModifiedDate(updateDTO.getLastModifiedDate());
                }

                return current;
            }).map(accountRepository::save);
    }
    

    /**
     * 
     * @param id
     * @return
     */
    public Optional<Account> getOne(Long id) {
        return accountRepository.findById(id);
    }

    /**
     * 
     * 
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<Account> getAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }
    
    /**
     * Change Password 
     * - find the current user 
     * - search user in database
     * - check if the current password mathes with the stored value 
     * - if success, encode the password and save on database 
     * 
     * @param currentPassword
     * @param newPassword
     */
    @Transactional
    public void changePassword(String currentPassword, String newPassword) {
        // todo
    }

}
